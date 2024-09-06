package com.powerservice.managermag.dizionari;

import com.powerservice.managermag.dizionari.utilities.DizionariUI;
import it.powerservice.managermag.*;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Window;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class DizionariIndexViewModel {
    @WireVariable
    DizionariCategorieService dizionariCategorieService;
    @WireVariable
    DizionariService dizionariService;
    List<DizionariCategorie> dizionariCategorie = new ArrayList<>();
    List<DizionariUI> dizionariUI = new ArrayList<>();
    private Integer selectedDizionarioCategoriaIndex = 0;
    private Boolean saveButtonDisabled = true;

    @Init
    public void init(@ContextParam(ContextType.COMPONENT) Window w) throws Exception {
        initDizionariCategorie();;
        initDizionari();
    }
    public void initDizionariCategorie() {
        dizionariCategorie = dizionariCategorieService.getDizionariCategorie();
    }

    public void initDizionari() throws SQLException {
        dizionariUI = dizionariService.getDizionariUi(dizionariCategorie.get(selectedDizionarioCategoriaIndex).getCategoria());
    }
    @Command
    @NotifyChange("dizionariUI")
    public void onSelect() throws SQLException {
        initDizionari();
    }

    @Command
    @NotifyChange({"saveButtonDisabled"})
    public void onSave() {
        for (DizionariUI dizionarioUI: dizionariUI) {
            if (dizionarioUI.getUpdated()) {
                var dizionario = new Dizionari(
                        dizionarioUI.getCodice(),
                        dizionarioUI.getCategoria(),
                        dizionarioUI.getDescrizioneDizionario(),
                        dizionarioUI.getModificabile()
                );
                dizionariService.saveDizionari(dizionario);
            }
        }
        saveButtonDisabled = true;
        Notification.show("Salvati");
    }
    @Command
    @NotifyChange({"dizionariUI", "saveButtonDisabled"})
    public void onChange(@BindingParam("dizionarioUI") DizionariUI dizionarioUI) {
        dizionarioUI.setUpdated(true);
        saveButtonDisabled = false;
    }
    public Integer getSelectedDizionarioCategoriaIndex() {
        return selectedDizionarioCategoriaIndex;
    }

    public void setSelectedDizionarioCategoriaIndex(Integer selectedDizionarioCategoriaIndex) {
        this.selectedDizionarioCategoriaIndex = selectedDizionarioCategoriaIndex;
    }

    public List<DizionariCategorie> getDizionariCategorie() {
        return dizionariCategorie;
    }

    public List<DizionariUI> getDizionariUI() {
        return dizionariUI;
    }

    public Boolean getSaveButtonDisabled() {
        return saveButtonDisabled;
    }

    public void setSaveButtonDisabled(Boolean saveButtonDisabled) {
        this.saveButtonDisabled = saveButtonDisabled;
    }
}
