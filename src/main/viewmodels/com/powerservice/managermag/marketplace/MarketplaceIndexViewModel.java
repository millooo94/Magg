package com.powerservice.managermag.marketplace;

import com.powerservice.managermag.marketplace.utilities.EditMarketplaceCloseListener;
import it.powerservice.managermag.Listini;
import it.powerservice.managermag.ListiniService;
import it.powerservice.managermag.Marketplace;
import it.powerservice.managermag.MarketplaceService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class MarketplaceIndexViewModel {
    @WireVariable
    private MarketplaceService marketplaceService;
    @WireVariable
    private ListiniService listiniService;
    private List<Marketplace> marketplaces = new ArrayList<>();
    private List<Listini> listini = new ArrayList<>();
    private Marketplace selectedMarketplace = null;
    private Boolean isEditButtonVisible = false;
    private Boolean buttonDisabled = false;

    @Init
    public void init() {
        marketplaces = marketplaceService.getMarketplace();
        listini = listiniService.getListini();
        initSelectedListini();
    }
    @Command
    @NotifyChange({"selectedMarkeplace", "isEditButtonVisible"})
    public void onMarketplaceClicked(@BindingParam("marketplace") Marketplace marketplace) {
        this.selectedMarketplace = marketplace;
        isEditButtonVisible = true;
    }

    @Command
    @NotifyChange({"buttonDisabled"})
    public void onEditMarketplace() {
        MarketplaceEditViewModel.apriPopup(this, selectedMarketplace).addEventListener(Events.ON_CLOSE, new EditMarketplaceCloseListener(this));
        buttonDisabled = true;
    }

    public  void refresh() {
        marketplaces = marketplaceService.getMarketplace();
        initSelectedListini();
        buttonDisabled = false;
        BindUtils.postNotifyChange(null, null, this, "marketplaces");
        BindUtils.postNotifyChange(null, null, this, "buttonDisabled");
    }

    public List<Marketplace> getMarketplaces() {
        return marketplaces;
    }

    public List<Listini> getListini() {
        return listini;
    }

    public Marketplace getSelectedMarketplace() {
        return selectedMarketplace;
    }

    public void setSelectedMarketplace(Marketplace selectedMarketplace) {
        this.selectedMarketplace = selectedMarketplace;
    }
    public Boolean getIsEditButtonVisible() {
        return isEditButtonVisible;
    }

    public void setEditButtonVisible(Boolean editButtonVisible) {
        isEditButtonVisible = editButtonVisible;
        BindUtils.postNotifyChange(null, null, this, "isEditButtonVisible");
    }

    public Boolean getButtonDisabled() {
        return buttonDisabled;
    }

    public void setButtonDisabled(Boolean buttonDisabled) {
        this.buttonDisabled = buttonDisabled;
        BindUtils.postNotifyChange(null, null, this, "buttonDisabled");
    }
    public String getMarketplaceStatusClass(Marketplace marketplace) {
        if (marketplace.getAttivo()) {
            return "active";
        } else return  "unactive";
    }

    public void initSelectedListini() {
        for (Marketplace m: marketplaces) {
            for (Listini l: listini) {
                if (m.getIdListino().equals(l.getId())) {
                    m.setSelectedListino(l);
                }
                if (m.getIdListinoInglese().equals(l.getId())) {
                    m.setSelectedListinoInglese(l);
                }
            }
        }
    }
}
