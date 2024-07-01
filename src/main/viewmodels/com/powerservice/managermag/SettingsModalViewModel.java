package com.powerservice.managermag;

import it.powerservice.managermag.ImpostazioniGridService;
import it.powerservice.managermag.ImpostazioniValori;
import it.powerservice.managermag.ImpostazioniValoriService;
import it.powerservice.managermag.customClass.CustomImpostazioniRow;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@VariableResolver(DelegatingVariableResolver.class)
@Getter
@Setter
public class SettingsModalViewModel {

    @WireVariable
    ImpostazioniGridService impostazioniGridService;
    @WireVariable
    ImpostazioniValoriService impostazioniValoriService;

    List<ImpostazioniValori> impostazioniValori;


    List<CustomImpostazioniRow> senzaNomeImpostazioni;
    List<CustomImpostazioniRow> modelliAggiuntiviImpostazioni;
    List<CustomImpostazioniRow> altreImpostazioni;


    @Init
    public void init(@ContextParam(ContextType.COMPONENT) Window w) throws Exception {
        var impostazioniRows = impostazioniGridService.getImpostazioniRows();
        senzaNomeImpostazioni = impostazioniRows.stream()
                .filter(row -> "SENZA NOME".equals(row.getCategoria()))
                .collect(Collectors.toList());
        modelliAggiuntiviImpostazioni = impostazioniRows.stream()
                .filter(row -> "MODELLI AGGIUNTIVI".equals(row.getCategoria()))
                .collect(Collectors.toList());
        altreImpostazioni = impostazioniRows.stream()
                .filter(row -> "ALTRE IMPOSTAZIONI".equals(row.getCategoria()))
                .collect(Collectors.toList());
        impostazioniValori = impostazioniValoriService.getImpostazioniValori();
    }

    @Command
    public void onSave() {
        try {
            List<CustomImpostazioniRow> impostazioniRow = new ArrayList<>();
            impostazioniRow.addAll(modelliAggiuntiviImpostazioni);
            impostazioniRow.addAll(senzaNomeImpostazioni);
            impostazioniRow.addAll(altreImpostazioni);
            impostazioniGridService.saveImpostazioniRows(impostazioniRow);
            Messagebox.show("Settings saved successfully.", "Information", Messagebox.OK, Messagebox.INFORMATION);
        } catch (Exception e) {
            e.printStackTrace();
            Messagebox.show("Error saving settings: " + e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
        }
    }

    @Command
    public void onCheck(@BindingParam("row") CustomImpostazioniRow row, @BindingParam("checked") boolean checked) {
        if (row != null) {
            row.setValoreStringa(checked ? "Y" : "N");
            BindUtils.postNotifyChange(null, null, row, "valoreStringa");
        }
    }



    @Command
    @NotifyChange("selectedValue")
    public void getValue() {
        System.out.println("ciao");
    }

    public List<ImpostazioniValori> getFilteredImpostazioniValori(CustomImpostazioniRow each) {
        String codiceImpostazione = each.getCodice();
        return impostazioniValori.stream()
                .filter(valore -> valore.getCodiceImpostazione().equals(codiceImpostazione))
                .collect(Collectors.toList());
    }
    public ImpostazioniValori getSelectedItem(CustomImpostazioniRow each) {
        List<ImpostazioniValori> filteredList = getFilteredImpostazioniValori(each);
        for (ImpostazioniValori valore : filteredList) {
            if (valore.getValoreStringa().equals(each.getValoreStringa())) {
                return valore;
            }
        }
        return null;
    }

    public ImpostazioniGridService getImpostazioniGridService() {
        return impostazioniGridService;
    }

    public void setImpostazioniGridService(ImpostazioniGridService impostazioniGridService) {
        this.impostazioniGridService = impostazioniGridService;
    }

    public ImpostazioniValoriService getImpostazioniValoriService() {
        return impostazioniValoriService;
    }

    public void setImpostazioniValoriService(ImpostazioniValoriService impostazioniValoriService) {
        this.impostazioniValoriService = impostazioniValoriService;
    }

    public List<ImpostazioniValori> getImpostazioniValori() {
        return impostazioniValori;
    }

    public void setImpostazioniValori(List<ImpostazioniValori> impostazioniValori) {
        this.impostazioniValori = impostazioniValori;
    }

    public List<CustomImpostazioniRow> getSenzaNomeImpostazioni() {
        return senzaNomeImpostazioni;
    }

    public void setSenzaNomeImpostazioni(List<CustomImpostazioniRow> senzaNomeImpostazioni) {
        this.senzaNomeImpostazioni = senzaNomeImpostazioni;
    }

    public List<CustomImpostazioniRow> getModelliAggiuntiviImpostazioni() {
        return modelliAggiuntiviImpostazioni;
    }

    public void setModelliAggiuntiviImpostazioni(List<CustomImpostazioniRow> modelliAggiuntiviImpostazioni) {
        this.modelliAggiuntiviImpostazioni = modelliAggiuntiviImpostazioni;
    }

    public List<CustomImpostazioniRow> getAltreImpostazioni() {
        return altreImpostazioni;
    }

    public void setAltreImpostazioni(List<CustomImpostazioniRow> altreImpostazioni) {
        this.altreImpostazioni = altreImpostazioni;
    }

    ImpostazioniValori test = new ImpostazioniValori("MET_PAG", "Contanti", "Contanti", null,null);

    public ImpostazioniValori getTest() {
        return test;
    }
}
