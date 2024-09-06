package com.powerservice.managermag.tipologiePagamenti;

import com.powerservice.managermag.tipologiePagamenti.utilities.PagFattPa;
import it.powerservice.managermag.*;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class TipologiePagamentiIndexViewModel {

    @WireVariable
    TipologiePagamentiService tipologiePagamentiService;
    @WireVariable
    TipologiePagamentiDettagliService tipologiePagamentiDettagliService;
    @WireVariable
    BancheService bancheService;
    List<TipologiePagamenti> tipologiePagamenti = new ArrayList<>();
    List<TipologiePagamentiDettagli> currentTipologiePagamentiDettagli = new ArrayList<>();
    List<Banche> banche = new ArrayList<>();
    List<PagFattPa> modPagFattPa = new ArrayList<>();
    List<PagFattPa> condPagFattPa = new ArrayList<>();
    TipologiePagamenti selectedTipologiaPagamento = null;
    TipologiePagamentiDettagli selectedTipologiaPagamentoDettaglio = null;

    private Boolean isTipologiePagamentiRemoveButtonVisible = false;
    private Boolean isTipologiePagamentiSaveButtonVisible = false;

    private Boolean isTipologiePagamentiDettagliRemoveButtonVisible = false;
    private Boolean isTipologiePagamentiDettagliSaveButtonVisible = false;
    private Boolean isTipologiePagamentiDettagliAddButtonVisible = false;



    private Boolean showDeleted = false;

    @Init
    public void init() {
        tipologiePagamenti = tipologiePagamentiService.getTipologiePagamenti();
        banche = bancheService.getBanche();
        initSelectedBanche();
        initPagFattPaLists();
        initSelectedPagFattPa();
    }

    public void initSelectedBanche() {
        for (TipologiePagamenti tp: tipologiePagamenti) {
            for (Banche b: banche) {
                if (tp.getIdBanca() != null && tp.getIdBanca().equals(b.getId())) {
                    tp.setSelectedBanca(b);
                }
            }
        }
    }

    public void initPagFattPaLists() {
        condPagFattPa.add(new PagFattPa(null, "(Nessuna)"));
        condPagFattPa.add(new PagFattPa("TP01", "Pagamento a rate"));
        condPagFattPa.add(new PagFattPa("TP02", "Pagamento completo"));
        condPagFattPa.add(new PagFattPa("TP03", "Anticipo"));

        modPagFattPa.add(new PagFattPa(null, "(Nessuna)")); // Valore fittizio
        modPagFattPa.add(new PagFattPa("MP01", "Contanti"));
        modPagFattPa.add(new PagFattPa("MP02", "Assegno"));
        modPagFattPa.add(new PagFattPa("MP03", "Assegno Circolare"));
        modPagFattPa.add(new PagFattPa("MP04", "Contanti presso Tesoreria"));
        modPagFattPa.add(new PagFattPa("MP05", "Bonifico"));
        modPagFattPa.add(new PagFattPa("MP06", "Vaglia Cambiario"));
        modPagFattPa.add(new PagFattPa("MP07", "Bollettino Bancario"));
        modPagFattPa.add(new PagFattPa("MP08", "Carta di Pagamento"));
        modPagFattPa.add(new PagFattPa("MP09", "RID"));
        modPagFattPa.add(new PagFattPa("MP10", "RID Utenze"));
        modPagFattPa.add(new PagFattPa("MP11", "RID Veloce"));
        modPagFattPa.add(new PagFattPa("MP12", "RIBA"));
        modPagFattPa.add(new PagFattPa("MP13", "MAV"));
        modPagFattPa.add(new PagFattPa("MP14", "Quietanza Erario"));
        modPagFattPa.add(new PagFattPa("MP15", "Giroconto su conti di contabilità speciale"));
        modPagFattPa.add(new PagFattPa("MP16", "Domiciliazione Bancaria"));
        modPagFattPa.add(new PagFattPa("MP17", "Domiciliazione Postale"));
        modPagFattPa.add(new PagFattPa("MP18", "Bollettino di c/c Postale"));
        modPagFattPa.add(new PagFattPa("MP19", "SEPA Direct Debit"));
        modPagFattPa.add(new PagFattPa("MP20", "SEPA Direct Debit CORE"));
        modPagFattPa.add(new PagFattPa("MP21", "SEPA Direct Debit B2B"));
        modPagFattPa.add(new PagFattPa("MP22", "Trattenuta su somme già riscosse"));
    }

    public void initSelectedPagFattPa() {
        for (TipologiePagamenti tp: tipologiePagamenti) {
            for (PagFattPa mfpa: modPagFattPa) {

                if (mfpa.getDescrizione().equals(tp.getModPagFattPA())) {
                    tp.setSelectedModPagFattPA(mfpa);
                }
            }
            for (PagFattPa cfpa: condPagFattPa) {
                if (cfpa.getDescrizione().equals(tp.getCondPagFattPA())) {
                    tp.setSelectedCondPagFattPA(cfpa);
                }
            }
        }
    }
    public void refresh() {
        tipologiePagamenti = tipologiePagamentiService.getTipologiePagamenti();
        BindUtils.postNotifyChange(null, null, this, "tipologiePagamenti");
    }

    @NotifyChange({"currentTipologiePagamentiDettagli"})
    public void refreshCurrentTipologiePagamentiDettaglio(Long tipologiaPagamentoId) {
        currentTipologiePagamentiDettagli = tipologiePagamentiDettagliService.findTipologiePagamentiDettagliFromTipologiePagamenti(tipologiaPagamentoId);
        BindUtils.postNotifyChange(null, null, this, "currentTipologiePagamentiDettagli");
    }

    @Command
    @NotifyChange({"isTipologiePagamentiSaveButtonVisible"})
    public void onSelectModPagFattPa(@BindingParam("item") TipologiePagamenti item) {
        onChangeTipologiePagamento(item);
        item.setModPagFattPA(item.getSelectedModPagFattPA().getDescrizione());
    }
    @Command
    @NotifyChange({"isTipologiePagamentiSaveButtonVisible"})
    public void onSelectCondPagFattPa(@BindingParam("item") TipologiePagamenti item) {
        onChangeTipologiePagamento(item);
        item.setCondPagFattPA(item.getSelectedCondPagFattPA().getDescrizione());
    }
    @Command
    @NotifyChange({"selectedTipologiaPagamento", "isTipologiePagamentiRemoveButtonVisible", "isTipologiePagamentiDettagliAddButtonVisible", "currentTipologiePagamentiDettagli"})
    public void onSelectTipologiaPagamento(@BindingParam("tipologiaPagamento") TipologiePagamenti tipologiaPagamento) {
        selectedTipologiaPagamento = tipologiaPagamento;
        isTipologiePagamentiRemoveButtonVisible = true;
        isTipologiePagamentiDettagliAddButtonVisible = true;
        currentTipologiePagamentiDettagli = tipologiePagamentiDettagliService.findTipologiePagamentiDettagliFromTipologiePagamenti(tipologiaPagamento.getId());
    }
    @Command
    @NotifyChange({"selectedTipologiaPagamentoDettaglio", "isTipologiePagamentiDettagliRemoveButtonVisible"})
    public void onSelectTipologiaPagamentoDettaglio(@BindingParam("tipologiaPagamentoDettaglio") TipologiePagamentiDettagli tipologiaPagamentoDettaglio) {
        selectedTipologiaPagamentoDettaglio = tipologiaPagamentoDettaglio;
        isTipologiePagamentiDettagliRemoveButtonVisible = true;
    }
    @Command
    public void onSaveTipologiePagamenti() {
        for (TipologiePagamenti tp: tipologiePagamenti) {
            if (tp.getIsUpdating()) {
                tipologiePagamentiService.onSaveTipologiaPagamento(tp);
            }
        }
        Notification.show("Salvati");
    }
    @Command
    public void onSaveTipologiePagamentiDettagli() {
        for (TipologiePagamentiDettagli tpd: currentTipologiePagamentiDettagli) {
            if (tpd.getIsUpdating()) {
                tipologiePagamentiDettagliService.onSaveTipologiaPagamentoDettagli(tpd);
            }
        }
        Notification.show("Salvati");
    }
    @Command
    @NotifyChange({"isTipologiePagamentiSaveButtonVisible"})
    public void onChangeTipologiePagamento(@BindingParam("tipologiaPagamento") TipologiePagamenti tipologiePagamento) {
        tipologiePagamento.setIsUpdating(true);
        System.out.println(tipologiePagamento.getIsUpdating());
        isTipologiePagamentiSaveButtonVisible = true;
    }
    @Command
    @NotifyChange({"isTipologiePagamentiDettagliSaveButtonVisible"})
    public void onChangeTipologiePagamentoDettagli(@BindingParam("tipologiaPagamentoDettagli") TipologiePagamentiDettagli tipologiePagamentoDettagli) {
        tipologiePagamentoDettagli.setisUpdating(true);
        isTipologiePagamentiDettagliSaveButtonVisible = true;
    }
    @Command
    public void onCreateTipologiaPagamento() {
        TipologiePagamentiNewViewModel.apriPopup(this);
    }
    @Command
    public void onCreateTipologiaPagamentoDettagli() {
        Map<String, Object> params = new HashMap<>();
        params.put("selectedTipologiaPagamento", selectedTipologiaPagamento);
        TipologiePagamentiDettagliNewViewModel.apriPopup(this, params);
    }
    @Command
    @NotifyChange({"selectedTipologiaPagamento", "tipologiePagamenti"})
    public void onDeleteTipologiaPagamento() {
        selectedTipologiaPagamento.setEliminato(true);
        tipologiePagamentiService.onSaveTipologiaPagamento(selectedTipologiaPagamento);
        selectedTipologiaPagamento = null;
        refresh();
    }
    @Command
    @NotifyChange({"selectedTipologiaPagamentoDettaglio", "currentTipologiePagamentiDettagli"})
    public void onDeleteTipologiaPagamentoDettaglio() {
        tipologiePagamentiDettagliService.deleteTipologiaPagamentoDettaglio(selectedTipologiaPagamentoDettaglio.getId());
        selectedTipologiaPagamentoDettaglio = null;
        refreshCurrentTipologiePagamentiDettaglio(selectedTipologiaPagamento.getId());
    }
    @Command
    @NotifyChange({"showDeleted", "tipologiePagamenti"})
    public void onChangeVisibility() {
        showDeleted = !showDeleted;
    }

    public List<TipologiePagamenti> getTipologiePagamenti() {
        return tipologiePagamenti;
    }

    public List<Banche> getBanche() {
        return banche;
    }

    public List<PagFattPa> getModPagFattPa() {
        return modPagFattPa;
    }

    public List<PagFattPa> getCondPagFattPa() {
        return condPagFattPa;
    }

    public Boolean getIsTipologiePagamentiRemoveButtonVisible() {
        return isTipologiePagamentiRemoveButtonVisible;
    }
    public void setIsTipologiePagamentiRemoveButtonVisible(Boolean isTipologiePagamentiRemoveButtonVisible) {
        this.isTipologiePagamentiRemoveButtonVisible = isTipologiePagamentiRemoveButtonVisible;
        BindUtils.postNotifyChange(null, null, this, "isTipologiePagamentiRemoveButtonVisible");

    }
    public Boolean getIsTipologiePagamentiDettagliRemoveButtonVisible() {
        return isTipologiePagamentiDettagliRemoveButtonVisible;
    }
    public void setIsTipologiePagamentiDettagliRemoveButtonVisible(Boolean isTipologiePagamentiDettagliRemoveButtonVisible) {
        this.isTipologiePagamentiDettagliRemoveButtonVisible = isTipologiePagamentiDettagliRemoveButtonVisible;
    }

    public Boolean getIsTipologiePagamentiSaveButtonVisible() {
        return isTipologiePagamentiSaveButtonVisible;
    }

    public void setIsTipologiePagamentiSaveButtonVisible(Boolean tipologiePagamentiSaveButtonVisible) {
        isTipologiePagamentiSaveButtonVisible = tipologiePagamentiSaveButtonVisible;
        BindUtils.postNotifyChange(null, null, this, "isTipologiePagamentiSaveButtonVisible");
    }

    public Boolean getIsTipologiePagamentiDettagliSaveButtonVisible() {
        return isTipologiePagamentiDettagliSaveButtonVisible;
    }

    public void setIsTipologiePagamentiDettagliSaveButtonVisible(Boolean tipologiePagamentiDettagliSaveButtonVisible) {
        isTipologiePagamentiDettagliSaveButtonVisible = tipologiePagamentiDettagliSaveButtonVisible;
    }

    public Boolean getIsTipologiePagamentiDettagliAddButtonVisible() {
        return isTipologiePagamentiDettagliAddButtonVisible;
    }

    public Boolean getShowDeleted() {
        return showDeleted;
    }

    public void setShowDeleted(Boolean showDeleted) {
        this.showDeleted = showDeleted;
    }

    public List<TipologiePagamentiDettagli> getCurrentTipologiePagamentiDettagli() {
        return currentTipologiePagamentiDettagli;
    }

    public void setSelectedTipologiaPagamento(TipologiePagamenti selectedTipologiaPagamento) {
        this.selectedTipologiaPagamento = selectedTipologiaPagamento;
    }
}
