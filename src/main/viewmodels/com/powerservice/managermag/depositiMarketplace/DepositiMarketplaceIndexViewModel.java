package com.powerservice.managermag.depositiMarketplace;

import com.powerservice.managermag.depositiMarketplace.utilities.CustomDepositiMarketplace;
import com.powerservice.managermag.marche.MarcheNewViewModel;
import com.powerservice.managermag.marche.utilities.NewMarcheCloseListener;
import it.powerservice.managermag.DepositiMarketplace;
import it.powerservice.managermag.DepositiMarketplaceService;
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
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class DepositiMarketplaceIndexViewModel {
    @WireVariable
    MarketplaceService marketplaceService;
    @WireVariable
    DepositiMarketplaceService depositiMarketplaceService;
    List<Marketplace> marketplaces = new ArrayList<>();
    List<CustomDepositiMarketplace> currentDepositi = new ArrayList<>();

    Marketplace clickedMarketplace = null;
    CustomDepositiMarketplace clickedDeposito = null;
    Boolean isRemoveButtonVisible = false;
    Boolean isAddButtonVisible = false;

    @Init
    public void init() {
        marketplaces = marketplaceService.getMarketplace();
    }

    @NotifyChange({"marketplace", "currentDepositi", "isAddButtonVisible"})
    @Command
    public void onMarketplaceClicked(@BindingParam("marketplace") Marketplace marketplace) throws SQLException {
        clickedMarketplace = marketplace;
        currentDepositi = depositiMarketplaceService.getDepositiByIdMarketplace(marketplace.getId());
        isAddButtonVisible = true;
    }
    @NotifyChange({"clickedDeposito", "isRemoveButtonVisible"})
    @Command
    public void onDepositoClicked(@BindingParam("clickedDeposito") CustomDepositiMarketplace deposito) {
        clickedDeposito = deposito;
        isRemoveButtonVisible = true;
    }
    @NotifyChange({"currentDepositi", "isRemoveButtonVisible"})
    @Command
    public void onRemoveDeposito() throws SQLException {
        depositiMarketplaceService.deleteByIdDeposito(clickedDeposito.getId(), clickedMarketplace.getId());
        isRemoveButtonVisible = false;
        refreshCurrentDepositi();
    }
    @NotifyChange({"currentDepositi", "isRemoveButtonVisible"})
    @Command
    public void onNonInviareChecked(@BindingParam("clickedDeposito") CustomDepositiMarketplace deposito) throws SQLException {
        DepositiMarketplace depositoMarketplace = depositiMarketplaceService.findDepositoMarketplaceByIdDepositoAndIdMarketplace(deposito.getId(), clickedMarketplace.getId());
        depositoMarketplace.setNonInviare(deposito.getNonInviare());
        depositiMarketplaceService.saveDepositoMarketplace(depositoMarketplace);
        refreshCurrentDepositi();
        Notification.show("Salvato!");
    }

    @Command
    public void onAddClicked() {
        Map<String, Object> params = new HashMap<>();
        params.put("clickedMarketplace", clickedMarketplace);
        DepositiMarketplaceAddViewModel.apriPopup(this, params, clickedMarketplace);
    }


    public void refreshCurrentDepositi() throws SQLException {
        currentDepositi = depositiMarketplaceService.getDepositiByIdMarketplace(clickedMarketplace.getId());
        BindUtils.postNotifyChange(null, null, this, "currentDepositi");
    }

    public List<Marketplace> getMarketplaces() {
        return marketplaces;
    }

    public List<CustomDepositiMarketplace> getCurrentDepositi() {
        return currentDepositi;
    }

    public Boolean getIsRemoveButtonVisible() {
        return isRemoveButtonVisible;
    }

    public Marketplace getClickedMarketplace() {
        return clickedMarketplace;
    }

    public Boolean getIsAddButtonVisible() {
        return isAddButtonVisible;
    }
}
