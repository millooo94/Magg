package com.powerservice.managermag;

import it.powerservice.managermag.ImpostazioniGridService;
import it.powerservice.managermag.ImpostazioniValori;
import it.powerservice.managermag.ImpostazioniValoriService;
import it.powerservice.managermag.customClass.CustomImpostazioniRow;
import it.powerservice.managermag.enums.TipoImpostazioni;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@VariableResolver(DelegatingVariableResolver.class)
public class SettingsModalViewModel {
    @WireVariable
    ImpostazioniGridService impostazioniGridService;
    @WireVariable
    ImpostazioniValoriService impostazioniValoriService;
    List<ImpostazioniValori> impostazioniValori;
    List<CustomImpostazioniRow> senzaNomeImpostazioni;
    List<CustomImpostazioniRow> modelliAggiuntiviImpostazioni;
    List<CustomImpostazioniRow> altreImpostazioni;
    List<CustomImpostazioniRow> primaColonnaAltreImpostazioni ;
    List<CustomImpostazioniRow> secondaColonnaAltreImpostazioni ;

    String parametro;
    Boolean searchDisabled = true;
    public List<String> totalOccurrences = new ArrayList<>();
    public Short currentOccurrence = 1;
    private int currentGlobalIndex = -1;

    public boolean isSearchDisabled() {
        return parametro == null || parametro.length() < 3;
    }

    @Init
    public void init(@ContextParam(ContextType.COMPONENT) Window w) throws Exception {
        impostazioniValori = impostazioniValoriService.getImpostazioniValori();
        initImpostazioniLists();
        System.out.println("SIZE ==> " + totalOccurrences.size());
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
    @NotifyChange({"searchDisabled"})
    public void setParametro(String parametro) {
        this.parametro = parametro;
        searchDisabled = parametro == null || parametro.length() <= 2;
    }
    @Command
    @NotifyChange({"senzaNomeImpostazioni", "modelliAggiuntiviImpostazioni", "primaColonnaAltreImpostazioni", "secondaColonnaAltreImpostazioni", "searchDisabled", "totalOccurrences"})
    public void onParametroSearch() {
        totalOccurrences.clear();
        currentGlobalIndex = -1;
        if (parametro != null && !parametro.isEmpty() && parametro.length() > 2) {
            highlightParametroInList(senzaNomeImpostazioni, parametro);
            highlightParametroInList(modelliAggiuntiviImpostazioni, parametro);
            highlightParametroInList(primaColonnaAltreImpostazioni, parametro);
            highlightParametroInList(secondaColonnaAltreImpostazioni, parametro);
        } else {
            resetHighlightedParametroInList(senzaNomeImpostazioni);
            resetHighlightedParametroInList(modelliAggiuntiviImpostazioni);
            resetHighlightedParametroInList(primaColonnaAltreImpostazioni);
            resetHighlightedParametroInList(secondaColonnaAltreImpostazioni);
        }
    }

    @Command
    @NotifyChange("currentOccurrence")
    public void onPrevSearchOccurrence() {
        if (currentOccurrence > 1) {
            currentOccurrence--;
        }
    }
    @Command
    @NotifyChange({"currentOccurrence", "currentGlobalIndex"})
    public void onNextSearchOccurrence() {
        if (currentOccurrence <= totalOccurrences.size() - 1) {
            currentGlobalIndex = -1;
            currentOccurrence++;
        }
    }

    private void highlightParametroInList(List<CustomImpostazioniRow> lista, String parametro) {
        int occurrenceCount = 0;
        for (CustomImpostazioniRow row : lista) {
            String etichetta = row.getEtichettaCampo();
            if (etichetta.toLowerCase().contains(parametro.toLowerCase())) {
                occurrenceCount++;
            }
        }
        if (occurrenceCount > 1) {
            int currentOccurrence = 0;
            for (CustomImpostazioniRow row : lista) {
                String etichetta = row.getEtichettaCampo();
                if (etichetta.toLowerCase().contains(parametro.toLowerCase())) {
                    currentOccurrence++;
                    if (currentOccurrence == 1) {
                        String highlighted = getHighlightedText(etichetta, parametro, "orange");
                        row.setHighlightedEtichettaCampo(highlighted);
                        totalOccurrences.add(highlighted);
                    } else {
                        String highlighted = getHighlightedText(etichetta, parametro, "yellow");
                        row.setHighlightedEtichettaCampo(highlighted);
                        totalOccurrences.add(highlighted);
                    }
                } else {
                    row.setHighlightedEtichettaCampo(etichetta);
                }
            }
        } else if (occurrenceCount == 1) {
            // Se c'è solo una occorrenza, evidenziala in giallo
            for (CustomImpostazioniRow row : lista) {
                String etichetta = row.getEtichettaCampo();
                if (etichetta.toLowerCase().contains(parametro.toLowerCase())) {
                    String highlighted = getHighlightedText(etichetta, parametro, "yellow");
                    row.setHighlightedEtichettaCampo(highlighted);
                    totalOccurrences.add(highlighted);
                } else {
                    row.setHighlightedEtichettaCampo(etichetta);
                }
            }
        } else {
            // Se non ci sono occorrenze, mantieni l'etichetta originale
            for (CustomImpostazioniRow row : lista) {
                String etichetta = row.getEtichettaCampo();
                row.setHighlightedEtichettaCampo(etichetta);
            }
        }
    }

    private void resetHighlightedParametroInList(List<CustomImpostazioniRow> lista) {
        for (CustomImpostazioniRow row : lista) {
            row.setHighlightedEtichettaCampo(row.getEtichettaCampo());
        }
    }

    private String getHighlightedText(String text, String parametro, String color) {
        Pattern pattern = Pattern.compile("(" + Pattern.quote(parametro) + ")", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll("<span style='background-color: " + color + ";'>$1</span>");
    }

    private void resetGlobalIndex() {
        currentGlobalIndex = -1;
    }

    public List<String> getFilteredImpostazioniValori(CustomImpostazioniRow each) {
        String codiceImpostazione = each.getCodice();
        return impostazioniValori.stream()
                .filter(valore -> valore.getCodiceImpostazione().equals(codiceImpostazione))
                .map(ImpostazioniValori::getValoreStringa)
                .collect(Collectors.toList());
    }

    public void initImpostazioniLists() throws SQLException {
        var impostazioniRows = impostazioniGridService.getImpostazioniRows();
        senzaNomeImpostazioni = impostazioniRows.stream()
                .filter(row -> TipoImpostazioni.SENZANOME.getValue().equals(row.getCategoria()))
                .collect(Collectors.toList());
        modelliAggiuntiviImpostazioni = impostazioniRows.stream()
                .filter(row -> TipoImpostazioni.MODELLIAGGIUNTIVI.getValue().equals(row.getCategoria()))
                .collect(Collectors.toList());
        altreImpostazioni = impostazioniRows.stream()
                .filter(row -> TipoImpostazioni.ALTREIMPOSTAZIONI.getValue().equals(row.getCategoria()))
                .collect(Collectors.toList());
        primaColonnaAltreImpostazioni = altreImpostazioni.stream()
                .limit(7)
                .collect(Collectors.toList());
        secondaColonnaAltreImpostazioni = altreImpostazioni.stream()
                .skip(7)
                .collect(Collectors.toList());

        for (CustomImpostazioniRow row : senzaNomeImpostazioni) {
            row.setHighlightedEtichettaCampo(row.getEtichettaCampo());
        }
        for (CustomImpostazioniRow row : modelliAggiuntiviImpostazioni) {
            row.setHighlightedEtichettaCampo(row.getEtichettaCampo());
        }
        for (CustomImpostazioniRow row : primaColonnaAltreImpostazioni) {
            row.setHighlightedEtichettaCampo(row.getEtichettaCampo());
        }
        for (CustomImpostazioniRow row : secondaColonnaAltreImpostazioni) {
            row.setHighlightedEtichettaCampo(row.getEtichettaCampo());
        }
    }
    public List<CustomImpostazioniRow> getSenzaNomeImpostazioni() {
        return senzaNomeImpostazioni;
    }
    public List<CustomImpostazioniRow> getModelliAggiuntiviImpostazioni() {
        return modelliAggiuntiviImpostazioni;
    }
    public List<CustomImpostazioniRow> getAltreImpostazioni() {
        return altreImpostazioni;
    }
    public List<CustomImpostazioniRow> getPrimaColonnaAltreImpostazioni() {
        return primaColonnaAltreImpostazioni;
    }
    public List<CustomImpostazioniRow> getSecondaColonnaAltreImpostazioni() {
        return secondaColonnaAltreImpostazioni;
    }
    public String getParametro() {
        return parametro;
    }
    public List<String> getTotalOccurrences() {
        return totalOccurrences;
    }
    public void setTotalOccurrences(List<String> totalOccurrences) {
        this.totalOccurrences = totalOccurrences;
    }
    public Short getCurrentOccurrence() {
        return currentOccurrence;
    }
    public void setCurrentOccurrence(Short currentOccurrence) {
        this.currentOccurrence = currentOccurrence;
    }
}
