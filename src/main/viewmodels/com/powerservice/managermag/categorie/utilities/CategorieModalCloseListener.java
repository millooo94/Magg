package com.powerservice.managermag.categorie.utilities;

import com.powerservice.managermag.categorie.CategorieIndexViewModel;
import com.powerservice.managermag.utilities.ModalCloseListener;
import org.zkoss.zk.ui.event.Event;

public class CategorieModalCloseListener extends ModalCloseListener<CategorieIndexViewModel> {

    public CategorieModalCloseListener(CategorieIndexViewModel viewModel) {
        super(viewModel);
    }

    @Override
    protected void handleEvent(CategorieIndexViewModel viewModel, Event event) throws Exception {
        viewModel.refreshTreeModel();
    }
}
