package com.powerservice.managermag.marketplace;

import com.powerservice.managermag.listini.ListiniIndexViewModel;
import it.powerservice.managermag.Listini;
import it.powerservice.managermag.ListiniService;
import it.powerservice.managermag.Marketplace;
import it.powerservice.managermag.MarketplaceService;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class MarketplaceEditViewModel {

    private Window window;
    @WireVariable
    private MarketplaceService marketplaceService;
    @WireVariable
    private ListiniService listiniService;
    private Marketplace marketplaceToSave;
    private List<Listini> listini = new ArrayList<>();
    private static MarketplaceIndexViewModel marketplaceIndexViewModel;

    private Listini selectedListino;
    private Listini selectedListinoInglese;


    @Init
    public void init(@ExecutionArgParam("selectedMarketplace") Marketplace selectedMarketplace) {
        marketplaceToSave = selectedMarketplace;
        listini = listiniService.getListini();
        initSelectedListini();
    }


    static Window apriPopup(MarketplaceIndexViewModel parentModel, Marketplace selectedMarketplace) {
        Map<String, Object> args = new HashMap<>();
        args.put("selectedMarketplace", selectedMarketplace);
        marketplaceIndexViewModel = parentModel;
        return (Window) Executions.createComponents(
                "marketplace/marketplace.edit.zul", null, args);
    }

    @Command
    @NotifyChange("marketplaceToSave")
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }


    @Command
    public void onSelectListino() {
        marketplaceToSave.setSelectedListino(selectedListino);
        marketplaceToSave.setIdListino(selectedListino.getId());
    }
    @Command
    public void onSelectListinoInglese() {
        marketplaceToSave.setSelectedListinoInglese(selectedListinoInglese);
        marketplaceToSave.setIdListinoInglese(selectedListinoInglese.getId());
    }

    @Command
    @NotifyChange("marketplaceToSave")
    public void onSaveMarketplace() {
        try {
            System.out.println(marketplaceToSave);
            marketplaceService.saveMarketplace(marketplaceToSave);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (window != null) {
                window.detach();
            }
        }
        marketplaceIndexViewModel.refresh();
        marketplaceIndexViewModel.setSelectedMarketplace(null);
        marketplaceIndexViewModel.setEditButtonVisible(false);
        Notification.show("Salvato");
    }

    public void initSelectedListini() {
        for (Listini l: listini) {
            if (marketplaceToSave.getIdListino().equals(l.getId())) {
                selectedListino = l;
            }
            if (marketplaceToSave.getIdListinoInglese().equals(l.getId())) {
                selectedListinoInglese = l;
            }
        }
    }

    public Marketplace getMarketplaceToSave() {
        return marketplaceToSave;
    }

    public List<Listini> getListini() {
        return listini;
    }
    public void setMarketplaceToSave(Marketplace marketplaceToSave) {
        this.marketplaceToSave = marketplaceToSave;
    }

    public Listini getSelectedListino() {
        return selectedListino;
    }

    public Listini getSelectedListinoInglese() {
        return selectedListinoInglese;
    }

    public void setSelectedListino(Listini selectedListino) {
        this.selectedListino = selectedListino;
    }

    public void setSelectedListinoInglese(Listini selectedListinoInglese) {
        this.selectedListinoInglese = selectedListinoInglese;
    }
}
