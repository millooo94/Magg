package com.powerservice.managermag;

import com.mysql.cj.xdevapi.Client;
import com.powerservice.managermag.anagrafiche.AnagraficheMonoViewModel;
import com.powerservice.managermag.griglia.GrigliaViewModel;
import it.powerservice.managermag.AnagraficheService;
import it.powerservice.managermag.Listini;
import it.powerservice.managermag.ListiniService;
import it.powerservice.managermag.customClass.AnagraficheEstese;
import it.powerservice.managermag.griglia.GridConfService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zhtml.I;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@VariableResolver(DelegatingVariableResolver.class)
public class IndexViewModel extends SelectorComposer<Window> {

    @WireVariable
    AnagraficheService anagraficheService;
    @WireVariable
    ListiniService listiniService;

    @WireVariable
    GridConfService gridConfService;

    @Wire("#searchPanel")
    Div searchPanel;

    @Wire("#search-item-container")
    Div searchItemContainer;

    @Wire("#searchBar")
    Textbox searchBar;

    private int prodottiCount = 0;
    private int anagraficheCount = 0;
    private int listiniCount = 0;

    public int getProdottiCount() {
        return prodottiCount;
    }

    public int getAnagraficheCount() {
        return anagraficheCount;
    }

    public int getListiniCount() {
        return listiniCount;
    }

    private Window window;

    private Boolean isRightSidebarOpen = false;
    private Boolean isLeftSidebarOpen = false;
    private String mainContainerMenuClass = "";

    private String searchValue;
    private String searchMessage;
    private boolean searchPanelVisible = false;
    private boolean allCategoriesClicked = true;
    private boolean anagraficheClicked = false;
    private boolean prodottiClicked = false;
    private boolean listiniClicked = false;

    private  String mainPath = "/zul/anagrafiche/anagrafiche.index.zul";

    public String getMainPath() {
        return mainPath;
    }

    @Command
    @NotifyChange({"mainPath"})
    public void openGriglia(@BindingParam("section") String section) {
        gridConfService.setGridConf(section);
        mainPath = "/zul/griglia/griglia.index.zul";

        var script = "document.querySelector('#gridManager');" + "console.log('INDEX ==> ', gridManager);";
        Clients.evalJavaScript(script);
        GrigliaViewModel.reload();
    }




    @Command
    @NotifyChange({"mainPath"})
    public void openAnagrafiche() {
        mainPath = "/zul/anagrafiche/anagrafiche.index.zul";
    }

    @Command
    public void onGroup() {


    }


    @Init
    public void init() {
    }

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);

    }





    @Command
    @NotifyChange({"isRightSidebarOpen", "mainContainerMenuClass"})
    public void onOpenRightSidebar() {
        isRightSidebarOpen = true;
        mainContainerMenuClass = "right-sidebar-collapsed";
    }
    @Command
    @NotifyChange({"isRightSidebarOpen", "mainContainerMenuClass"})
    public void onCloseRightSidebar() {
        isRightSidebarOpen = false;
        mainContainerMenuClass = "";
    }

    @Command
    @NotifyChange({"isLeftSidebarOpen", "mainContainerMenuClass"})
    public void onToggleLeftSidebar() {
        isLeftSidebarOpen = !isLeftSidebarOpen;
        mainContainerMenuClass = isLeftSidebarOpen ? "left-sidebar-collapsed" : "";

    }

    @Command
    public void onOpenModal(@BindingParam("modal") String modal) {

        var modalUrl = "/zul/" + modal + "/" + modal + ".index.zul";

        Window window = (Window)Executions.createComponents(
                modalUrl, null, null);
        window.doModal();
    }

    public Boolean getIsRightSidebarOpen() {
        return isRightSidebarOpen;
    }

    public Boolean getIsLeftSidebarOpen() {
        return isLeftSidebarOpen;
    }

    public String getMainContainerMenuClass() {
        return mainContainerMenuClass;
    }


    @Command
    @NotifyChange({"searchPanelVisible", "searchMessage", "anagraficheCount", "listiniCount"})
    public void onSearch() throws SQLException {
        if (searchValue != null && searchValue.length() > 2) {
            searchPanelVisible = true;
            searchItemContainer.getChildren().clear();

            var anagraficheList = anagraficheService.getAnagraficheFromSearch(searchValue);
            var listiniList = listiniService.getListiniFromSearch(searchValue);

            anagraficheCount = anagraficheList.size();
            listiniCount = listiniList.size();

            if (anagraficheList.isEmpty() && listiniList.isEmpty()) {
                searchMessage = "Nessun risultato trovato.";
            } else {
                searchMessage = "Tutti i risultati.";
                /*
                getAnagraficheSearchItems(anagraficheList, true);
                 */
                getListiniSearchItems(listiniList, true);
            }
        } else {
            searchPanelVisible = false;
        }

    }


    /*
    public void getAnagraficheSearchItems(List<AnagraficheEstese> anagraficheList, boolean hasLabel) {
        if (anagraficheCount > 0) {
            if (hasLabel) {
                Div mainCategoryContainer = new Div();
                mainCategoryContainer.setStyle("display: flex; align-items: center; margin-bottom: 10px;");

                Label mainCategoryLabel = new Label("Anagrafiche");
                mainCategoryLabel.setWidth("70px");
                mainCategoryLabel.setStyle("font-weight: 700; font-size: 14px; margin-right: 23px;");
                mainCategoryContainer.appendChild(mainCategoryLabel);

                Div mainLine = new Div();
                mainLine.setStyle("width: 200px; height: 1px; background-color: #000;");
                mainCategoryContainer.appendChild(mainLine);

                searchItemContainer.appendChild(mainCategoryContainer);
            }

            Map<String, String> tipoLabelMap = new HashMap<>();
            tipoLabelMap.put("C", "Clienti:");
            tipoLabelMap.put("T", "Trasportatori:");
            tipoLabelMap.put("A", "Agenti:");
            tipoLabelMap.put("F", "Fornitori:");
            tipoLabelMap.put("P", "Personale:");

            Map<String, List<AnagraficheEstese>> anagraficheByType = anagraficheList.stream()
                    .collect(Collectors.groupingBy(AnagraficheEstese::getTipo));

            for (Map.Entry<String, List<AnagraficheEstese>> entry : anagraficheByType.entrySet()) {
                String tipo = entry.getKey();
                List<AnagraficheEstese> anagraficheOfType = entry.getValue();

                String tipoLabel = tipoLabelMap.getOrDefault(tipo, "Sconosciuto");

                Div typeContainer = new Div();
                typeContainer.setStyle("display: flex; align-items: center; margin-left: 15px;");

                // Label del tipo (innestata sotto "Anagrafiche")
                Label typeLabel = new Label(tipoLabel);
                typeLabel.setWidth("fit-content");
                typeLabel.setStyle("font-weight: 600; font-size: 12px; margin-right: 10px;");
                typeContainer.appendChild(typeLabel);

                // Linea accanto alla label del tipo
                Div typeLine = new Div();
                typeLine.setStyle("width: 180px; height: 1px; background-color: #666;");
                typeContainer.appendChild(typeLine);

                searchItemContainer.appendChild(typeContainer);

                // Iteriamo su ciascuna anagrafica del tipo attuale
                for (int i = 0; i < anagraficheOfType.size(); i++) {
                    AnagraficheEstese a = anagraficheOfType.get(i);

                    Div itemDiv = new Div();
                    itemDiv.setClass("search-result");
                    itemDiv.setStyle("margin-left: 25px;");

                    if (i == anagraficheOfType.size() - 1) {
                        itemDiv.setStyle("margin: 0 0 10px 25px;");
                    }

                    I icon = new I();
                    icon.setSclass("fa-solid fa-user");

                    String text = "";

                    if (a.getRagioneSociale() != null) {
                        text += a.getRagioneSociale() + " | " + a.getPIva();
                    } else {
                        text += a.getNome() + " " + a.getCognome() + " | " + a.getPIva();
                    }

                    text += " | " + a.getProvincia() + " | " + a.getComune();

                    // Evidenziamo la parte di testo che corrisponde alla ricerca
                    String highlightedText = getHighlightedText(text, searchValue, "yellow");

                    Html htmlLabel = new Html();
                    htmlLabel.setContent(highlightedText);

                    itemDiv.appendChild(icon);
                    itemDiv.appendChild(htmlLabel);

                    itemDiv.addEventListener(Events.ON_CLICK, clickEvent -> {
                        String script = "localStorage.setItem('idAnagrafica', '" + a.getId() + "');";
                        Clients.evalJavaScript(script);
                        Map<String, Object> params = new HashMap<>();
                        params.put("anagraficaToSave", a);
                        params.put("monoType", "EDIT");
                        params.put("tipoAnagrafica", a.getTipo());
                        AnagraficheMonoViewModel.apriPopup(this, params);
                    });

                    searchItemContainer.appendChild(itemDiv);
                }

            }
        }
        BindUtils.postNotifyChange(null, null, null, "anagraficheCount");
    }
     */





    public void getListiniSearchItems(List<Listini> listiniList, boolean hasLabel) {
        if (listiniCount > 0) {
            if (hasLabel)
            {
                Div categoryContainer = new Div();
                categoryContainer.setStyle("display: flex; align-items: center; margin-bottom: 10px;");

                Label categoryLabel = new Label("Listini");
                categoryLabel.setWidth("65px");
                categoryLabel.setStyle("font-weight: bold; font-size: 14px; margin-right: 23px;"); // Font-size 12px e margine a destra per la linea
                categoryContainer.appendChild(categoryLabel);

                Div line = new Div();
                categoryContainer.appendChild(line);

                searchItemContainer.appendChild(categoryContainer);
            }

            // Iteriamo sulla lista dei listini
            for (Listini a : listiniList) {

                Div itemDiv = new Div();
                itemDiv.setClass("search-result");

                I icon = new I();
                icon.setSclass("fa-solid fa-list");

                String text = a.getNome();

                String highlightedText = getHighlightedText(text, searchValue, "yellow");

                Html htmlLabel = new Html();
                htmlLabel.setContent(highlightedText);

                itemDiv.appendChild(icon);
                itemDiv.appendChild(htmlLabel);

                searchItemContainer.appendChild(itemDiv);

                itemDiv.addEventListener(Events.ON_CLICK, clickEvent -> {
                    onOpenModal("listini");
                });

                searchItemContainer.appendChild(itemDiv);
            }
            }

        }




    @Command
    @NotifyChange({"allCategoriesClicked", "anagraficheClicked", "prodottiClicked", "listiniClicked", "searchMessage", "anagraficheCount", "listiniCount"})
    public void onSectionClicked(@BindingParam("event") Event event, @BindingParam("id") String id) throws SQLException {

        Label label = (Label) event.getTarget().getFellow(id);
        String labelText = label.getValue();

        allCategoriesClicked = false;
        anagraficheClicked = false;
        prodottiClicked = false;
        listiniClicked = false;

        List<AnagraficheEstese> anagraficheList = new ArrayList<>();
        List<Listini> listiniList = new ArrayList<>();


        switch (labelText) {
            case "Tutte le categorie":
                allCategoriesClicked = true;
                searchItemContainer.getChildren().clear();

                anagraficheList = anagraficheService.getAnagraficheFromSearch(searchValue);
                listiniList = listiniService.getListiniFromSearch(searchValue);

                if (anagraficheList.isEmpty() && listiniList.isEmpty()) {

                    searchMessage = "Nessun risultato trovato.";

                } else {
                    searchMessage = "Tutti i risultati.";

                    /*
                    getAnagraficheSearchItems(anagraficheList, true);
                     */

                    getListiniSearchItems(listiniList, true);
                }

                break;

            case "Prodotti":
                prodottiClicked = true;
                break;

            case "Anagrafiche":
                anagraficheClicked = true;
                searchItemContainer.getChildren().clear();

                anagraficheList = anagraficheService.getAnagraficheFromSearch(searchValue);

                if (anagraficheList.isEmpty()) {

                    searchMessage = "Nessun risultato trovato.";

                } else {
                    searchMessage = "Tutti i risultati.";

                    /*
                    getAnagraficheSearchItems(anagraficheList, false);
                     */
                }


                break;

            case "Listini":
                listiniClicked = true;
                searchItemContainer.getChildren().clear();
                listiniList = listiniService.getListiniFromSearch(searchValue);

                if (listiniList.isEmpty()) {

                    searchMessage = "Nessun risultato trovato.";

                } else {
                    searchMessage = "Tutti i risultati.";

                    getListiniSearchItems(listiniList, false);
                }
                break;
            default:
                break;
        }
    }

    @Command
    @NotifyChange({"searchPanelVisible", "searchValue"})
    public void onCloseSearchPanel()
    {
        this.searchPanelVisible = false;
        this.searchValue = "";
    }


    private String getHighlightedText(String text, String parametro, String color) {
        if (parametro == null || parametro.isEmpty()) {
            return text;
        }
        return text.replaceAll("(?i)(" + Pattern.quote(parametro) + ")", "<span style='background-color:" + color + "'>$1</span>");
    }



    public String getSearchValue() {
        return searchValue;
    }
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public boolean isSearchPanelVisible() {
        return searchPanelVisible;
    }
    public String getSearchMessage() {
        return searchMessage;
    }

    public boolean isAllCategoriesClicked() {
        return allCategoriesClicked;
    }

    public boolean isAnagraficheClicked() {
        return anagraficheClicked;
    }

    public boolean isProdottiClicked() {
        return prodottiClicked;
    }
    public boolean isListiniClicked() {
        return listiniClicked;
    }

    @Command
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }

    public boolean anagraficheCountGreaterThanZero()
    {
        return anagraficheCount > 0;
    }


}