package com.powerservice.managermag;

import com.powerservice.managermag.anagrafiche.AnagraficheMonoViewModel;
import com.powerservice.managermag.anagrafiche.utilities.AnagraficheMonoCloseListener;
import it.powerservice.managermag.Anagrafiche;
import it.powerservice.managermag.AnagraficheService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zhtml.I;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

import java.util.*;
import java.util.regex.Pattern;

@VariableResolver(DelegatingVariableResolver.class)
public class IndexViewModel extends SelectorComposer<Window> {

    @WireVariable
    AnagraficheService anagraficheService;

    @Wire("#search-item-container")
    Div searchItemContainer;

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


    @Init
    public void init() {
        System.out.println("ciao");
    }


    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        System.out.println("container ==> " + searchItemContainer);
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
    @NotifyChange({"searchPanelVisible", "searchMessage"})
    public void onSearch() {
        if (searchValue.length() > 1) {
            searchPanelVisible = true;
            searchItemContainer.getChildren().clear();
            var list = anagraficheService.getAnagraficheFromSearch(searchValue);

            if (list.isEmpty()) {
                searchMessage = "Nessun risultato trovato.";
            } else {
                searchMessage = "Tutti i risultati.";
                for (Anagrafiche a : list) {

                    Div itemDiv = new Div();
                    itemDiv.setClass("search-result");

                    I icon = new I();
                    icon.setSclass("fa-solid fa-download");

                    String text = a.getNome() + " " + a.getCognome() + " | " + a.getRagioneSociale();
                    String highlightedText = getHighlightedText(text, searchValue, "yellow");

                    Html htmlLabel = new Html();
                    htmlLabel.setContent(highlightedText);

                    itemDiv.appendChild(icon);
                    itemDiv.appendChild(htmlLabel);

                    Map<String, Object> params = new HashMap<>();
                    params.put("anagrafica", list.get(0));


                    itemDiv.addEventListener(Events.ON_CLICK, event -> {
                        AnagraficheMonoViewModel.apriPopup(this, params);
                    });

                    searchItemContainer.appendChild(itemDiv);

                }
            }
        } else {
            searchPanelVisible = false;
        }
    }



    @Command
    @NotifyChange({"allCategoriesClicked", "anagraficheClicked", "prodottiClicked", "searchMessage"})
    public void onSectionClicked(@BindingParam("event") Event event, @BindingParam("id") String id) {
        Label label = (Label) event.getTarget().getFellow(id);
        String labelText = label.getValue();
        System.out.println(labelText);

        allCategoriesClicked = false;
        anagraficheClicked = false;
        prodottiClicked = false;

        List<Anagrafiche> list = new ArrayList<Anagrafiche>();

        switch (labelText) {
            case "Tutte le categorie":
                allCategoriesClicked = true;
                searchItemContainer.getChildren().clear();
                list = anagraficheService.getAnagraficheFromSearch(searchValue);

                if (list.isEmpty()) {
                    searchMessage = "Nessun risultato trovato.";
                } else {
                    searchMessage = "Tutti i risultati.";
                    for (Anagrafiche a : list) {

                        Div itemDiv = new Div();
                        itemDiv.setClass("search-result");

                        I icon = new I();
                        icon.setSclass("fa-solid fa-download");

                        String text = a.getNome() + " " + a.getCognome() + " | " + a.getRagioneSociale();
                        String highlightedText = getHighlightedText(text, searchValue, "yellow");

                        Html htmlLabel = new Html();
                        htmlLabel.setContent(highlightedText); // Imposta il contenuto HTML evidenziato

                        itemDiv.appendChild(icon);
                        itemDiv.appendChild(htmlLabel);

                        searchItemContainer.appendChild(itemDiv);
                    }
                }
                break;
            case "Prodotti":
                prodottiClicked = true;
                break;
            case "Anagrafiche":
                anagraficheClicked = true;
                searchItemContainer.getChildren().clear();
                list = anagraficheService.getAnagraficheFromSearch(searchValue);

                if (list.isEmpty()) {
                    searchMessage = "Nessun risultato trovato.";
                } else {
                    searchMessage = "Risultati per anagrafiche.";
                    for (Anagrafiche a : list) {

                        Div itemDiv = new Div();
                        itemDiv.setClass("search-result");

                        I icon = new I();
                        icon.setSclass("fa-solid fa-download");

                        String text = a.getNome() + " " + a.getCognome() + " | " + a.getRagioneSociale();
                        String highlightedText = getHighlightedText(text, searchValue, "yellow");

                        Html htmlLabel = new Html();
                        htmlLabel.setContent(highlightedText); // Imposta il contenuto HTML evidenziato

                        itemDiv.appendChild(icon);
                        itemDiv.appendChild(htmlLabel);

                        searchItemContainer.appendChild(itemDiv);
                    }
                }
                break;
            default:
                break;
        }
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

    @Command
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }
}