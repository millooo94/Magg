package com.powerservice.managermag.anagrafiche;

import com.powerservice.managermag.anagrafiche.utilities.General;
import com.powerservice.managermag.tipologiePagamenti.TipologiePagamentiIndexViewModel;
import it.powerservice.managermag.*;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class AnagraficheMonoViewModel {
    @WireVariable
    private AnagraficheService anagraficheService;
    @WireVariable
    private DizionariService dizionariService;
    private Window window;
    private static AnagraficheIndexViewModel anagraficheIndexViewModel;
    private Boolean isEditModal = false;
    private Boolean isCreateModal = false;
    private Anagrafiche anagraficaToSave = null;
    private String title = null;
    private String anagraficaExtendedType = null;
    private List<String> status = new ArrayList<>();
    private List<String> subCategorie = new ArrayList<>();
    private  List<String> sesso = new ArrayList<>(Arrays.asList("M", "F"));
    private  List<String> ceeExtraCee = new ArrayList<>(Arrays.asList("CEE", "Extra CEE"));
    private List<String> stati = new ArrayList<>();
    private Integer selectedStatusIndex = 0;
    private Integer selectedSubcategoriaIndex = 0;
    private Integer selectedSessoIndex = 0;
    private Integer selectedStatoIndex = 0;
    private String selectedCeeExtraCee = "";

    @Init
    private void init() {
        Map<?, ?> args = Executions.getCurrent().getArg();
        anagraficaToSave = (Anagrafiche) args.get("anagraficaToSave");
        setAnagraficaExtendedType((String) args.get("tipoAnagrafica"));
        initModal((String) args.get("monoType"));
        initStatus();
        initSubcategorie();
        initCeeExtraCee();
        initSesso();
        stati = General.getStatiDelMondo();
    }


    public static Window apriPopup(AnagraficheIndexViewModel parentModel, Map<String, Object> params) {
        anagraficheIndexViewModel = parentModel;
        Window window = (Window) Executions.createComponents(
                "anagrafiche/anagrafiche.mono.zul", null, params);
        return window;
    }

    @Command
    @NotifyChange("tipologiaPagamentoToSave")
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }


    public  void initStatus() {
        var res = dizionariService.getDizionariFromCategoria("STATUS_ANAG");
        status.add("(Nessuno)");
        for (Dizionari d: res) {
            status.add(d.getCodice());
            if (anagraficaToSave.getStatus() != null && anagraficaToSave.getStatus().equals(d.getCodice())) {
                selectedStatusIndex = status.indexOf(d.getCodice());
            }
        }
    }
    public  void initSubcategorie() {
        var res = dizionariService.getDizionariFromCategoria("SUBCAT_ANAG");
        status.add("(Nessuno)");
        for (Dizionari d: res) {
            subCategorie.add(d.getDescrizione());
            if (anagraficaToSave.getSubCategoria() != null && anagraficaToSave.getSubCategoria().equals(d.getDescrizione())) {
                selectedSubcategoriaIndex = subCategorie.indexOf(d.getDescrizione());
            }
        }
    }
    public  void initSesso() {
        for (String s: sesso) {
            if (anagraficaToSave.getSesso() != null && anagraficaToSave.getSesso().equals(s)) {
                selectedSessoIndex = sesso.indexOf(s);
            }
        }
    }
    public  void initCeeExtraCee() {
        if (!anagraficaToSave.getCeeExtraCee()) {
            selectedCeeExtraCee = "CEE";
        } else {
            selectedCeeExtraCee = "Extra CEE";
        }
        System.out.println(selectedCeeExtraCee);
    }

    @Command
    public void onCheckCeeExtraCee() {
        switch (selectedCeeExtraCee) {
            case "CEE":
                anagraficaToSave.setCeeExtraCee(false);
                break;
            case "EXTRA CEE":
                anagraficaToSave.setCeeExtraCee(true);
                break;
        }

    }


    public Anagrafiche getAnagraficaToSave() {
        return anagraficaToSave;
    }

    public String getTitle() {
        return title;
    }

    public String getAnagraficaExtendedType() {
        return anagraficaExtendedType;
    }

    public Boolean getIsEditModal() {
        return isEditModal;
    }

    public Boolean getIsCreateModal() {
        return isCreateModal;
    }

    public List<String> getStatus() {
        return status;
    }

    public List<String> getSubCategorie() {
        return subCategorie;
    }

    public List<String> getCeeExtraCee() {
        return ceeExtraCee;
    }

    public List<String> getSesso() {
        return sesso;
    }

    public List<String> getStati() {
        return stati;
    }

    public Integer getSelectedStatusIndex() {
        return selectedStatusIndex;
    }

    public Integer getSelectedSubcategoriaIndex() {
        return selectedSubcategoriaIndex;
    }

    public Integer getSelectedSessoIndex() {
        return selectedSessoIndex;
    }

    public Integer getSelectedStatoIndex() {
        return selectedStatoIndex;
    }

    public void setAnagraficheService(AnagraficheService anagraficheService) {
        this.anagraficheService = anagraficheService;
    }

    public void setSelectedStatusIndex(Integer selectedStatusIndex) {
        this.selectedStatusIndex = selectedStatusIndex;
    }

    public String getSelectedCeeExtraCee() {
        return selectedCeeExtraCee;
    }

    public void setSelectedCeeExtraCee(String selectedCeeExtraCee) {
        this.selectedCeeExtraCee = selectedCeeExtraCee;
    }

    public void setSelectedSubcategoriaIndex(Integer selectedSubcategoriaIndex) {
        this.selectedSubcategoriaIndex = selectedSubcategoriaIndex;
    }

    public void setSelectedSessoIndex(Integer selectedSessoIndex) {
        this.selectedSessoIndex = selectedSessoIndex;
    }

    public void setSelectedStatoIndex(Integer selectedStatoIndex) {
        this.selectedStatoIndex = selectedStatoIndex;
    }

    public void initModal(String monoType) {
        switch (monoType) {
            case "EDIT":
                title = "Modifica Anagrafica";
                isEditModal = true;
                break;
            case "CREATE":
                title = "Nuova Anagrafica";
                isCreateModal = true;
                break;
        }
    }

    public void setAnagraficaExtendedType(String tipoAnagrafica) {
        switch (tipoAnagrafica) {
            case "C":
                anagraficaExtendedType = "Cliente";
                break;
            case "F":
                anagraficaExtendedType = "Fornitore";
                break;
            case "T":
                anagraficaExtendedType = "Trasportatore";
                break;
            case "A":
                anagraficaExtendedType = "Agente";
                break;
            case "P":
                anagraficaExtendedType = "Personal";
                break;
        }
    }
}

