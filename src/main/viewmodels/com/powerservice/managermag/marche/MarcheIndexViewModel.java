package com.powerservice.managermag.marche;

import com.powerservice.managermag.marche.utilities.NewMarcheCloseListener;
import com.powerservice.managermag.marketplace.MarketplaceEditViewModel;
import com.powerservice.managermag.marketplace.utilities.EditMarketplaceCloseListener;
import it.powerservice.managermag.Marche;
import it.powerservice.managermag.MarcheService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)

public class MarcheIndexViewModel {
    @WireVariable
    private MarcheService marcheService;

    private List<Marche> marche = new ArrayList<>();
    private Marche selectedMarca = null;
    private Boolean showDeleted = false;
    private Boolean isEditButtonVisible = false;
    private Boolean isDeleteButtonVisible = false;
    private Boolean isSaveButtonVisible = false;
    private Boolean isShowDeletedButtonVisible = true;
    private Boolean isNewButtonVisible = true;
    private Boolean isEditButtonDisabled = false;
    private Boolean isSaveButtonDisabled = false;
    private Boolean isDeleteButtonDisabled = false;
    private Boolean isShowDeletedButtonDisabled = false;
    private Boolean isNewButtonDisabled = false;



    @Init
    public void init() {
        marche = marcheService.getMarche();
    }

    @Command
    @NotifyChange({"selectedMarca", "isEditButtonVisible", "isSaveButtonVisible", "isDeleteButtonVisible","isShowDeletedButtonVisible", "isEditButtonDisabled", "isDeleteButtonDisabled"})
    public void onMarcaClicked(@BindingParam("marca") Marche marca) {
        if (getIsSaveButtonVisible()) {
            setButtonsVisibility(false,false, true, false, false);
        } else {
            if (!marca.getEliminato()) {
                selectedMarca = marca;
                setButtonsVisibility(true,true, false, true, true);
            }
        }
    }

    @Command
    @NotifyChange({"selectedMarca", "marche", "isNewButtonVisible", "isEditButtonVisible", "isDeleteButtonVisible", "isSaveButtonVisible", "isShowDeletedButtonVisible"})
    public void onEditMarca() {
        selectedMarca.setIsUpdating(true);
        setButtonsVisibility(false,false, true, false, false);
    }
    @Command
    @NotifyChange({"selectedMarca", "marche", "isNewButtonVisible", "isEditButtonVisible", "isDeleteButtonVisible", "isSaveButtonVisible", "isShowDeletedButtonVisible"})
    public void onSaveMarca() {
        marcheService.saveMarca(selectedMarca);
        selectedMarca.setIsUpdating(false);
        setButtonsVisibility(true,false, false, false, true);
        Notification.show("Salvato!");
    }
    @Command
    @NotifyChange({"selectedMarca", "marche"})
    public void onDeleteMarca() {
        selectedMarca.setEliminato(true);
        marcheService.saveMarca(selectedMarca);
        refresh();
    }

    @Command
    @NotifyChange({"showDeleted", "marche"})
    public void onChangeVisibility() {
        showDeleted = !showDeleted;
    }
    @Command
    @NotifyChange({"isNewButtonVisible", "isEditButtonVisible", "isSaveButtonVisible", "isDeleteButtonVisible", "isShowDeletedButtonVisible"})
    public void setButtonsVisibility(Boolean neww, Boolean edit, Boolean save, Boolean delete, Boolean showDeleted) {
        isNewButtonVisible = neww;
        isEditButtonVisible = edit;
        isSaveButtonVisible = save;
        isDeleteButtonVisible = delete;
        isShowDeletedButtonVisible = showDeleted;
        BindUtils.postNotifyChange(null, null, this, "isNewButtonVisible");
        BindUtils.postNotifyChange(null, null, this, "isEditButtonVisible");
        BindUtils.postNotifyChange(null, null, this, "isSaveButtonVisible");
        BindUtils.postNotifyChange(null, null, this, "isDeleteButtonVisible");
        BindUtils.postNotifyChange(null, null, this, "isShowDeletedButtonVisible");
    }
    @Command
    @NotifyChange({"isNewButtonDisabled", "isEditButtonDisabled", "isSaveButtonDisabled", "isDeleteButtonDisabled", "isShowDeletedButtonDisabled"})
    public void setButtonsDisabledStatus(Boolean neww, Boolean edit, Boolean save, Boolean delete, Boolean showDeleted) {
        isNewButtonDisabled = neww;
        isEditButtonDisabled = edit;
        isSaveButtonDisabled = save;
        isDeleteButtonDisabled = delete;
        isShowDeletedButtonDisabled = showDeleted;
        BindUtils.postNotifyChange(null, null, this, "isNewButtonDisabled");
        BindUtils.postNotifyChange(null, null, this, "isEditButtonDisabled");
        BindUtils.postNotifyChange(null, null, this, "isSaveButtonDisabled");
        BindUtils.postNotifyChange(null, null, this, "isDeleteButtonDisabled");
        BindUtils.postNotifyChange(null, null, this, "isShowDeletedButtonDisabled");
    }

    @Command
    public void onCreateMarca() {
        MarcheNewViewModel.apriPopup(this).addEventListener(Events.ON_CLOSE, new NewMarcheCloseListener(this));
    }

    public List<Marche> getMarche() {
        return marche;
    }

    public Marche getSelectedMarca() {
        return selectedMarca;
    }

    public void setSelectedMarca(Marche selectedMarca) {
        this.selectedMarca = selectedMarca;
    }

    public Boolean getIsEditButtonVisible() {
        return isEditButtonVisible;
    }

    public Boolean getIsDeleteButtonVisible() {
        return isDeleteButtonVisible;
    }

    public Boolean getIsSaveButtonVisible() {
        return isSaveButtonVisible;
    }
    public Boolean getShowDeleted() {
        return showDeleted;
    }


    public Boolean getIsShowDeletedButtonVisible() {
        return isShowDeletedButtonVisible;
    }

    public Boolean getIsEditButtonDisabled() {
        return isEditButtonDisabled;
    }

    public Boolean getIsNewButtonVisible() {
        return isNewButtonVisible;
    }

    public Boolean getIsNewButtonDisabled() {
        return isNewButtonDisabled;
    }

    public Boolean getIsDeleteButtonDisabled() {
        return isDeleteButtonDisabled;
    }

    public Boolean getIsSaveButtonDisabled() {
        return isSaveButtonDisabled;
    }

    public Boolean getIsShowDeletedButtonDisabled() {
        return isShowDeletedButtonDisabled;
    }

    public void refresh() {
        marche = marcheService.getMarche();
        setButtonsVisibility(true, false, false, false, true);
        BindUtils.postNotifyChange(null, null, this, "marche");
    }
}
