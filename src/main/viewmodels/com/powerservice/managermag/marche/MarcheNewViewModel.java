package com.powerservice.managermag.marche;

import com.powerservice.managermag.marketplace.MarketplaceIndexViewModel;
import it.powerservice.managermag.Marche;
import it.powerservice.managermag.MarcheService;
import it.powerservice.managermag.Marketplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class MarcheNewViewModel {
    private Window window;
    @WireVariable
    MarcheService marcheService;
    Marche marcaToSave;
    private static MarcheIndexViewModel marcheIndexViewModel;

    @Init
    public void init() {
        marcaToSave = new Marche();
    }

    static Window apriPopup(MarcheIndexViewModel parentModel) {
        marcheIndexViewModel = parentModel;
        return (Window) Executions.createComponents(
                "marche/marche.new.zul", null, null);
    }

    @Command
    @NotifyChange("marcaToSave")
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }

    @Command
    @NotifyChange("marcaToSave")
    public void onSaveMarca() {
        try {
            marcheService.saveMarca(marcaToSave);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (window != null) {
                window.detach();
            }
        }
        marcheIndexViewModel.refresh();
        marcheIndexViewModel.setSelectedMarca(null);
        Notification.show("Salvato");
    }

    public Marche getMarcaToSave() {
        return marcaToSave;
    }
}
