package com.powerservice.managermag;

import it.powerservice.managermag.ImpostazioniGridService;
import it.powerservice.managermag.ImpostazioniValori;
import it.powerservice.managermag.ImpostazioniValoriService;
import it.powerservice.managermag.customClass.CustomImpostazioniRow;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.List;
import java.util.stream.Collectors;

@VariableResolver(DelegatingVariableResolver.class)
public class SettingsModalViewModel {

    @WireVariable
    ImpostazioniGridService impostazioniGridService;
    @WireVariable
    ImpostazioniValoriService impostazioniValoriService;

    List<CustomImpostazioniRow> impostazioniRows;
    List<ImpostazioniValori> impostazioniValori;

    public List<CustomImpostazioniRow> getImpostazioniRows() {
        return impostazioniRows;
    }

    public void setImpostazioniRows(List<CustomImpostazioniRow> impostazioniRows) {
        this.impostazioniRows = impostazioniRows;
    }

    public List<ImpostazioniValori> getImpostazioniValori() {
        return impostazioniValori;
    }

    public void setImpostazioniValori(List<ImpostazioniValori> impostazioniValori) {
        this.impostazioniValori = impostazioniValori;
    }

    public List<ImpostazioniValori> getFilteredImpostazioniValori(CustomImpostazioniRow row) {


        var list = impostazioniValoriService.getImpostazioniValori(row.getCodice());
        System.out.println(row.getCodice());


        /*return impostazioniValori.stream()
                .filter(valore -> valore.getCodiceImpostazione().equals(row.getCodice()))
                .collect(Collectors.toList());*/
      for (ImpostazioniValori iv: list) {
            System.out.println(iv.getValoreStringa());
        }

        return impostazioniValoriService.getImpostazioniValori(row.getCodice());

    }

    @Init
    public void init(@ContextParam(ContextType.COMPONENT) Window w) throws Exception {
        impostazioniRows = impostazioniGridService.getImpostazioniRows();
        //impostazioniValori = impostazioniValoriService.getImpostazioniValori();
    }

    /*public void scriviValoreDropdown(@BindingParam("row") CustomImpostazioniRow row,@BindingParam("valoreScelto") String valoreScelto) {
        row.setValoreStringa(valoreScelto);
    }*/

    @Command
    public void onSave() {
        try {
            impostazioniGridService.saveImpostazioniRows(impostazioniRows);
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
    public void onSelect(@BindingParam("row") CustomImpostazioniRow row, @BindingParam("selected") String selected) {
        if (row != null) {
            row.setValoreStringa(selected);
            BindUtils.postNotifyChange(null, null, row, "selected");
        }
    }


}
