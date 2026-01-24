package com.powerservice.managermag.categorie;

import com.powerservice.managermag.anagrafiche.AnagraficheIndexViewModel;
import it.powerservice.managermag.Categorie;
import it.powerservice.managermag.CategorieService;
import it.powerservice.managermag.enums.ActionType;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.sql.SQLException;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class CategorieShowViewModel {

    private static CategorieIndexViewModel categorieIndexViewModel;

    @WireVariable
    CategorieService categorieService;
    private Long parentCategoryId;
    private String codice;
    private String descrizione;
    ActionType action = ActionType.CREATE;
    private Window window;


    @Init
    void init() throws SQLException {
        Map<?, ?> args = Executions.getCurrent().getArg();
        Long parentCategoryId = (Long) args.get("parentCategoryId");
        ActionType action = (ActionType) args.get("action");
        setParentCategoryId(parentCategoryId);
        setAction(action);
        if (action == ActionType.UPDATE) {
            Categorie categoryToUpdate = categorieService.getCategoriaById(parentCategoryId);
            setCodice(categoryToUpdate.getCodice());
            setDescrizione(categoryToUpdate.getDescrizione());
        }
    }

    @Command
    public void saveCategory() {
        if (action == ActionType.UPDATE) {
            categorieService.updateCategory(codice, descrizione, parentCategoryId);
        } else {
            categorieService.createCategory(codice, descrizione, parentCategoryId);
        }
        if (window != null) {
            categorieIndexViewModel.refreshTreeModel();
            window.detach();
        }
    }

    static Window apriPopup(CategorieIndexViewModel parentModel, Map<String, Object> params, String action, Long currentCategoryContextMenu) {
        categorieIndexViewModel = parentModel;
        Window window = (Window) Executions.createComponents(
                "categorie/categorie.show.zul", null, params);
        window.setAttribute("action", action);
        window.setAttribute("parentCategoryId", currentCategoryContextMenu);

        return window;
    }

    @Command
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }


    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }
}
