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
    List<CustomImpostazioniRow> impostazioni = new ArrayList<>();
    List<ImpostazioniValori> impostazioniValori;
    Boolean searchDisabled = true;
    Boolean prevDisabled = false;
    Boolean nextDisabled = false;

    String parametro;
    public Integer currentOccurrence = 1;
    public List<CustomImpostazioniRow> totalOccurrences = new ArrayList<>();
    private Integer selectedTab = 0;


    @Init
    public void init(@ContextParam(ContextType.COMPONENT) Window w) throws Exception {
        initImpostazioni();
        impostazioniValori = impostazioniValoriService.getImpostazioniValori();

    }

    @Command
    public void onSave() {
        try {
            impostazioniGridService.saveImpostazioniRows(impostazioni);
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
    @NotifyChange({"impostazioni", "searchDisabled", "totalOccurrences", "nextDisabled", "prevDisabled"})
    public void onParametroSearch() {
        totalOccurrences.clear();
        if (parametro != null && !parametro.isEmpty() && parametro.length() > 2) {
            highlightParametroInList(impostazioni, parametro);
        } else {
            resetHighlightedParametroInList(impostazioni);
        }
        if (!totalOccurrences.isEmpty()) {
            nextDisabled = false;
            prevDisabled = true;
        }
    }

    @Command
    @NotifyChange({"currentOccurrence", "nextDisabled", "prevDisabled", "impostazioni", "selectedTab"})
    public void onNextSearchOccurrence() {
        if (currentOccurrence < totalOccurrences.size()) {
            currentOccurrence++;
            currentOccurrenceHighlight();
        }
        nextDisabled = currentOccurrence >= totalOccurrences.size();
        prevDisabled = currentOccurrence <= 1;
        System.out.println(currentOccurrence + " " + totalOccurrences.size());
    }
    @Command
    @NotifyChange({"currentOccurrence", "nextDisabled", "prevDisabled", "impostazioni", "selectedTab"})
    public void onPrevSearchOccurrence() {
        if (currentOccurrence > 1) {
            currentOccurrence--;
            currentOccurrenceHighlight();
        }
        nextDisabled = false;
        prevDisabled = currentOccurrence <= 1;
    }
    private void highlightParametroInList(List<CustomImpostazioniRow> lista, String parametro) {
        int occurrenceCounter = 0;

        for (CustomImpostazioniRow row : lista) {
            String etichetta = row.getEtichettaCampo();
            if (etichetta.toLowerCase().contains(parametro.toLowerCase())) {
                occurrenceCounter++;
                row.setOccurrenceNumber(occurrenceCounter);
                totalOccurrences.add(row);
                if (row.getOccurrenceNumber() == 1) {
                    row.setCurrentOccurrence(true);
                }
                String highlighted = !row.getCurrentOccurrence() ? getHighlightedText(etichetta, parametro, "yellow") : getHighlightedText(etichetta, parametro, "orange");
                row.setHighlightedEtichettaCampo(highlighted);
            } else {
                row.setHighlightedEtichettaCampo(etichetta);
                row.setOccurrenceNumber(0);
                row.setCurrentOccurrence(false);
            }
        }
        System.out.println(totalOccurrences.size());
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


    public List<String> getFilteredImpostazioniValori(CustomImpostazioniRow each) {
        String codiceImpostazione = each.getCodice();
        return impostazioniValori.stream()
                .filter(valore -> valore.getCodiceImpostazione().equals(codiceImpostazione))
                .map(ImpostazioniValori::getValoreStringa)
                .collect(Collectors.toList());
    }

    public String getParametro() {
        return parametro;
    }

    public List<CustomImpostazioniRow> getImpostazioni() {
        return impostazioni;
    }

    public Boolean getSearchDisabled() {
        return searchDisabled;
    }

    public Boolean getPrevDisabled() {
        return prevDisabled;
    }

    public Boolean getNextDisabled() {
        return nextDisabled;
    }

    public Integer getCurrentOccurrence() {
        return currentOccurrence;
    }
    public void setCurrentOccurrence(Integer currentOccurrence) {
        this.currentOccurrence = currentOccurrence;
    }

    public void setImpostazioni(List<CustomImpostazioniRow> impostazioni) {
        this.impostazioni = impostazioni;
    }

    public List<CustomImpostazioniRow> getTotalOccurrences() {
        return totalOccurrences;
    }
    public Integer getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(Integer selectedTab) {
        this.selectedTab = selectedTab;
    }


    public void setTotalOccurrences(List<CustomImpostazioniRow> totalOccurrences) {
        this.totalOccurrences = totalOccurrences;
    }

    public void initImpostazioni() throws SQLException {
        impostazioni = impostazioniGridService.getImpostazioniRows();
        int altreImpostazioniCount = 0;
        for (int i = 0; i < impostazioni.size(); i++) {
            CustomImpostazioniRow row = impostazioni.get(i);
            row.setHighlightedEtichettaCampo(row.getEtichettaCampo());
            if (row.getCategoria().equals("ALTRE IMPOSTAZIONI")) {
                altreImpostazioniCount++;
                if (altreImpostazioniCount <= 6) {
                    row.setIsAltreImpostazioniPrimaColonna(true);
                } else {
                    row.setIsAltreImpostazioniSecondaColonna(true);
                }
            }
        }
    }

    public void currentOccurrenceHighlight() {
        for (CustomImpostazioniRow row: impostazioni) {
            String etichetta = row.getEtichettaCampo();
            if (row.getOccurrenceNumber().equals(currentOccurrence)) {
                String highlighted = getHighlightedText(etichetta, parametro, "orange");
                row.setHighlightedEtichettaCampo(highlighted);
                selectedTab = row.getTab();
            } else {
                String highlighted = getHighlightedText(etichetta, parametro, "yellow");
                row.setHighlightedEtichettaCampo(highlighted);
            }
        }
        System.out.println(currentOccurrence);
    }
}
