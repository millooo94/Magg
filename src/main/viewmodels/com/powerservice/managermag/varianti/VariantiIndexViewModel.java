package com.powerservice.managermag.varianti;

import com.powerservice.managermag.varianti.utilities.VariantiNewCloseListener;
import it.powerservice.managermag.Varianti;
import it.powerservice.managermag.VariantiService;
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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static it.powerservice.managermag.enums.VariantiType.CATEGORIA;

@VariableResolver(DelegatingVariableResolver.class)
public class VariantiIndexViewModel {

    @WireVariable
    VariantiService variantiService;

    private List<Varianti> variantiCategorie = new ArrayList<>();
    private List<Varianti> variantiTipologie = new ArrayList<>();
    private List<Varianti> variantiValori = new ArrayList<>();
    private Varianti selectedVarianteCategoria;
    private Varianti selectedVarianteTipologia;
    private Varianti selectedVarianteValore;
    private Varianti rightClickedVariante;
    private Boolean isVariantiTipologieAddButtonDisabled = true;
    private Boolean isVariantiValoriAddButtonDisabled = true;



    private Boolean variantiCategorieSearchDisabled = true;
    private Boolean variantiCategoriePrevDisabled = false;
    private Boolean variantiCategorieNextDisabled = false;
    private String variantiCategorieParametro;
    private Integer variantiCategorieCurrentSearchedOccurrence = 1;
    private List<Varianti> variantiCategorieTotalSearchedOccurrences = new ArrayList<>();
    private Boolean variantiTipologieSearchDisabled = true;
    private Boolean variantiTipologiePrevDisabled = false;
    private Boolean variantiTipologieNextDisabled = false;
    private String variantiTipologieParametro;
    private Integer variantiTipologieCurrentSearchedOccurrence = 1;
    private List<Varianti> variantiTipologieTotalSearchedOccurrences = new ArrayList<>();
    private Boolean variantiValoriSearchDisabled = true;
    private Boolean variantiValoriPrevDisabled = false;
    private Boolean variantiValoriNextDisabled = false;
    private String variantiValoriParametro;
    private Integer variantiValoriCurrentSearchedOccurrence = 1;
    private List<Varianti> variantiValoriTotalSearchedOccurrences = new ArrayList<>();
    private Boolean showDeletedVariantiCategorie = false;
    private Boolean showDeletedVariantiTipologie = false;
    private Boolean showDeletedVariantiValori = false;




    @Init
    public void init() throws SQLException {
        initVarianti();
        System.out.println(variantiCategorie);
    }

    @Command
    @NotifyChange({
            "selectedVarianteCategoria",
            "selectedVarianteTipologia",
            "selectedVarianteValore",
            "isVariantiTipologieAddButtonDisabled",
            "isVariantiValoriAddButtonDisabled",
            "variantiTipologie",
            "variantiValori"
    })
    public void onClickVariant(@BindingParam("variant") Varianti variant) throws SQLException {
        switch (variant.getTipo()) {
            case "C":
                variantiValori.clear();
                selectedVarianteCategoria = variant;
                variantiTipologie = variantiService.findChildrenById(selectedVarianteCategoria.getId());
                for (Varianti vt: variantiTipologie) {
                    vt.setHighlightedDescrizione(vt.getDescrizione());
                }
                isVariantiTipologieAddButtonDisabled = false;
                break;
            case "T":
                selectedVarianteTipologia = variant;
                variantiValori = variantiService.findChildrenById(selectedVarianteTipologia.getId());
                for (Varianti vv: variantiValori) {
                    vv.setHighlightedDescrizione(vv.getDescrizione());
                }
                isVariantiValoriAddButtonDisabled = false;
                break;
            case "V":
                selectedVarianteValore = variant;
                break;
        }
    }
    @Command
    public void onCreateVariant(@BindingParam("type") String type) {
        Map<String, Object> params = new HashMap<>() {{
            put("type", type);
        }};
        switch (type) {
            case "T":
                params.put("idVariantePartenza", selectedVarianteCategoria.getId());
                break;
            case "V":
                params.put("idVariantePartenza", selectedVarianteTipologia.getId());
                break;

        }
        VariantiNewViewModel.apriPopup(this, params).addEventListener(Events.ON_CLOSE, new VariantiNewCloseListener(this));
    }
    public void refresh() throws SQLException {
        initVarianti();
        if (selectedVarianteCategoria != null) {
            variantiTipologie = variantiService.findChildrenById(selectedVarianteCategoria.getId());
            for (Varianti vt: variantiTipologie) {
                vt.setHighlightedDescrizione(vt.getDescrizione());
            }
            if (selectedVarianteTipologia != null) {
                variantiValori = variantiService.findChildrenById(selectedVarianteTipologia.getId());
                for (Varianti vv: variantiValori) {
                    vv.setHighlightedDescrizione(vv.getDescrizione());
                }
            }
        }
        notifyVariants();
    }
    @Command
    public void onRightClick(@BindingParam("variant") Varianti variant) {
        rightClickedVariante = variant;
    }
    @Command
    @NotifyChange("rightClickedVariante")
    public void onUpdateVariant() {
        rightClickedVariante.setIsUpdating(true);
        notifyVariants();
    }
    @Command
    @NotifyChange("rightClickedVariante")
    public void onSaveVariantUpdate() throws SQLException {
        variantiService.updateVariant(rightClickedVariante);
        Notification.show("Salvato");
        rightClickedVariante.setIsUpdating(false);
    }
    @Command
    public void onDeleteVariant() throws Exception {
        variantiService.deleteById(rightClickedVariante.getId());
        refresh();
    }

    @Command
    @NotifyChange({
            "variantiCategorieSearchDisabled",
            "variantiTipologieSearchDisabled",
            "variantiValoriSearchDisabled",
    })
    public void setParametro(@BindingParam("type") String type, String parametro) {
        switch (type) {
            case "C":
                this.variantiCategorieParametro = parametro;
                variantiCategorieSearchDisabled = variantiCategorieParametro == null || parametro.length() <= 2;
                break;
            case "T":
                this.variantiTipologieParametro = parametro;
                variantiTipologieSearchDisabled = variantiTipologieParametro == null || parametro.length() <= 2;
                break;

            case "V":
                this.variantiValoriParametro = parametro;
                variantiValoriSearchDisabled = variantiValoriParametro == null || parametro.length() <= 2;
                break;
        }
    }
    @Command
    @NotifyChange({
            "variantiCategorie",
            "variantiTipologie",
            "variantiValori",
            "variantiCategorieSearchDisabled",
            "variantiTipologieSearchDisabled",
            "variantiValoriSearchDisabled",
            "variantiCategorieTotalSearchedOccurrences",
            "variantiTipologieTotalSearchedOccurrences",
            "variantiValoriTotalSearchedOccurrences",
            "variantiCategoriePrevDisabled",
            "variantiTipologiePrevDisabled",
            "variantiValoriPrevDisabled",
            "variantiCategorieNextDisabled",
            "variantiTipologieNextDisabled",
            "variantiValoriNextDisabled",
    })
    public void onParametroSearch(@BindingParam("type") String type) {
        List<Varianti> varianti = new ArrayList<>();
        switch (type) {
            case "C":
                varianti = variantiCategorie;
                variantiCategorieTotalSearchedOccurrences.clear();
                if (variantiCategorieParametro != null && !variantiCategorieParametro.isEmpty() && variantiCategorieParametro.length() > 2) {
                    highlightParametroInList(varianti, variantiCategorieParametro, variantiCategorieTotalSearchedOccurrences);
                    System.out.println(variantiCategorieTotalSearchedOccurrences.size());
                } else {
                    resetHighlightedParametroInList(varianti);
                }
                if (!variantiCategorieTotalSearchedOccurrences.isEmpty()) {
                    variantiCategorieNextDisabled = false;
                    variantiCategoriePrevDisabled = true;
                }
                break;
            case "T":
                varianti = variantiTipologie;
                variantiTipologieTotalSearchedOccurrences.clear();
                if (variantiTipologieParametro != null && !variantiTipologieParametro.isEmpty() && variantiTipologieParametro.length() > 2) {
                    highlightParametroInList(varianti, variantiTipologieParametro, variantiTipologieTotalSearchedOccurrences);
                } else {
                    resetHighlightedParametroInList(varianti);
                }
                if (!variantiTipologieTotalSearchedOccurrences.isEmpty()) {
                    variantiTipologieNextDisabled = false;
                    variantiTipologiePrevDisabled = true;
                }
                break;
            case "V":
                varianti = variantiValori;
                variantiValoriTotalSearchedOccurrences.clear();
                if (variantiValoriParametro != null && !variantiValoriParametro.isEmpty() && variantiValoriParametro.length() > 2) {
                    highlightParametroInList(varianti, variantiValoriParametro, variantiValoriTotalSearchedOccurrences);
                } else {
                    resetHighlightedParametroInList(varianti);
                }
                if (!variantiValoriTotalSearchedOccurrences.isEmpty()) {
                    variantiValoriNextDisabled = false;
                    variantiValoriPrevDisabled = true;
                }
                break;
        }

    }
    @Command
    @NotifyChange({
            "variantiCategorie",
            "variantiTipologie",
            "variantiValori",
            "variantiCategorieCurrentSearchedOccurrence",
            "variantiTipologieCurrentSearchedOccurrence",
            "variantiValoriCurrentSearchedOccurrence",
            "variantiCategoriePrevDisabled",
            "variantiTipologiePrevDisabled",
            "variantiValoriPrevDisabled",
            "variantiCategorieNextDisabled",
            "variantiTipologieNextDisabled",
            "variantiValoriNextDisabled",
    })
    public void onNextSearchOccurrence(@BindingParam("type") String type) {
        List<Varianti> varianti = new ArrayList<>();
        switch (type) {
            case "C":
                varianti = variantiCategorie;
                if (variantiCategorieCurrentSearchedOccurrence < variantiCategorieTotalSearchedOccurrences.size()) {
                    variantiCategorieCurrentSearchedOccurrence++;
                    currentOccurrenceHighlight(varianti, variantiCategorieCurrentSearchedOccurrence, variantiCategorieParametro);
                }
                variantiCategorieNextDisabled = variantiCategorieCurrentSearchedOccurrence >= variantiCategorieTotalSearchedOccurrences.size();
                variantiCategoriePrevDisabled = variantiCategorieCurrentSearchedOccurrence <= 1;
                break;
            case "T":
                varianti = variantiTipologie;
                if (variantiTipologieCurrentSearchedOccurrence < variantiTipologieTotalSearchedOccurrences.size()) {
                    variantiTipologieCurrentSearchedOccurrence++;
                    currentOccurrenceHighlight(varianti, variantiTipologieCurrentSearchedOccurrence, variantiTipologieParametro);
                }
                variantiTipologieNextDisabled = variantiTipologieCurrentSearchedOccurrence >= variantiTipologieTotalSearchedOccurrences.size();
                variantiTipologiePrevDisabled = variantiTipologieCurrentSearchedOccurrence <= 1;
                break;
            case "V":
                varianti = variantiValori;
                if (variantiValoriCurrentSearchedOccurrence < variantiValoriTotalSearchedOccurrences.size()) {
                    variantiValoriCurrentSearchedOccurrence++;
                    currentOccurrenceHighlight(varianti, variantiValoriCurrentSearchedOccurrence, variantiValoriParametro);
                }
                variantiValoriNextDisabled = variantiValoriCurrentSearchedOccurrence >= variantiValoriTotalSearchedOccurrences.size();
                variantiValoriPrevDisabled = variantiValoriCurrentSearchedOccurrence <= 1;
                break;
        }
    }
    @Command
    @NotifyChange({
            "variantiCategorie",
            "variantiTipologie",
            "variantiValori",
            "variantiCategorieCurrentSearchedOccurrence",
            "variantiTipologieCurrentSearchedOccurrence",
            "variantiValoriCurrentSearchedOccurrence",
            "variantiCategoriePrevDisabled",
            "variantiTipologiePrevDisabled",
            "variantiValoriPrevDisabled",
            "variantiCategorieNextDisabled",
            "variantiTipologieNextDisabled",
            "variantiValoriNextDisabled",
    })
    public void onPrevSearchOccurrence(@BindingParam("type") String type) {
        List<Varianti> varianti = new ArrayList<>();
        switch (type) {
            case "C":
                varianti = variantiCategorie;
                if (variantiCategorieCurrentSearchedOccurrence > 1) {
                    variantiCategorieCurrentSearchedOccurrence--;
                    currentOccurrenceHighlight(varianti, variantiCategorieCurrentSearchedOccurrence, variantiCategorieParametro);
                }
                variantiCategorieNextDisabled = false;
                variantiCategoriePrevDisabled = variantiCategorieCurrentSearchedOccurrence <= 1;
                break;
            case "T":
                varianti = variantiTipologie;
                if (variantiTipologieCurrentSearchedOccurrence > 1) {
                    variantiTipologieCurrentSearchedOccurrence--;
                    currentOccurrenceHighlight(varianti, variantiTipologieCurrentSearchedOccurrence, variantiTipologieParametro);
                }
                variantiTipologieNextDisabled = false;
                variantiTipologiePrevDisabled = variantiTipologieCurrentSearchedOccurrence <= 1;
                break;
            case "V":
                varianti = variantiValori;
                if (variantiValoriCurrentSearchedOccurrence > 1) {
                    variantiValoriCurrentSearchedOccurrence--;
                    currentOccurrenceHighlight(varianti, variantiValoriCurrentSearchedOccurrence, variantiValoriParametro);
                }
                variantiValoriNextDisabled = false;
                variantiValoriPrevDisabled = variantiValoriCurrentSearchedOccurrence <= 1;
                break;
        }
    }

    private void highlightParametroInList(List<Varianti> list, String parametro, List<Varianti> totalSearchedOccurrences) {
        int occurrenceCounter = 0;
        for (Varianti variant : list) {
            String descrizione = variant.getDescrizione();
            if (descrizione.toLowerCase().contains(parametro.toLowerCase())) {
                occurrenceCounter++;
                variant.setOccurrenceNumber(occurrenceCounter);
                totalSearchedOccurrences.add(variant);
                System.out.println(totalSearchedOccurrences.size() > 1);
            } else {
                variant.setHighlightedDescrizione(descrizione);
                variant.setOccurrenceNumber(0);
                variant.setCurrentOccurrence(false);
            }
        }
        for (Varianti variant : totalSearchedOccurrences) {
            String descrizione = variant.getDescrizione();
            if (totalSearchedOccurrences.size() == 1) {
                variant.setCurrentOccurrence(false);
                variant.setHighlightedDescrizione(getHighlightedText(descrizione, parametro, "yellow"));
            } else {
                variant.setCurrentOccurrence(variant.getOccurrenceNumber() == 1);
                String highlighted = variant.getIsCurrentOccurrence() ? getHighlightedText(descrizione, parametro, "orange") : getHighlightedText(descrizione, parametro, "yellow");
                variant.setHighlightedDescrizione(highlighted);
            }
        }
    }

    public void currentOccurrenceHighlight(List<Varianti> varianti, Integer currentSearchedOccurrence, String parametro) {
        for (Varianti variant: varianti) {
            String descrizione = variant.getDescrizione();
            if (variant.getOccurrenceNumber().equals(currentSearchedOccurrence)) {
                String highlighted = getHighlightedText(descrizione, parametro, "orange");
                variant.setHighlightedDescrizione(highlighted);
            } else {
                String highlighted = getHighlightedText(descrizione, parametro, "yellow");
                variant.setHighlightedDescrizione(highlighted);
            }
        }
    }
    private void resetHighlightedParametroInList(List<Varianti> list) {
        for (Varianti variant : list) {
            variant.setHighlightedDescrizione(variant.getDescrizione());
        }
    }
    private String getHighlightedText(String text, String parametro, String color) {
        Pattern pattern = Pattern.compile("(" + Pattern.quote(parametro) + ")", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll("<span style='background-color: " + color + ";'>$1</span>");
    }

    @NotifyChange({
        "variantiCategorie"
    })
    public void initVarianti() throws SQLException {
        variantiCategorie = filterNotDeleted(variantiService.findByType(CATEGORIA.getLabel()));
        for (Varianti variant : variantiCategorie) {
            variant.setHighlightedDescrizione(variant.getDescrizione());
        }
    }

    @Command
    @NotifyChange({
            "variantiCategorie",
            "variantiTipologie",
            "variantiValori",
            "showDeletedVariantiCategorie",
            "showDeletedVariantiCategorieButtonLabel",
            "showDeletedVariantiTipologieButtonLabel",
            "showDeletedVariantiValoriButtonLabel"
    })
    public void showDeletedVariants(@BindingParam("type") String type) throws SQLException {
        switch (type) {
            case "C":
                showDeletedVariantiCategorie = !showDeletedVariantiCategorie;
                if (showDeletedVariantiCategorie) {
                    variantiCategorie = variantiService.findByType(CATEGORIA.getLabel());
                    for (Varianti vc: variantiCategorie) {
                        vc.setHighlightedDescrizione(vc.getDescrizione());
                    }
                } else {
                    variantiCategorie =  filterNotDeleted(variantiService.findByType(CATEGORIA.getLabel()));
                    for (Varianti vc: variantiCategorie) {
                        vc.setHighlightedDescrizione(vc.getDescrizione());
                    }
                }
                break;
            case "T":
                showDeletedVariantiTipologie = !showDeletedVariantiTipologie;
                if (showDeletedVariantiTipologie) {
                    variantiTipologie = variantiService.findChildrenById(selectedVarianteCategoria.getId());
                    for (Varianti vc: variantiTipologie) {
                        vc.setHighlightedDescrizione(vc.getDescrizione());
                    }
                } else {
                    variantiTipologie =  filterNotDeleted(variantiService.findChildrenById(selectedVarianteCategoria.getId()));
                    for (Varianti vc: variantiTipologie) {
                        vc.setHighlightedDescrizione(vc.getDescrizione());
                    }
                }
                break;
            case "V":
                showDeletedVariantiValori = !showDeletedVariantiValori;
                if (showDeletedVariantiValori) {
                    variantiValori = variantiService.findChildrenById(selectedVarianteTipologia.getId());
                    for (Varianti vc: variantiValori) {
                        vc.setHighlightedDescrizione(vc.getDescrizione());
                    }
                } else {
                    variantiValori =  filterNotDeleted(variantiService.findChildrenById(selectedVarianteTipologia.getId()));
                    for (Varianti vc: variantiValori) {
                        vc.setHighlightedDescrizione(vc.getDescrizione());
                    }
                }
                break;
        }

    }
    public String getShowDeletedVariantiCategorieButtonLabel() {
        return showDeletedVariantiCategorie ? "Nascondi Eliminati" : "Mostra Eliminati";
    }
    public String getShowDeletedVariantiTipologieButtonLabel() {
        return showDeletedVariantiTipologie ? "Nascondi Eliminati" : "Mostra Eliminati";
    }
    public String getShowDeletedVariantiValoriButtonLabel() {
        return showDeletedVariantiValori ? "Nascondi Eliminati" : "Mostra Eliminati";
    }

    public Varianti getSelectedVarianteCategoria() {
        return selectedVarianteCategoria;
    }

    public Boolean getVariantiCategorieSearchDisabled() {
        return variantiCategorieSearchDisabled;
    }

    public Boolean getVariantiCategoriePrevDisabled() {
        return variantiCategoriePrevDisabled;
    }

    public Boolean getVariantiCategorieNextDisabled() {
        return variantiCategorieNextDisabled;
    }

    public String getVariantiCategorieParametro() {
        return variantiCategorieParametro;
    }

    public Integer getVariantiCategorieCurrentSearchedOccurrence() {
        return variantiCategorieCurrentSearchedOccurrence;
    }

    public List<Varianti> getVariantiCategorieTotalSearchedOccurrences() {
        return variantiCategorieTotalSearchedOccurrences;
    }

    public Boolean getVariantiTipologieSearchDisabled() {
        return variantiTipologieSearchDisabled;
    }

    public Boolean getVariantiTipologiePrevDisabled() {
        return variantiTipologiePrevDisabled;
    }

    public Boolean getVariantiTipologieNextDisabled() {
        return variantiTipologieNextDisabled;
    }

    public String getVariantiTipologieParametro() {
        return variantiTipologieParametro;
    }

    public Integer getVariantiTipologieCurrentSearchedOccurrence() {
        return variantiTipologieCurrentSearchedOccurrence;
    }

    public List<Varianti> getVariantiTipologieTotalSearchedOccurrences() {
        return variantiTipologieTotalSearchedOccurrences;
    }

    public Boolean getVariantiValoriSearchDisabled() {
        return variantiValoriSearchDisabled;
    }

    public Boolean getVariantiValoriPrevDisabled() {
        return variantiValoriPrevDisabled;
    }

    public Boolean getVariantiValoriNextDisabled() {
        return variantiValoriNextDisabled;
    }

    public String getVariantiValoriParametro() {
        return variantiValoriParametro;
    }

    public Integer getVariantiValoriCurrentSearchedOccurrence() {
        return variantiValoriCurrentSearchedOccurrence;
    }

    public List<Varianti> getVariantiValoriTotalSearchedOccurrences() {
        return variantiValoriTotalSearchedOccurrences;
    }

    public Varianti getRightClickedVariante() {
        return rightClickedVariante;
    }

    public List<Varianti> getVariantiCategorie() {
        return variantiCategorie;

    }

    private List<Varianti> filterNotDeleted(List<Varianti> list) {
        return list.stream()
                .filter(varianti -> !varianti.getEliminato())
                .collect(Collectors.toList());
    }

    public List<Varianti> getVariantiTipologie() {
        return showDeletedVariantiTipologie ? variantiTipologie : filterNotDeleted(variantiTipologie);

    }
    public List<Varianti> getVariantiValori() {
        return showDeletedVariantiValori ? variantiValori : filterNotDeleted(variantiValori);
    }

    public Boolean getIsVariantiTipologieAddButtonDisabled() {
        return isVariantiTipologieAddButtonDisabled;
    }

    public Boolean getIsVariantiValoriAddButtonDisabled() {
        return isVariantiValoriAddButtonDisabled;
    }

    public boolean isVariantiTipologieEmpty() {
        return variantiTipologie.isEmpty();
    }

    public VariantiService getVariantiService() {
        return variantiService;
    }
    public void setVariantiCategorieParametro(String variantiCategorieParametro) {
        this.variantiCategorieParametro = variantiCategorieParametro;
    }

    public void setVariantiTipologieParametro(String variantiTipologieParametro) {
        this.variantiTipologieParametro = variantiTipologieParametro;
    }

    public void setVariantiValoriParametro(String variantiValoriParametro) {
        this.variantiValoriParametro = variantiValoriParametro;
    }

    public void setVariantiService(VariantiService variantiService) {
        this.variantiService = variantiService;
    }

    public Boolean getShowDeletedVariantiCategorie() {
        return showDeletedVariantiCategorie;
    }

    public void setShowDeletedVariantiCategorie(Boolean showDeletedVariantiCategorie) {
        this.showDeletedVariantiCategorie = showDeletedVariantiCategorie;
    }

    public Boolean getShowDeletedVariantiTipologie() {
        return showDeletedVariantiTipologie;
    }

    public void setShowDeletedVariantiTipologie(Boolean showDeletedVariantiTipologie) {
        this.showDeletedVariantiTipologie = showDeletedVariantiTipologie;
    }

    public Boolean getShowDeletedVariantiValori() {
        return showDeletedVariantiValori;
    }

    public void setShowDeletedVariantiValori(Boolean showDeletedVariantiValori) {
        this.showDeletedVariantiValori = showDeletedVariantiValori;
    }

    public void notifyVariants() {
        BindUtils.postNotifyChange(null, null, this, "variantiCategorie");
        BindUtils.postNotifyChange(null, null, this, "variantiTipologie");
        BindUtils.postNotifyChange(null, null, this, "variantiValori");
    }


    @Command
    public void test() {
        System.out.println("ceeeeeeeeeeeee");
    }
}
