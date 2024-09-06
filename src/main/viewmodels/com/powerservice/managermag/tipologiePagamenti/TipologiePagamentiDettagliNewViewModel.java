package com.powerservice.managermag.tipologiePagamenti;

import it.powerservice.managermag.Categorie;
import it.powerservice.managermag.TipologiePagamenti;
import it.powerservice.managermag.TipologiePagamentiDettagli;
import it.powerservice.managermag.TipologiePagamentiDettagliService;
import it.powerservice.managermag.enums.ActionType;
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
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class TipologiePagamentiDettagliNewViewModel {
    @WireVariable
    TipologiePagamentiDettagliService tipologiePagamentiDettagliService;
    private Window window;
    private static TipologiePagamentiIndexViewModel tipologiePagamentiIndexViewModel;

    private TipologiePagamenti selectedTipologiaPagamento = null;
    TipologiePagamentiDettagli tipologiaPagamentoDettagliToSave = new TipologiePagamentiDettagli();

    @Init
    void init() {
        Map<?, ?> args = Executions.getCurrent().getArg();
        selectedTipologiaPagamento = (TipologiePagamenti) args.get("selectedTipologiaPagamento");
        tipologiaPagamentoDettagliToSave.setIdPagamento(selectedTipologiaPagamento.getId());
    }


    static Window apriPopup(TipologiePagamentiIndexViewModel parentModel, Map<String, Object> params) {
        tipologiePagamentiIndexViewModel = parentModel;
        Window window = (Window) Executions.createComponents(
                "tipologiePagamenti/tipologiePagamentiDettagli.new.zul", null, params);
        return window;
    }

    @Command
    @NotifyChange("tipologiaPagamentoToSave")
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }

    public TipologiePagamentiDettagli getTipologiaPagamentoDettaglioToSave() {
        return tipologiaPagamentoDettagliToSave;
    }

    @Command
    public void onSaveTipologiePagamentiDettagli() {
        try {
            tipologiePagamentiDettagliService.onSaveTipologiaPagamentoDettagli(tipologiaPagamentoDettagliToSave);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (window != null) {
                window.detach();
            }
        }
        tipologiePagamentiIndexViewModel.refreshCurrentTipologiePagamentiDettaglio(selectedTipologiaPagamento.getId());
        Notification.show("Salvato");
    }
}
