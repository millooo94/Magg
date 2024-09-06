package com.powerservice.managermag.categorie.utilities;

import it.powerservice.managermag.Categorie;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;

import java.util.LinkedList;

public class CategorieTreeNode extends DefaultTreeNode<Categorie> {
    private static final long serialVersionUID = 1L;
    Boolean isOpen = false;

    public CategorieTreeNode(Categorie category) {
        super(category, new LinkedList<TreeNode<Categorie>>());
    }
    public Long getId() {
        return getData().getId();
    }
    public String getDescrizione() {
        return getData().getDescrizione();
    }
    public boolean isLeaf() {
        return getData() != null && getData().getNumeroFigli() != null &&  getData().getNumeroFigli() == 0;
    }
    public Boolean getIsOpen() {
        return isOpen;
    }
    public void setIsOpen(Boolean open) {
        isOpen = open;
    }

}
