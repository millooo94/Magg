package com.powerservice.managermag.tipologiePagamenti;

import com.powerservice.managermag.marche.MarcheIndexViewModel;
import com.powerservice.managermag.tipologiePagamenti.utilities.PagFattPa;
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

import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class TipologiePagamentiNewViewModel {
    private Window window;
    private static TipologiePagamentiIndexViewModel tipologiePagamentiIndexViewModel;

    @WireVariable
    TipologiePagamentiService tipologiePagamentiService;
    @WireVariable
    BancheService bancheService;
    TipologiePagamenti tipologiaPagamentoToSave = new TipologiePagamenti();
    List<Banche> banche = new ArrayList<>();
    List<PagFattPa> modPagFattPa = new ArrayList<>();
    List<PagFattPa> condPagFattPa = new ArrayList<>();

    Banche selectedBanca = new Banche();
    PagFattPa selectedModPagFattPa = new PagFattPa(null, null);
    PagFattPa selectedCondPagFattPa = new PagFattPa(null, null);


    @Init
    public void init() {
        banche = bancheService.getBanche();
        initPagFattPaLists();
    }


    static Window apriPopup(TipologiePagamentiIndexViewModel parentModel) {
        tipologiePagamentiIndexViewModel = parentModel;
        return (Window) Executions.createComponents(
                "tipologiePagamenti/tipologiePagamenti.new.zul", null, null);
    }

    @Command
    @NotifyChange("tipologiaPagamentoToSave")
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }

    @Command
    @NotifyChange({"tipologiaPagamentoToSave"})
    public void onSelectBanca() {
        tipologiaPagamentoToSave.setSelectedBanca(selectedBanca);
        tipologiaPagamentoToSave.setIdBanca(selectedBanca.getId());
    }

    @Command
    @NotifyChange({"tipologiaPagamentoToSave"})
    public void onSelectModPagFattPa() {
        tipologiaPagamentoToSave.setSelectedModPagFattPA(selectedModPagFattPa);
        tipologiaPagamentoToSave.setModPagFattPA(selectedModPagFattPa.getDescrizione());
    }
    @Command
    @NotifyChange({"tipologiaPagamentoToSave"})
    public void onSelectCondPagFattPa() {
        tipologiaPagamentoToSave.setSelectedCondPagFattPA(selectedCondPagFattPa);
        tipologiaPagamentoToSave.setCondPagFattPA(selectedCondPagFattPa.getDescrizione());

    }

    @Command
    @NotifyChange({"tipologiaPagamentoToSave"})
    public void onSaveTipologiaPagamento() {
        try {
            tipologiePagamentiService.onSaveTipologiaPagamento(tipologiaPagamentoToSave);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (window != null) {
                window.detach();
            }
        }
        tipologiePagamentiIndexViewModel.refresh();
        tipologiePagamentiIndexViewModel.setSelectedTipologiaPagamento(null);
        tipologiePagamentiIndexViewModel.setIsTipologiePagamentiRemoveButtonVisible(false);
        tipologiePagamentiIndexViewModel.setIsTipologiePagamentiSaveButtonVisible(false);
        Notification.show("Salvato");
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

    public TipologiePagamenti getTipologiaPagamentoToSave() {
        return tipologiaPagamentoToSave;
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

    public PagFattPa getSelectedModPagFattPa() {
        return selectedModPagFattPa;
    }

    public PagFattPa getSelectedCondPagFattPa() {
        return selectedCondPagFattPa;
    }

    public void setSelectedBanca(Banche selectedBanca) {
        this.selectedBanca = selectedBanca;
    }

    public void setSelectedModPagFattPa(PagFattPa selectedModPagFattPa) {
        this.selectedModPagFattPa = selectedModPagFattPa;
    }

    public void setSelectedCondPagFattPa(PagFattPa selectedCondPagFattPa) {
        this.selectedCondPagFattPa = selectedCondPagFattPa;
    }
}
