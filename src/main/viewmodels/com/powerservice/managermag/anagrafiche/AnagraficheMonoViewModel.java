package com.powerservice.managermag.anagrafiche;

import com.powerservice.managermag.IndexViewModel;
import com.powerservice.managermag.anagrafiche.utilities.General;
import com.powerservice.managermag.datiAzienda.utilities.DatiAziendaListItem;
import it.powerservice.managermag.*;
import it.powerservice.managermag.customClass.CodDesc;
import it.powerservice.managermag.customClass.GridColumn;
import it.powerservice.managermag.customClass.TabRef;
import it.powerservice.managermag.utilities.PropertiesReader;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;
import org.zkoss.zul.Window;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;

@VariableResolver(DelegatingVariableResolver.class)
public class AnagraficheMonoViewModel extends SelectorComposer<Window> {
    @WireVariable
    private AnagraficheService anagraficheService;
    @WireVariable
    private DizionariService dizionariService;
    @WireVariable
    private IndirizziService indirizziService;
    @WireVariable
    private NazioniService nazioniService;
    @WireVariable
    private ComuniService comuniService;
    @Wire("#stars-container")
    private Div starsContainer = new Div();
    @Wire("#main-tabs")
    private Tabs mainTabs;
    @Wire("#secondary-tabs")
    private Tabs secondaryTabs;

    private String monoType;

    private Window window;
    private static AnagraficheIndexViewModel anagraficheIndexViewModel;
    private static IndexViewModel indexViewModel;

    private CodDesc tipoAnagrafica = null;
    private double rating = 2.5;
    private Anagrafiche anagraficaToSave = null;
    private Indirizzi sedePrincipaleToSave = new Indirizzi();
    private Indirizzi destinazioneBaseToSave = new Indirizzi();
    private List<CodDesc> status = new ArrayList<>();
    private int selectedStatusIndex = 0;


    // DATI PRINCIPALI
    private List<CodDesc> soggetti = new ArrayList<>();
    private int selectedSoggettoIndex = 0;
    private  List<CodDesc> sessi = new ArrayList<>();
    private int selectedSessoIndex = 0;
    private Boolean ragioneSocialeDisabled = false;


    // INDIRIZZI - SEDI
    @Wire("#indirizzi-columns")
    Columns indirizziColumns = new Columns();
    private Boolean allIndirizziChecked = false;
    private List<Indirizzi> indirizzi = new ArrayList<>();
    private Indirizzi selectedIndirizzo = new Indirizzi();
    private List<CodDesc> tipiIndirizzo = new ArrayList<>();
    private int selectedTipoIndirizzoIndex = 0;



    public static Window apriPopup(AnagraficheIndexViewModel parentModel, Map<String, Object> params) {
        anagraficheIndexViewModel = parentModel;
        Window window = (Window) Executions.createComponents(
                "anagrafiche/anagrafiche.mono.zul", null, params);
        return window;
    }

    public static Window apriPopup(IndexViewModel parentModel, Map<String, Object> params) {
        indexViewModel = parentModel;
        Window window = (Window) Executions.createComponents(
                "anagrafiche/anagrafiche.mono.zul", null, params);
        return window;
    }



    @Init
    private void init() throws IOException {
        PropertiesReader.setURI();

        Map<?, ?> args = Executions.getCurrent().getArg();
        anagraficaToSave = (Anagrafiche) args.get("anagraficaToSave");
        tipoAnagrafica = new CodDesc((String) args.get("tipoAnagrafica"), General.setTipoAnagrafica((String) args.get("tipoAnagrafica")));
        monoType = (String) args.get(("monoType"));

        System.out.println();

        initMainTabs();
        initSecondaryTabs();
    }

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        generateStars();
        generateMainTabs();
        generateSecondaryTabs();
        generateIndirizziColumns();

        EventQueues.lookup("indirizzoQueue", EventQueues.APPLICATION, true).subscribe(event -> {
            if ("indirizzoSaved".equals(event.getName())) {
                initIndirizzi();
                BindUtils.postNotifyChange(null, null, this, "indirizzi");
            }
        });
    }

    @Command
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }
    @Command
    @NotifyChange({"anagraficaToSave", "ragioneSocialeDisabled"})
    public void onSelectSoggetto(@BindingParam("selectedItem") CodDesc selectedItem) {
        if ("P".equals(selectedItem.getCodice()) || "EP".equals(selectedItem.getCodice())) {
            anagraficaToSave.setRagioneSociale("");
            ragioneSocialeDisabled = true;
        } else {
            ragioneSocialeDisabled = false;
        }
    }

    @Command
    @NotifyChange({"indirizzi"})
    public void onAllIndirizziChecked() {
        if (allIndirizziChecked) {
            for (Indirizzi i: indirizzi) {
                i.setChecked(true);
            }
        } else {
            for (Indirizzi i: indirizzi) {
                i.setChecked(!i.getChecked());
            }
        }
    }
    @Command
    @NotifyChange({"indirizzi","allIndirizziChecked"})
    public void onIndirizzoChecked() {
        for (Indirizzi i: indirizzi) {
            if (!i.getChecked()) {
                allIndirizziChecked = false;
                break;
            } else {
                allIndirizziChecked = true;
            }
        }
    }
    @Command
    @NotifyChange(
            {
            "indirizzi",
            "selectedIndirizzo",
            "selectedTipoIndirizzoIndex",
            "selectedIndirizzoNazioneIndex",
            "isSelectedIndirizzoInItaly",
            "selectedIndirizzoRegioneIndex",
            "provinceSelectedIndirizzo",
            "selectedIndirizzoProvinciaIndex",
            "comuniSelectedIndirizzo",
            "selectedIndirizzoComuneIndex",
            "capSelectedIndirizzo",
            "selectedIndirizzoCapIndex",
            })
    public void onIndirizzoSelected(@BindingParam("indirizzo") Indirizzi indirizzo) {

        selectedIndirizzo = indirizzo;

        initTipoIndirizzo();

        for (Indirizzi i: indirizzi) {
            if (!i.getId().equals(indirizzo.getId())) {
                i.setSelected(false);
            }
        }

        indirizzo.setSelected(!indirizzo.getSelected());

        String script = "localStorage.setItem('idIndirizzoSelezionato', '" + selectedIndirizzo.getId() + "');";
        Clients.evalJavaScript(script);
    }

    @Command
    public void onCloseModal() {
        try {
            if (indexViewModel != null) {
                indexViewModel.onSearch();
            } else {
                System.err.println("indexViewModel è null, impossibile chiamare onSearch()");
            }

            if (window != null) {
                window.detach();
            } else {
                System.err.println("La finestra non è stata inizializzata.");
            }
        } catch (SQLException e) {
            System.err.println("Errore durante l'esecuzione di onSearch(): " + e.getMessage());
        }
    }


    /////////////// MAIN TABS METHODS ///////////////
    public void initMainTabs() {
        initStatus();
    }

    public void initStatus() {
        List<Dizionari> dizionari = dizionariService.getDizionariFromCategoria("STATUS_ANAG");
        for (Dizionari d: dizionari) {
            CodDesc statusItem = new CodDesc(d.getCodice(), d.getDescrizione());
            status.add(statusItem);
            if (d.getCodice().equals(anagraficaToSave.getStatus())) {
                selectedStatusIndex = status.indexOf(statusItem);
            }
        }
    }
    /////////////// MAIN TABS METHODS ///////////////


    /////////////// SECONDARY TABS TABS METHODS ///////////////
    public void initSecondaryTabs() {

        System.out.println("SIAMO QUIII");

        initSoggetti();
        initSessi();
        initIndirizzi();
    }

    public void initSoggetti() {
        soggetti = General.getSoggetti();

        if (monoType.equals("EDIT")) {
            for (CodDesc s: soggetti) {
                if (s.getCodice().equals(anagraficaToSave.getSoggetto())) {
                    selectedSoggettoIndex = soggetti.indexOf(s);
                    break;
                }
            }
        } else {
            selectedSoggettoIndex = 0;
        }

        ragioneSocialeDisabled = anagraficaToSave.getSoggetto().equals("P") || anagraficaToSave.getSoggetto().equals("EP");

        System.out.println("ANA ==> " + anagraficaToSave);
    }

    public  void initSessi() {
        sessi = General.getSessi();

        if (monoType.equals("EDIT")) {
            for (CodDesc s: sessi) {
                if (anagraficaToSave.getSesso() != null && anagraficaToSave.getSesso().equals(s.getCodice())) {
                    selectedSessoIndex = sessi.indexOf(s);
                }
            }
        } else {
            selectedSessoIndex = 0;
        }

    }

    public  void initIndirizzi() {
        if (monoType.equals("EDIT"))
        {
            indirizzi = indirizziService.getIndirizziFromIdAnagrafica(anagraficaToSave.getId());
        }

        tipiIndirizzo = General.getTipiIndirizzo();
    }


    /////////////// SECONDARY TABS TABS METHODS ///////////////


    public void generateStars() {
        starsContainer.getChildren().clear();

        for (int i = 0; i < 5; i++) {
            Html star;
            if (i < rating - 0.5) {
                star = new Html("&#xf005;");
            } else if (i < rating) {
                star = new Html("&#xf123;");
            } else {
                star = new Html("&#xf006;");
            }
            star.setStyle("font-family: 'FontAwesome';");
            starsContainer.appendChild(star);
        }
    }

    public void generateMainTabs() {
        ArrayList<TabRef> mainTabRefs = General.getMainTabs();
        for (TabRef mainTabRef: mainTabRefs) {
            Tab tab = new Tab(mainTabRef.getName());
            this.mainTabs.appendChild(tab);
        }
    }
    public void generateSecondaryTabs() {
        ArrayList<TabRef> secondaryTabRefs = General.getSecondaryTabs();
        for (TabRef secondaryTabRef: secondaryTabRefs) {
            Tab tab = new Tab(secondaryTabRef.getName());
            this.secondaryTabs.appendChild(tab);
        }
    }

    public void generateIndirizziColumns() {
        /*
        List<GridColumn> indirizziColumnRefs = General.getIndirizziColumns();
        for (GridColumn columnRef: indirizziColumnRefs) {
            Column column = new Column(columnRef.getLabel());
            column.setWidth(columnRef.getWidth());
            indirizziColumns.appendChild(column);
        }
         */
    }

    public  void initTipoIndirizzo() {
        tipiIndirizzo = General.getTipiIndirizzo();
        for (CodDesc ti: tipiIndirizzo) {
            if (selectedIndirizzo != null && selectedIndirizzo.getTipoIndirizzo().equals(ti.getCodice())) {
                selectedTipoIndirizzoIndex = tipiIndirizzo.indexOf(ti);
            }
        }
    }
    @Command
    public void onSave() throws NoSuchFieldException, IllegalAccessException {
        anagraficheService.saveAnagrafica(anagraficaToSave);
    }


    public CodDesc getTipoAnagrafica() {
        return tipoAnagrafica;
    }

    public Indirizzi getSedePrincipaleToSave() {
        return sedePrincipaleToSave;
    }

    public Indirizzi getDestinazioneBaseToSave() {
        return destinazioneBaseToSave;
    }

    public Anagrafiche getAnagraficaToSave() {
        return anagraficaToSave;
    }

    public List<CodDesc> getStatus() {
        return status;
    }
    public int getSelectedStatusIndex() {
        return selectedStatusIndex;
    }

    public void setSelectedStatusIndex(int selectedStatusIndex) {
        this.selectedStatusIndex = selectedStatusIndex;
    }

    public Integer getSelectedSessoIndex() {
        return selectedSessoIndex;
    }

    public void setSelectedSessoIndex(Integer selectedSessoIndex) {
        this.selectedSessoIndex = selectedSessoIndex;
    }

    public int getSelectedSoggettoIndex() {
        return selectedSoggettoIndex;
    }
    public void setSelectedSoggettoIndex(int selectedSoggettondex) {
        this.selectedSoggettoIndex = selectedSoggettondex;
    }
    public List<CodDesc> getSessi() {
        return sessi;
    }
    public List<CodDesc> getSoggetti() {
        return soggetti;
    }
    public Boolean getRagioneSocialeDisabled() {
        return ragioneSocialeDisabled;
    }
    public void setRagioneSocialeDisabled(Boolean ragioneSocialeDisabled) {
        this.ragioneSocialeDisabled = ragioneSocialeDisabled;
    }
    public List<Indirizzi> getIndirizzi() {
        return indirizzi;
    }

    public List<CodDesc> getTipiIndirizzo() {
        return tipiIndirizzo;
    }

    public int getSelectedTipoIndirizzoIndex() {
        return selectedTipoIndirizzoIndex;
    }

    public void setSelectedTipoIndirizzoIndex(int selectedTipoIndirizzoIndex) {
        this.selectedTipoIndirizzoIndex = selectedTipoIndirizzoIndex;
    }

    public Indirizzi getSelectedIndirizzo() {
        return selectedIndirizzo;
    }

    public Boolean getAllIndirizziChecked() {
        return allIndirizziChecked;
    }

    public void setAllIndirizziChecked(Boolean allIndirizziChecked) {
        this.allIndirizziChecked = allIndirizziChecked;
    }

    public void setAllChecked(Boolean allIndirizziChecked) {
        this.allIndirizziChecked = allIndirizziChecked;
    }

    public String getFullName() {
        return anagraficaToSave.getCognome() + " " + anagraficaToSave.getNome();
    }

    public String getMonoType() {
        return monoType;
    }

    private Boolean sedePrincipaleOnServer = false;
    private Boolean selectedIndirizzoOnServer = false;

    @Command
    public void onSelectedIndirizzoServerSwitch() {
        selectedIndirizzoOnServer = true;
    }

    @Command
    public void onSedePrincipaleServerSwitch() {
        sedePrincipaleOnServer = true;
    }

    @Command
    public void onWindowClicked() {
        Clients.evalJavaScript("localStorage.setItem('windowClicked', 'true');");
    }

    public static void test() {
        System.out.println("Ciaoooooooooooo");
    }
}




