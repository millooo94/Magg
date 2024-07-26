package com.powerservice.managermag;

import it.powerservice.managermag.Categorie;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CategoryTreeNode extends DefaultTreeNode<Categorie> {
    private static final long serialVersionUID = 1L;
    Boolean isOpen = false;

    public CategoryTreeNode(Categorie category) {
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
