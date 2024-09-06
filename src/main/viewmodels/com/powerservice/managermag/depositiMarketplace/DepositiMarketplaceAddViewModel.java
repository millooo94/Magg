package com.powerservice.managermag.depositiMarketplace;

import com.powerservice.managermag.marche.MarcheIndexViewModel;
import it.powerservice.managermag.*;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class DepositiMarketplaceAddViewModel {
    private Window window;
    @WireVariable
    DepositiService depositiService;
    @WireVariable
    DepositiMarketplaceService depositiMarketplaceService;
    List<Depositi> depositiNonAssociati = new ArrayList<>();
    List<Depositi> depositiDaAggiungere = new ArrayList<>();


    Marketplace clickedMarketplace = null;
    private static DepositiMarketplaceIndexViewModel depositiMarketplaceIndexViewModel;

    @Init
    public void init() {
        clickedMarketplace = (Marketplace) Executions.getCurrent().getArg().get("clickedMarketplace");
        depositiNonAssociati = depositiService.findDepositiNonAssociati(clickedMarketplace.getId());
    }
    static Window apriPopup(DepositiMarketplaceIndexViewModel parentModel, Map<String, Object> params,  Marketplace clickedMarketplace) {
        depositiMarketplaceIndexViewModel = parentModel;
        Window window =  (Window) Executions.createComponents(
                "depositiMarketplace/depositiMarketplace.add.zul", null, params);
        window.setAttribute("clickedMarketplace", clickedMarketplace);
        return  window;
    }
    @Command
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }
    @Command
    public void onDepositoChecked(@BindingParam("deposito") Depositi deposito) {
        depositiDaAggiungere.add(deposito);
    }
    @Command
    public void onSaveDepositi() throws SQLException {
        try {
            for (Depositi deposito: depositiDaAggiungere) {
                DepositiMarketplace depositoMarketplace = new DepositiMarketplace(deposito.getId(), clickedMarketplace.getId(), false);
                depositiMarketplaceService.saveDepositoMarketplace(depositoMarketplace);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (window != null) {
                window.detach();
            }
        }

        depositiMarketplaceIndexViewModel.refreshCurrentDepositi();
        Notification.show("Salvato");
    }

    public List<Depositi> getDepositiNonAssociati() {
        return depositiNonAssociati;
    }
}
