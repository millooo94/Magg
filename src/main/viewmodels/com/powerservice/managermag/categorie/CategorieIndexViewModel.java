package com.powerservice.managermag.categorie;
import com.powerservice.managermag.categorie.utilities.CategorieModalCloseListener;
import com.powerservice.managermag.categorie.utilities.CategorieTreeNode;
import it.powerservice.managermag.*;
import it.powerservice.managermag.enums.ActionType;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

@VariableResolver(DelegatingVariableResolver.class)
public class CategorieIndexViewModel {
    @WireVariable
    CategorieService categorieService;
    @WireVariable
    CategorieRifService categorieRifService;
    private Categorie categoryRoot;
    private Long currentCategoryId = null;

    @Wire("#treeRootChildren")
    private Treechildren treeRootChildren;

    @Init
    public void init() throws SQLException {
        categoryRoot = categorieService.getCategoriesRoot();
    }

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws SQLException {
        Selectors.wireComponents(view, this, false);
        loadCategories(11L, treeRootChildren);
    }

    private void loadCategories(Long parentId, Treechildren parentTreeChildren) throws SQLException {
        var rootChildren = categorieService.getChildrenCategories(parentId);
        for (Categorie c : rootChildren) {
            Treeitem treeItem = new Treeitem();
            treeItem.setOpen(false);
            Treerow treeRow = new Treerow();
            Treecell treeCell = new Treecell();
            treeCell.setLabel(c.getDescrizione());
            treeCell.setId(c.getId().toString());
            treeRow.appendChild(treeCell);
            treeItem.appendChild(treeRow);
            parentTreeChildren.appendChild(treeItem);

            treeItem.setDraggable("true");
            treeItem.setDroppable("true");

            treeItem.addEventListener("onDrop", event -> {

                CategorieRif categoriaRif = null;

                Component draggedComponent = ((DropEvent) event).getDragged();

                if (draggedComponent instanceof Treeitem) {
                    Treeitem draggedItem = (Treeitem) draggedComponent;

                    Treerow draggedRow = draggedItem.getTreerow();
                    if (draggedRow != null) {
                        Treecell draggedCell = (Treecell) draggedRow.getFirstChild();
                        String draggedLabel = draggedCell.getLabel();
                        long draggedId = Long.parseLong(draggedCell.getId());
                        categoriaRif = categorieRifService.getCategoriaRifFromIdCategoriaArrivo(draggedId);
                        System.out.println("CATEGORIA RIF " + categoriaRif);
                        System.out.println("Label of dragged Treecell: " + draggedLabel + " " + draggedId);
                    }
                }
                Component targetComponent = event.getTarget();
                if (targetComponent instanceof Treeitem) {
                    Treeitem targetItem = (Treeitem) targetComponent;
                    Treerow targetRow = targetItem.getTreerow();
                    if(targetRow != null) {
                        Treecell targetCell = (Treecell) targetRow.getFirstChild();
                        String targetLabel = targetCell.getLabel();
                        long targetId = Long.parseLong(targetCell.getId());
                        if (categoriaRif != null) {
                            categoriaRif.setIdCategoriaPartenza(targetId);
                            categorieRifService.updateCategorieRif(categoriaRif);
                        }
                        System.out.println("Label of target Treecell: " + targetLabel + " " + targetId);
                        refreshTreeModel();
                    }
                }
            });

            if (categorieRifService.checkIdPartenzaExists(c.getId())) {
                Treechildren treeChildren = new Treechildren();
                treeItem.appendChild(treeChildren);

                treeItem.addEventListener("onOpen", event -> {
                    Treeitem sourceItem = (Treeitem) event.getTarget();
                    Treechildren children = sourceItem.getTreechildren();

                    if (children != null && children.getChildren().isEmpty()) {
                        try {
                            loadCategories(c.getId(), children);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            treeItem.addEventListener("onClick", event -> {
                currentCategoryId = c.getId();
                BindUtils.postNotifyChange(null, null, this, "currentCategoryId");
            });

        }
    }

    public void onRootCategoryDrop(@BindingParam("event") DropEvent event) throws SQLException {
        CategorieRif categoriaRif = null;
        Component draggedComponent = ((DropEvent) event).getDragged();
        Treeitem draggedItem = (Treeitem) draggedComponent;
        Treerow draggedRow = draggedItem.getTreerow();
        Treecell draggedCell = (Treecell) draggedRow.getFirstChild();
        long draggedId = Long.parseLong(draggedCell.getId());
        categoriaRif = categorieRifService.getCategoriaRifFromIdCategoriaArrivo(draggedId);

        if (categoriaRif != null) {
            categoriaRif.setIdCategoriaPartenza(categoryRoot.getId());
            categorieRifService.updateCategorieRif(categoriaRif);
            refreshTreeModel();
        }
    }
    public void onRootCategoryClicked()  {
        currentCategoryId = categoryRoot.getId();
        BindUtils.postNotifyChange(null, null, this, "currentCategoryId");
    }





    @Command
    public void onCreateOrUpdateCategory(@BindingParam("action") String action) {
        ActionType actionType;
        try {
            actionType = ActionType.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            actionType = null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("parentCategoryId", currentCategoryId);
        params.put("action", actionType);

        CategorieShowViewModel.apriPopup(this, params, action, currentCategoryId).addEventListener(Events.ON_CLOSE, new CategorieModalCloseListener(this));
    }

    public void deleteCategory() {
        categorieService.deleteCategory(currentCategoryId);
        refreshTreeModel();
    }


    public void refreshTreeModel() {
        treeRootChildren.getChildren().clear();
        try {
            loadCategories(11L, treeRootChildren);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BindUtils.postNotifyChange(null, null, this, "treeRootChildren"); // Notifica il binding
    }
    @Command
    public void onDeleteCategory() {
        refreshTreeModel();
    }

    public Categorie getCategoryRoot() {
        return categoryRoot;
    }

    public Long getCurrentCategoryId() {
        return currentCategoryId;
    }
}



