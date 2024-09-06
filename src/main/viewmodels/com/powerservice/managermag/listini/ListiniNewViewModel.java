package com.powerservice.managermag.listini;

import it.powerservice.managermag.Listini;
import it.powerservice.managermag.ListiniService;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;


@VariableResolver(DelegatingVariableResolver.class)
public class ListiniNewViewModel {

    private Window window;
    @WireVariable
    private ListiniService listiniService;
    private Listini listinoToSave;
    private static ListiniIndexViewModel listiniIndexViewModel;


    @Init
    public void init() {
        listinoToSave = new Listini();
    }
    static Window apriPopup(ListiniIndexViewModel parentModel) {
        listiniIndexViewModel = parentModel;
        return (Window) Executions.createComponents(
                "listini/listini.new.zul", null, null);
    }

    @Command
    @NotifyChange("listinoToSave")
    public void onSaveListino() {
        try {
            listiniService.saveListino(listinoToSave);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (window != null) {
                window.detach();
            }
        }
        listiniIndexViewModel.refresh();
        Notification.show("Salvato");
    }


    @Command
    @NotifyChange("listinoToSave")
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }

    public Listini getListinoToSave() {
        return listinoToSave;
    }

    public void setListinoToSave(Listini listinoToSave) {
        this.listinoToSave = listinoToSave;
    }
}
