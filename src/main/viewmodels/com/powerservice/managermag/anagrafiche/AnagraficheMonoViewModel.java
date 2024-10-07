package com.powerservice.managermag.anagrafiche;

import com.powerservice.managermag.anagrafiche.utilities.General;
import it.powerservice.managermag.*;
import it.powerservice.managermag.customClass.CodDesc;
import it.powerservice.managermag.customClass.GridColumn;
import it.powerservice.managermag.customClass.TabRef;
import it.powerservice.managermag.geography.*;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;
import org.zkoss.zul.Window;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @Wire("#statoSedePrincipale")
    private Combobox statoSedePrincipaleCombobox;
    @Wire("#regioneSedePrincipale")
    private Combobox regioneSedePrincipaleCombobox;
    @Wire("#provinciaSedePrincipale")
    private Combobox provinciaSedePrincipaleCombobox;
    @Wire("#cittaSedePrincipale")
    private Combobox comuneSedePrincipaleCombobox;
    @Wire("#capSedePrincipale")
    private Combobox capSedePrincipaleCombobox;
    @Wire("#statoSelectedIndirizzo")
    private Combobox statoSelectedIndirizzoCombobox;
    @Wire("#regioneSelectedIndirizzo")
    private Combobox regioneSelectedIndirizzoCombobox;
    @Wire("#provinciaSelectedIndirizzo")
    private Combobox provinciaSelectedIndirizzoCombobox;
    @Wire("#comuneSelectedIndirizzo")
    private Combobox comuneSelectedIndirizzoCombobox;
    @Wire("#capSelectedIndirizzo")
    private Combobox capSelectedIndirizzoCombobox;
    private Window window;
    private static AnagraficheIndexViewModel anagraficheIndexViewModel;
    private CodDesc tipoAnagrafica = null;
    private double rating = 2.5;
    private Anagrafiche anagraficaToSave = null;
    private List<CodDesc> status = new ArrayList<>();
    private int selectedStatusIndex = 0;
    private List<Nazioni> nazioni = Arrays.asList(new Nazioni("(Nessuno)"));
    private List<Regioni> regioni = Arrays.asList(new Regioni("(Nessuno)"));
    private List<Province> provinceSedePrincipale = Arrays.asList(new Province("(Nessuno)"));
    private List<Comuni> comuniSedePrincipale = Arrays.asList(new Comuni("(Nessuno)"));
    private List<Cap> capSedePrincipale = Arrays.asList(new Cap("(Nessuno)"));
    private List<Province> provinceSelectedIndirizzo = new ArrayList<>(Arrays.asList(new Province("(Nessuno)")));
    private List<Comuni> comuniSelectedIndirizzo = new ArrayList<>(Arrays.asList(new Comuni("(Nessuno)")));
    private List<Cap> capSelectedIndirizzo = new ArrayList<>(Arrays.asList(new Cap("(Nessuno)")));


    // DATI PRINCIPALI
    private List<CodDesc> soggetti = new ArrayList<>();
    private int selectedSoggettoIndex = 0;
    private  List<CodDesc> sessi = new ArrayList<>();
    private int selectedSessoIndex = 0;
    private Boolean ragioneSocialeDisabled = false;
    private Indirizzi indirizzoSedePrincipale = null;
    private int selectedCapSedePrincipaleIndex = 0;
    private int selectedComuneSedePrincipaleIndex = 0;
    private int selectedProvinciaSedePrincipaleIndex = 0;
    private int selectedRegioneSedePrincipaleIndex = 0;
    private int selectedNazioneSedePrincipaleIndex = 0;
    private Boolean isSelectedIndirizzoInItaly = true;


    // INDIRIZZI - SEDI
    @Wire("#indirizzi-columns")
    Columns indirizziColumns = new Columns();
    private Boolean allIndirizziChecked = false;
    private List<Indirizzi> indirizzi = new ArrayList<>();
    private Indirizzi selectedIndirizzo = new Indirizzi();
    private List<CodDesc> tipiIndirizzo = new ArrayList<>();
    private int selectedTipoIndirizzoIndex = 0;
    private int selectedIndirizzoNazioneIndex = 0;
    private int selectedIndirizzoRegioneIndex = 0;
    private int selectedIndirizzoProvinciaIndex = 0;
    private int selectedIndirizzoComuneIndex = 0;
    private int selectedIndirizzoCapIndex = 0;
    private Boolean isSedePrincipaleInItaly = false;


    public static Window apriPopup(AnagraficheIndexViewModel parentModel, Map<String, Object> params) {
        anagraficheIndexViewModel = parentModel;
        Window window = (Window) Executions.createComponents(
                "anagrafiche/anagrafiche.mono.zul", null, params);
        return window;
    }

    @Init
    private void init() {
        Map<?, ?> args = Executions.getCurrent().getArg();
        anagraficaToSave = (Anagrafiche) args.get("anagraficaToSave");
        tipoAnagrafica = new CodDesc((String) args.get("tipoAnagrafica"), General.setTipoAnagrafica((String) args.get("tipoAnagrafica")));

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
    }

    @Command
    @NotifyChange("tipologiaPapiamentoToSave")
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }
    @Command
    @NotifyChange({"anagraficaToSave", "ragioneSocialeDisabled"})
    public void onSelectSoggetto() {
        if (soggetti.get(selectedSoggettoIndex).getCodice().equals("P") || soggetti.get(selectedSoggettoIndex).getCodice().equals("EP")) {
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

        selectedIndirizzo = indirizzo.clone();

        initTipoIndirizzo();

        for (Indirizzi i: indirizzi) {
            if (!i.getId().equals(indirizzo.getId())) {
                i.setSelected(false);
            }
        }

        indirizzo.setSelected(!indirizzo.getSelected());

        for (Nazioni n: nazioni) {
            if (n.getNazione().equals(indirizzo.getNazione())) {
                selectedIndirizzoNazioneIndex = nazioni.indexOf(n);
                isSelectedIndirizzoInItaly = n.getCountryCode().equals("IT");
            }
        }

        for (Regioni r: regioni) {
            if (r.getNome().equals(indirizzo.getRegione())) {
                selectedIndirizzoRegioneIndex = regioni.indexOf(r);
            }
        }

        provinceSelectedIndirizzo = comuniService.getProvinceFromRegione(regioni.get(selectedIndirizzoRegioneIndex).getNome());
        provinceSelectedIndirizzo.add(0, new Province("(Nessuno)"));

        for (Province p: provinceSelectedIndirizzo) {
            if (p.getNome().equals(indirizzo.getProvincia())) {
                if (!provinceSelectedIndirizzo.isEmpty()) {
                    selectedIndirizzoProvinciaIndex = provinceSelectedIndirizzo.indexOf(p);
                }
            }
        }

        comuniSelectedIndirizzo = comuniService.getComuniFromProvincia(provinceSelectedIndirizzo.get(selectedIndirizzoProvinciaIndex).getNomeCompleto());
        comuniSelectedIndirizzo.add(0, new Comuni("(Nessuno)"));



        for (Comuni c: comuniSelectedIndirizzo) {
            if (c.getCitta().equals(indirizzo.getComune())) {
                selectedIndirizzoComuneIndex = comuniSelectedIndirizzo.indexOf(c);
            }
        }

        capSelectedIndirizzo = comuniService.getCapFromComune(comuniSelectedIndirizzo.get(selectedIndirizzoComuneIndex).getCitta());
        capSelectedIndirizzo.add(0, new Cap("(Nessuno)"));

        for (Cap c: capSelectedIndirizzo) {
            if (c.getCodice().equals(indirizzo.getCap())) {
                selectedIndirizzoCapIndex = capSelectedIndirizzo.indexOf(c);
            };
        }

    }

    @Command
    public void onCloseModal() {
        if (window != null) {
            window.detach();
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
        initSoggetti();
        initSessi();
        initIndirizzi();
    }

    public void initSoggetti() {
        soggetti = General.getSoggetti();

        for (CodDesc s: soggetti) {
            if (s.getCodice().equals(anagraficaToSave.getSoggetto())) {
                selectedSoggettoIndex = soggetti.indexOf(s);
                break;
            }
        }
        ragioneSocialeDisabled = anagraficaToSave.getSoggetto().equals("P") || anagraficaToSave.getSoggetto().equals("EP");
    }

    public  void initSessi() {
        sessi = General.getSessi();
        for (CodDesc s: sessi) {
            if (anagraficaToSave.getSesso() != null && anagraficaToSave.getSesso().equals(s.getCodice())) {
                selectedSessoIndex = sessi.indexOf(s);
            }
        }
    }

    public  void initIndirizzi() {
        indirizzi = indirizziService.getIndirizziFromIdAnagrafica(anagraficaToSave.getId());

        tipiIndirizzo = General.getTipiIndirizzo();

        nazioni = nazioniService.getNazioni();
        nazioni.add(0, new Nazioni("(Nessuno)"));


        for (Indirizzi i: indirizzi) {
            if (i.getTipoIndirizzo().equals("S")) {
                indirizzoSedePrincipale = i;
                break;
            }
        }
        for (Nazioni n: nazioni) {
            if (n.getNazione().equals(indirizzoSedePrincipale.getNazione())) {
                selectedNazioneSedePrincipaleIndex = nazioni.indexOf(n);
                if (n.getCountryCode().equals("IT")) {
                    isSedePrincipaleInItaly = true;
                }
            }
        }
        
        regioni = comuniService.getRegioni();
        regioni.add(0, new Regioni("(Nessuno)"));

        for (Regioni r: regioni) {
            if (r.getNome().equals(indirizzoSedePrincipale.getRegione())) {
                selectedRegioneSedePrincipaleIndex = regioni.indexOf(r);
            }
        }

        provinceSedePrincipale = comuniService.getProvinceFromRegione(regioni.get(selectedRegioneSedePrincipaleIndex).getNome());
        provinceSedePrincipale.add(0, new Province("(Nessuno)"));

        for (Province p: provinceSedePrincipale) {
            if (p.getNome().equals(indirizzoSedePrincipale.getProvincia())) {
                selectedProvinciaSedePrincipaleIndex = provinceSedePrincipale.indexOf(p);
            }
        }


        comuniSedePrincipale = comuniService.getComuniFromProvincia(provinceSedePrincipale.get(selectedProvinciaSedePrincipaleIndex).getNomeCompleto());
        comuniSedePrincipale.add(0, new Comuni("(Nessuno)"));

        for (Comuni c: comuniSedePrincipale) {
            if (c.getCitta().equals(indirizzoSedePrincipale.getComune())) {
                selectedComuneSedePrincipaleIndex = comuniSedePrincipale.indexOf(c);
            }
        }

        capSedePrincipale = comuniService.getCapFromComune(comuniSedePrincipale.get(selectedComuneSedePrincipaleIndex).getCitta());
        capSedePrincipale.add(0, new Cap("(Nessuno)"));

        for (Cap c: capSedePrincipale) {
            if (c.getCodice().equals(indirizzoSedePrincipale.getCap())) {
                selectedCapSedePrincipaleIndex = capSedePrincipale.indexOf(c);
            };
        }

    }

    @Command
    @NotifyChange({"isSedePrincipaleInItaly", "indirizzoSedePrincipale", "provinceSedePrincipale", "comuniSedePrincipale", "capSedePrincipale", "fieldZIndex"})
    public void onNazioneSedePrincipaleSelected(@BindingParam("stato") String stato) {



        if (sedePrincipaleOnServer)
        {
            String script = "localStorage.setItem('sedePrincipaleOnServer', 'true');";
            Clients.evalJavaScript(script);
            sedePrincipaleOnServer = false;
        }

        if (!stato.equals("Italia")) {
            System.out.println(stato);
            isSedePrincipaleInItaly = false;

            for (Nazioni n: nazioni) {
                if (n.getNazione().equals(stato)) {
                    statoSedePrincipaleCombobox.setSelectedIndex(nazioni.indexOf(n));
                    statoSedePrincipaleCombobox.invalidate();
                }
            }

            indirizzoSedePrincipale.setRegione("");
            indirizzoSedePrincipale.setProvincia("");
            indirizzoSedePrincipale.setComune("");
            indirizzoSedePrincipale.setCap("");

        } else {
            isSedePrincipaleInItaly = true;

            statoSedePrincipaleCombobox.setSelectedIndex(90);
            statoSedePrincipaleCombobox.invalidate();

            regioneSedePrincipaleCombobox.setValue("(Nessuno)");
            regioneSedePrincipaleCombobox.setSelectedIndex(0);
            regioneSedePrincipaleCombobox.invalidate();

            provinceSedePrincipale.clear();
            provinceSedePrincipale.add(new Province("(Nessuno)"));
            provinciaSedePrincipaleCombobox.setValue("(Nessuno)");
            provinciaSedePrincipaleCombobox.setSelectedIndex(0);
            provinciaSedePrincipaleCombobox.invalidate();

            comuniSedePrincipale.clear();
            comuniSedePrincipale.add(new Comuni("(Nessuno)"));
            comuneSedePrincipaleCombobox.setValue("(Nessuno)");
            comuneSedePrincipaleCombobox.setSelectedIndex(0);
            comuneSedePrincipaleCombobox.invalidate();

            capSedePrincipale.clear();
            capSedePrincipale.add(new Cap("(Nessuno)"));
            capSedePrincipaleCombobox.setValue("(Nessuno)");
            capSedePrincipaleCombobox.setSelectedIndex(0);
            capSedePrincipaleCombobox.invalidate();
        }
    }

    @Command
    @NotifyChange({"provinceSedePrincipale", "comuniSedePrincipale", "capSedePrincipale"})
    public void onRegioneSedePrincipaleSelected(@BindingParam("regione") String regione) {

        System.out.println("SIAMO IN ITALIA ===> REGIONI");

        if (sedePrincipaleOnServer)
        {
            String script = "localStorage.setItem('sedePrincipaleOnServer', 'true');";
            Clients.evalJavaScript(script);
            sedePrincipaleOnServer = false;
        }

        provinceSedePrincipale.clear();
        provinceSedePrincipale.add(new Province("(Nessuno)"));
        provinciaSedePrincipaleCombobox.setValue("(Nessuno)");
        provinciaSedePrincipaleCombobox.setSelectedIndex(0);
        provinciaSedePrincipaleCombobox.invalidate();

        comuniSedePrincipale.clear();
        comuniSedePrincipale.add(new Comuni("(Nessuno)"));
        comuneSedePrincipaleCombobox.setValue("(Nessuno)");
        comuneSedePrincipaleCombobox.setSelectedIndex(0);
        comuneSedePrincipaleCombobox.invalidate();

        capSedePrincipale.clear();
        capSedePrincipale.add(new Cap("(Nessuno)"));
        capSedePrincipaleCombobox.setValue("(Nessuno)");
        capSedePrincipaleCombobox.setSelectedIndex(0);
        capSedePrincipaleCombobox.invalidate();

        for (Regioni r: regioni) {

            if (r.getNome().equals(regione)) {
                regioneSedePrincipaleCombobox.setValue(regione);
                regioneSedePrincipaleCombobox.setSelectedIndex(regioni.indexOf(r));
                regioneSedePrincipaleCombobox.invalidate();

                provinceSedePrincipale = comuniService.getProvinceFromRegione(regione);
                provinceSedePrincipale.add(0, new Province("(Nessuno)"));
                provinciaSedePrincipaleCombobox.setSelectedIndex(0);
                provinciaSedePrincipaleCombobox.invalidate();
            }
        }
    }

    @Command
    @NotifyChange({"comuniSedePrincipale", "capSedePrincipale"})
    public void onProvinciaSedePrincipaleSelected(@BindingParam("provincia") String provincia) {

        System.out.println("SIAMO IN ITALIA ===> PROVINCE");

        if (sedePrincipaleOnServer)
        {
            String script = "localStorage.setItem('sedePrincipaleOnServer', 'true');";
            Clients.evalJavaScript(script);
            sedePrincipaleOnServer = false;
        }

        comuniSedePrincipale.clear();
        comuniSedePrincipale.add(new Comuni("(Nessuno)"));
        comuneSedePrincipaleCombobox.setValue("(Nessuno)");
        comuneSedePrincipaleCombobox.setSelectedIndex(0);
        comuneSedePrincipaleCombobox.invalidate();

        capSedePrincipale.clear();
        capSedePrincipale.add(new Cap("(Nessuno)"));
        capSedePrincipaleCombobox.setValue("(Nessuno)");
        capSedePrincipaleCombobox.setSelectedIndex(0);
        capSedePrincipaleCombobox.invalidate();


        for (Province p: provinceSedePrincipale) {

            if (p.getNomeCompleto().equals(provincia)) {
                provinciaSedePrincipaleCombobox.setValue(provincia);
                provinciaSedePrincipaleCombobox.setSelectedIndex(provinceSedePrincipale.indexOf(p));
                provinciaSedePrincipaleCombobox.invalidate();

                comuniSedePrincipale = comuniService.getComuniFromProvincia(provincia);
                comuniSedePrincipale.add(0, new Comuni("(Nessuno)"));
                comuneSedePrincipaleCombobox.setSelectedIndex(0);
                comuneSedePrincipaleCombobox.invalidate();
            }
        }

    }



    @Command
    @NotifyChange({"capSedePrincipale"})
    public void onComuneSedePrincipaleSelected(@BindingParam("comune") String comune) {
        System.out.println("SIAMO IN ITALIA ===> COMUNI");

        if (sedePrincipaleOnServer)
        {
            String script = "localStorage.setItem('sedePrincipaleOnServer', 'true');";
            Clients.evalJavaScript(script);
            sedePrincipaleOnServer = false;
        }


        capSedePrincipale.clear();
        capSedePrincipale.add(new Cap("Nessuno"));
        capSedePrincipaleCombobox.setValue("(Nessuno)");
        capSedePrincipaleCombobox.setSelectedIndex(0);
        capSedePrincipaleCombobox.invalidate();


        for (Comuni c: comuniSedePrincipale) {


            if (c.getCitta().equals(comune)) {
                comuneSedePrincipaleCombobox.setValue(comune);
                comuneSedePrincipaleCombobox.setSelectedIndex(comuniSedePrincipale.indexOf(c));
                comuneSedePrincipaleCombobox.invalidate();

                capSedePrincipale = comuniService.getCapFromComune(comune);
                capSedePrincipale.add(0, new Cap("(Nessuno)"));
                capSedePrincipaleCombobox.setSelectedIndex(0);
                capSedePrincipaleCombobox.invalidate();
            }
        }
    }


    @Command
    @NotifyChange({"isSelectedIndirizzoInItaly", "selectedIndirizzo", "provinceSelectedIndirizzo", "comuniSelectedIndirizzo", "capSelectedIndirizzo"})
    public void onNazioneSelectedIndirizzoSelected(@BindingParam("stato") String stato) {

        if (selectedIndirizzo != null) {
            System.out.println(iframeIndirizzoSedePrincipale.getStyle());


            if (selectedIndirizzoOnServer)
            {
                String script = "localStorage.setItem('selectedIndirizzoOnServer', 'true');";
                Clients.evalJavaScript(script);
                selectedIndirizzoOnServer = false;
            }

            if (!stato.equals("Italia")) {
                isSelectedIndirizzoInItaly = false;

                for (Nazioni n: nazioni) {
                    if (n.getNazione().equals(stato)) {
                        statoSelectedIndirizzoCombobox.setSelectedIndex(nazioni.indexOf(n));
                        statoSelectedIndirizzoCombobox.invalidate();
                    }
                }

                selectedIndirizzo.setRegione("");
                selectedIndirizzo.setProvincia("");
                selectedIndirizzo.setComune("");
                selectedIndirizzo.setCap("");

            } else {
                System.out.println("SIAMO IN ITALIA ===> NAZIONI");
                isSelectedIndirizzoInItaly = true;

                statoSelectedIndirizzoCombobox.setSelectedIndex(90);
                statoSelectedIndirizzoCombobox.invalidate();

                regioneSelectedIndirizzoCombobox.setValue("(Nessuno)");
                regioneSelectedIndirizzoCombobox.setSelectedIndex(0);
                regioneSelectedIndirizzoCombobox.invalidate();

                provinceSelectedIndirizzo.clear();
                provinceSelectedIndirizzo.add(new Province("(Nessuno)"));
                provinciaSelectedIndirizzoCombobox.setValue("(Nessuno)");
                provinciaSelectedIndirizzoCombobox.setSelectedIndex(0);
                provinciaSelectedIndirizzoCombobox.invalidate();

                comuniSelectedIndirizzo.clear();
                comuniSelectedIndirizzo.add(new Comuni("(Nessuno)"));
                comuneSelectedIndirizzoCombobox.setValue("(Nessuno)");
                comuneSelectedIndirizzoCombobox.setSelectedIndex(0);
                comuneSelectedIndirizzoCombobox.invalidate();

                capSelectedIndirizzo.clear();
                capSelectedIndirizzo.add(new Cap("(Nessuno)"));
                capSelectedIndirizzoCombobox.setValue("(Nessuno)");
                capSelectedIndirizzoCombobox.setSelectedIndex(0);
                capSelectedIndirizzoCombobox.invalidate();
            }
        }


    }

    @Command
    @NotifyChange({"provinceSelectedIndirizzo", "comuniSelectedIndirizzo", "capSelectedIndirizzo"})
    public void onRegioneSelectedIndirizzoSelected(@BindingParam("regione") String regione) {

        if (selectedIndirizzo != null) {
            System.out.println("SIAMO IN ITALIA ===> REGIONI");

            if (selectedIndirizzoOnServer)
            {
                String script = "localStorage.setItem('selectedIndirizzoOnServer', 'true');";
                Clients.evalJavaScript(script);
                selectedIndirizzoOnServer = false;
            }

            provinceSelectedIndirizzo.clear();
            provinceSelectedIndirizzo.add(new Province("(Nessuno)"));
            provinciaSelectedIndirizzoCombobox.setValue("(Nessuno)");
            provinciaSelectedIndirizzoCombobox.setSelectedIndex(0);
            provinciaSelectedIndirizzoCombobox.invalidate();

            comuniSelectedIndirizzo.clear();
            comuniSelectedIndirizzo.add(new Comuni("(Nessuno)"));
            comuneSelectedIndirizzoCombobox.setValue("(Nessuno)");
            comuneSelectedIndirizzoCombobox.setSelectedIndex(0);
            comuneSelectedIndirizzoCombobox.invalidate();

            capSelectedIndirizzo.clear();
            capSelectedIndirizzo.add(new Cap("(Nessuno)"));
            capSelectedIndirizzoCombobox.setValue("(Nessuno)");
            capSelectedIndirizzoCombobox.setSelectedIndex(0);
            capSelectedIndirizzoCombobox.invalidate();

            for (Regioni r: regioni) {

                if (r.getNome().equals(regione)) {
                    regioneSelectedIndirizzoCombobox.setValue(regione);
                    regioneSelectedIndirizzoCombobox.setSelectedIndex(regioni.indexOf(r));
                    regioneSelectedIndirizzoCombobox.invalidate();

                    provinceSelectedIndirizzo = comuniService.getProvinceFromRegione(regione);
                    provinceSelectedIndirizzo.add(0, new Province("(Nessuno)"));
                    provinciaSelectedIndirizzoCombobox.setSelectedIndex(0);
                    provinciaSelectedIndirizzoCombobox.invalidate();
                }
            }
        }


    }

    @Command
    @NotifyChange({"comuniSelectedIndirizzo", "capSelectedIndirizzo"})
    public void onProvinciaSelectedIndirizzoSelected(@BindingParam("provincia") String provincia) {

        if (selectedIndirizzo != null) {
            System.out.println("SIAMO IN ITALIA ===> PROVINCE");

            if (selectedIndirizzoOnServer)
            {
                String script = "localStorage.setItem('selectedIndirizzoOnServer', 'true');";
                Clients.evalJavaScript(script);
                selectedIndirizzoOnServer = false;
            }

            comuniSelectedIndirizzo.clear();
            comuniSelectedIndirizzo.add(new Comuni("(Nessuno)"));
            comuneSelectedIndirizzoCombobox.setValue("(Nessuno)");
            comuneSelectedIndirizzoCombobox.setSelectedIndex(0);
            comuneSelectedIndirizzoCombobox.invalidate();

            capSelectedIndirizzo.clear();
            capSelectedIndirizzo.add(new Cap("(Nessuno)"));
            capSelectedIndirizzoCombobox.setValue("(Nessuno)");
            capSelectedIndirizzoCombobox.setSelectedIndex(0);
            capSelectedIndirizzoCombobox.invalidate();


            for (Province p: provinceSelectedIndirizzo) {

                if (p.getNomeCompleto().equals(provincia)) {
                    provinciaSelectedIndirizzoCombobox.setValue(provincia);
                    provinciaSelectedIndirizzoCombobox.invalidate();

                    comuniSelectedIndirizzo = comuniService.getComuniFromProvincia(provincia);
                    for (Comuni c: comuniSelectedIndirizzo) {
                        System.out.println(c);
                    }
                    comuniSelectedIndirizzo.add(0, new Comuni("(Nessuno)"));
                    capSelectedIndirizzoCombobox.setSelectedIndex(0);
                    capSelectedIndirizzoCombobox.invalidate();
                }
            }
        }


    }

    @Command
    @NotifyChange({"capSelectedIndirizzo"})
    public void onComuneSelectedIndirizzoSelected(@BindingParam("comune") String comune) {

        if (selectedIndirizzo != null) {
            System.out.println("SIAMO IN ITALIA ===> COMUNI");

            if (selectedIndirizzoOnServer)
            {
                String script = "localStorage.setItem('selectedIndirizzoOnServer', 'true');";
                Clients.evalJavaScript(script);
                selectedIndirizzoOnServer = false;
            }


            capSelectedIndirizzo.clear();
            capSelectedIndirizzo.add(new Cap("(Nessuno)"));
            capSelectedIndirizzoCombobox.setValue("(Nessuno)");
            capSelectedIndirizzoCombobox.setSelectedIndex(0);
            capSelectedIndirizzoCombobox.invalidate();


            for (Comuni c: comuniSelectedIndirizzo) {


                if (c.getCitta().equals(comune)) {
                    comuneSelectedIndirizzoCombobox.setValue(comune);
                    comuneSelectedIndirizzoCombobox.setSelectedIndex(comuniSelectedIndirizzo.indexOf(c));
                    comuneSelectedIndirizzoCombobox.invalidate();

                    capSelectedIndirizzo = comuniService.getCapFromComune(comune);
                    capSelectedIndirizzo.add(0, new Cap("(Nessuno)"));
                    capSelectedIndirizzoCombobox.setSelectedIndex(0);
                    capSelectedIndirizzoCombobox.invalidate();
                }
            }
        }

    }


    private int fieldZIndex = 1000;

    public int getFieldZIndex() {
        return fieldZIndex;
    }
    @Wire("#iframeIndirizzoSedePrincipale")
    Iframe iframeIndirizzoSedePrincipale = new Iframe();

    @Wire("#iframeIndirizzo")
    Iframe iframeSelectedIndirizzo = new Iframe();

    private String falseInputValue = null;
    public String getFalseInputValue() {
        return falseInputValue;
    }
    public void setFalseInputValue(String falseInputValue) {
        this.falseInputValue = falseInputValue;
    }

    @Command
    @NotifyChange({"iframeZIndex", "fieldZIndex"})
    public void onFalseInputChange(@BindingParam("valore") String valore) {
        falseInputValue = valore;
        if (valore.equals("true")) {
            System.out.println("VALOREE TRUE ==> " + valore);
            fieldZIndex = 1000;
            iframeIndirizzoSedePrincipale.setZindex(100000);
            iframeSelectedIndirizzo.setZindex(100000);
        } else if (valore.equals("false")) {
            System.out.println("VALOREE FALSE ==> " + valore);
            fieldZIndex = 100001;
        }
    }

    @Command
    @NotifyChange({"iframeZIndex", "fieldZIndex"})
    public void onFalseInputOver() {
        iframeIndirizzoSedePrincipale.setZindex(100001);
    }
    @Command
    @NotifyChange({"iframeZIndex", "fieldZIndex"})
    public void onFalseInputOut() {
        iframeIndirizzoSedePrincipale.setZindex(1000);
    }
    @Command
    @NotifyChange({"iframeZIndex", "fieldZIndex"})
    public void onComboboxOver() {
        System.out.println("OVER");
      iframeIndirizzoSedePrincipale.setZindex(1000);
      fieldZIndex = 100001;
    }
    @Command
    @NotifyChange({"iframeZIndex", "fieldZIndex"})
    public void onComboboxOut() {
        iframeIndirizzoSedePrincipale.setZindex(100001);
        fieldZIndex = 1000;
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
        List<GridColumn> indirizziColumnRefs = General.getIndirizziColumns();
        for (GridColumn columnRef: indirizziColumnRefs) {
            Column column = new Column(columnRef.getLabel());
            column.setWidth(columnRef.getWidth());
            indirizziColumns.appendChild(column);
        }
    }

    public  void initTipoIndirizzo() {
        tipiIndirizzo = General.getTipiIndirizzo();
        for (CodDesc ti: tipiIndirizzo) {
            if (selectedIndirizzo != null && selectedIndirizzo.getTipoIndirizzo().equals(ti.getCodice())) {
                selectedTipoIndirizzoIndex = tipiIndirizzo.indexOf(ti);
            }
        }
    }

    public Boolean getIsSedePrincipaleInItaly() {
        return isSedePrincipaleInItaly;
    }

    public CodDesc getTipoAnagrafica() {
        return tipoAnagrafica;
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
    public Integer getSelectedIndirizzoNazioneIndex() {
        return selectedIndirizzoNazioneIndex;
    }
    public void setSelectedIndirizzoNazioneIndex(Integer selectedIndirizzoNazioneIndex) {
        this.selectedIndirizzoNazioneIndex = selectedIndirizzoNazioneIndex;
    }

    public int getSelectedIndirizzoRegioneIndex() {
        return selectedIndirizzoRegioneIndex;
    }

    public void setSelectedIndirizzoRegioneIndex(int selectedIndirizzoRegioneIndex) {
        this.selectedIndirizzoRegioneIndex = selectedIndirizzoRegioneIndex;
    }

    public int getSelectedIndirizzoProvinciaIndex() {
        return selectedIndirizzoProvinciaIndex;
    }

    public void setSelectedIndirizzoProvinciaIndex(int selectedIndirizzoProvinciaIndex) {
        this.selectedIndirizzoProvinciaIndex = selectedIndirizzoProvinciaIndex;
    }

    public int getSelectedIndirizzoComuneIndex() {
        return selectedIndirizzoComuneIndex;
    }

    public void setSelectedIndirizzoComuneIndex(int selectedIndirizzoComuneIndex) {
        this.selectedIndirizzoComuneIndex = selectedIndirizzoComuneIndex;
    }

    public int getSelectedIndirizzoCapIndex() {
        return selectedIndirizzoCapIndex;
    }

    public void setSelectedIndirizzoCapIndex(int selectedIndirizzoCapIndex) {
        this.selectedIndirizzoCapIndex = selectedIndirizzoCapIndex;
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
    public List<Nazioni> getNazioni() {
        return nazioni;
    }

    public List<Regioni> getRegioni() {
        return regioni;
    }

    public List<Province> getProvinceSedePrincipale() {
        return provinceSedePrincipale;
    }

    public void setProvinceSedePrincipale(List<Province> provinceSedePrincipale) {
        this.provinceSedePrincipale = provinceSedePrincipale;
    }

    public List<Comuni> getComuniSedePrincipale() {
        return comuniSedePrincipale;
    }

    public void setComuniSedePrincipale(List<Comuni> comuniSedePrincipale) {
        this.comuniSedePrincipale = comuniSedePrincipale;
    }

    public List<Cap> getCapSedePrincipale() {
        return capSedePrincipale;
    }

    public void setCapSedePrincipale(List<Cap> capSedePrincipale) {
        this.capSedePrincipale = capSedePrincipale;
    }

    public List<Province> getProvinceSelectedIndirizzo() {
        return provinceSelectedIndirizzo;
    }

    public void setProvinceSelectedIndirizzo(List<Province> provinceSelectedIndirizzo) {
        this.provinceSelectedIndirizzo = provinceSelectedIndirizzo;
    }

    public List<Comuni> getComuniSelectedIndirizzo() {
        return comuniSelectedIndirizzo;
    }

    public void setComuniSelectedIndirizzo(List<Comuni> comuniSelectedIndirizzo) {
        this.comuniSelectedIndirizzo = comuniSelectedIndirizzo;
    }

    public List<Cap> getCapSelectedIndirizzo() {
        return capSelectedIndirizzo;
    }

    public void setCapSelectedIndirizzo(List<Cap> capSelectedIndirizzo) {
        this.capSelectedIndirizzo = capSelectedIndirizzo;
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

    public Indirizzi getIndirizzoSedePrincipale() {
        return indirizzoSedePrincipale;
    }

    public int getSelectedNazioneSedePrincipaleIndex() {
        return selectedNazioneSedePrincipaleIndex;
    }

    public void setSelectedNazioneSedePrincipaleIndex(int selectedNazioneSedePrincipaleIndex) {
        this.selectedNazioneSedePrincipaleIndex = selectedNazioneSedePrincipaleIndex;
    }

    public int getSelectedRegioneSedePrincipaleIndex() {
        return selectedRegioneSedePrincipaleIndex;
    }
    public void setSelectedRegioneSedePrincipaleIndex(int selectedRegioneSedePrincipaleIndex) {
        this.selectedRegioneSedePrincipaleIndex = selectedRegioneSedePrincipaleIndex;
    }

    public int getSelectedProvinciaSedePrincipaleIndex() {
        return selectedProvinciaSedePrincipaleIndex;
    }
    public void setSelectedProvinciaSedePrincipaleIndex(int selectedProvinciaSedePrincipaleIndex) {
        this.selectedProvinciaSedePrincipaleIndex = selectedProvinciaSedePrincipaleIndex;
    }
    public int getSelectedComuneSedePrincipaleIndex() {
        return selectedComuneSedePrincipaleIndex;
    }
    public void setSelectedComuneSedePrincipaleIndex(int selectedComuneSedePrincipaleIndex) {
        this.selectedComuneSedePrincipaleIndex = selectedComuneSedePrincipaleIndex;
    }

    public int getSelectedCapSedePrincipaleIndex() {
        return selectedCapSedePrincipaleIndex;
    }

    public void setSelectedCapSedePrincipaleIndex(int selectedCapSedePrincipaleIndex) {
        this.selectedCapSedePrincipaleIndex = selectedCapSedePrincipaleIndex;
    }
    public Boolean getIsSelectedIndirizzoInItaly() {
        return isSelectedIndirizzoInItaly;
    }
    public void setIsSelectedIndirizzoInItaly(Boolean selectedIndirizzoInItaly) {
        isSelectedIndirizzoInItaly = selectedIndirizzoInItaly;
    }
    public String getFullName() {
        return anagraficaToSave.getCognome() + " " + anagraficaToSave.getNome();
    }





    @Command
    public void onSelectedIndirizzoFalseWidgetChange(@BindingParam("valore") String valore) {
        if (valore.equals("true")) {
            iframeSelectedIndirizzo.setHeight("250px");
            iframeSelectedIndirizzo.setStyle("left: 75px");
            iframeSelectedIndirizzo.setStyle("bottom: -216px");
        } else {
            iframeSelectedIndirizzo.setHeight("28px");
            iframeSelectedIndirizzo.setStyle("left: 75px");
            iframeSelectedIndirizzo.setStyle("bottom: 6px");
        }
    }
    @Command
    public void onSedePrincipaleFalseWidgetChange(@BindingParam("valore") String valore) {
        System.out.println("qui");
        if (valore.equals("true")) {
            iframeIndirizzoSedePrincipale.setHeight("250px");
            iframeIndirizzoSedePrincipale.setStyle("left: 139px");
            iframeIndirizzoSedePrincipale.setStyle("bottom: 5px");
        } else {
            iframeIndirizzoSedePrincipale.setHeight("28px");
            iframeIndirizzoSedePrincipale.setStyle("left: 139px");
            iframeIndirizzoSedePrincipale.setStyle("bottom:227px");
        }
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


}




