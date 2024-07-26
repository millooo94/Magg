package com.powerservice.managermag;
import it.powerservice.managermag.Categorie;
import it.powerservice.managermag.CategorieRepository;
import it.powerservice.managermag.CategorieService;
import it.powerservice.managermag.enums.ActionType;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

import java.sql.SQLException;
import java.util.*;

@VariableResolver(DelegatingVariableResolver.class)
public class CategoriesModalViewModel {
    private static final long serialVersionUID = 1L;
    @WireVariable
    CategorieService categorieService;
    @WireVariable
    CategorieRepository categorieRepository;
    @Wire
    private Tree tree = new Tree();
    private TreeModel<TreeNode<Categorie>> categoriesTreeModel;
    private CategoryTreeNode rootNode;
    private Categorie categoryRoot;
    private Long currentCategoryContextMenu;

    @Init
    public void init() throws SQLException {
        categoryRoot = categorieService.getCategoriesRoot();
        rootNode = constructCategoryTreeNode(categoryRoot);
        categoryRoot.addEmptyChild();
        categoriesTreeModel = new DefaultTreeModel<Categorie>(rootNode);
        ((DefaultTreeModel<Categorie>)categoriesTreeModel).addOpenPath(new int[]{0});
    }
    private CategoryTreeNode constructCategoryTreeNode(Categorie categoryRoot) {
        CategoryTreeNode categoryNode = new CategoryTreeNode(categoryRoot);
        LinkedList<CategoryTreeNode> queue = new LinkedList<>();
        queue.add(categoryNode);
        while(!queue.isEmpty()) {
            CategoryTreeNode node = queue.remove();
            for (Categorie childCategory: node.getData().getChildren()) {
                CategoryTreeNode child = new CategoryTreeNode(childCategory);
                node.add(child);
                queue.add(child);
            }
        }
        CategoryTreeNode rootNode = new CategoryTreeNode(null);
        rootNode.add(categoryNode);
        return rootNode;
    };
    @Command
    public void onOpenNode(@BindingParam("event") OpenEvent event, @BindingParam("node") CategoryTreeNode node) throws SQLException {
        Treeitem treeitem = (Treeitem) event.getTarget();
        boolean isOpen = treeitem.isOpen();
        if (isOpen) {
            List<Categorie> children = categorieService.getChildrenCategories(node.getData().getId());
            for (Categorie category : children) {
                CategoryTreeNode childNode = new CategoryTreeNode(category);
                node.add(childNode);
            }
            tree.setModel(categoriesTreeModel);
        } else {
            node.getChildren().clear();
        }
        tree.setModel(categoriesTreeModel);
    }
    @Command
    public void onRightClick(@BindingParam("event") Event event, @BindingParam("node") CategoryTreeNode node) {
        currentCategoryContextMenu = node.getData().getId();
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
        params.put("parentCategoryId", currentCategoryContextMenu);
        params.put("action", actionType);

        CategoryContextMenuModalViewModel.apriPopup(params, action, currentCategoryContextMenu).addEventListener(Events.ON_CLOSE, new PopupClosListener(this));
    }
    @Command
    public void onDeleteCategory() {
        categorieRepository.deleteById(currentCategoryContextMenu);
        refreshTreeModel();
    }


    @Command
    public void test(@BindingParam("event") DropEvent event, @BindingParam("droppedNode") CategoryTreeNode droppedNode) throws SQLException {
        CategoryTreeNode draggedNode = (CategoryTreeNode) ((Treeitem) event.getDragged()).getValue();
        categorieService.updateCategorieRif(droppedNode.getId(), draggedNode.getId());
        BindUtils.postNotifyChange(null, null, this, "categoriesModel");
        refreshTreeModel();
    }

    public TreeModel<TreeNode<Categorie>> getCategoriesTreeModel() {
        return categoriesTreeModel;
    }

    public void refreshTreeModel() {
        tree.setModel(categoriesTreeModel);
        BindUtils.postNotifyChange(null, null, this, "categoriesTreeModel");
    }


}


