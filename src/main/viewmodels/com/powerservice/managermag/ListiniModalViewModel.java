package com.powerservice.managermag;

import it.powerservice.managermag.Listini;
import it.powerservice.managermag.ListiniService;
import it.powerservice.managermag.enums.ActionType;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class ListiniModalViewModel {

    @WireVariable
    ListiniService listiniService;
    List<Listini> listini = new ArrayList<>();
    Listini rightClickedListino;
    Boolean showDeleted = false;
    Boolean buttonDisabled = false;

    @Init
    public void init() {
        listini = listiniService.getListini();
    }
    @Command
    @NotifyChange({"buttonDisabled"})
    public void onCreateListino() {
        ListiniContextMenuModalViewModel.apriPopup(this).addEventListener(Events.ON_CLOSE, new ListiniModalCloseListener(this));
        buttonDisabled = true;
    }
    @Command
    @NotifyChange({"rightClickedListino"})
    public void onRightClickListino(@BindingParam("listino") Listini listino) {
        rightClickedListino = listino;
    }
    @Command
    public void onChangeListino(@BindingParam("listino") Listini listino) {
        listino.setIsUpdating(true);
    }
    @Command
    @NotifyChange({"listini"})
    public void onSaveListino() {
        for(Listini listino: listini) {
            if (listino.getIsUpdating()) {
                listiniService.saveListino(listino);
            }
        }
    }
    @Command
    @NotifyChange({"listini"})
    public void onDeleteRestoreListino(@BindingParam("action") String action) {
        var value = action.equals("delete");
        rightClickedListino.setEliminato(value);
    }

    @Command
    @NotifyChange({"showDeleted"})
    public void onChangeVisibility() {
        showDeleted = !showDeleted;
    }

    public  void refresh() {
        listini = listiniService.getListini();
        buttonDisabled = false;
        BindUtils.postNotifyChange(null, null, this, "listini");
        BindUtils.postNotifyChange(null, null, this, "buttonDisabled");
    }

    public List<Listini> getListini() {
        return listini;
    }
    public Boolean getShowDeleted() {
        return showDeleted;
    }

    public Boolean getButtonDisabled() {
        return buttonDisabled;
    }

    public void setButtonDisabled(Boolean buttonDisabled) {
        this.buttonDisabled = buttonDisabled;
        BindUtils.postNotifyChange(null, null, this, "buttonDisabled");
    }
}
