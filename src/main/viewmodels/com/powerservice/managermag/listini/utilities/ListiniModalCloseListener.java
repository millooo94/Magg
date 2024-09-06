package com.powerservice.managermag.listini.utilities;
import com.powerservice.managermag.listini.ListiniIndexViewModel;
import com.powerservice.managermag.utilities.ModalCloseListener;
import org.zkoss.zk.ui.event.Event;

public class ListiniModalCloseListener extends ModalCloseListener<ListiniIndexViewModel> {

    public ListiniModalCloseListener(ListiniIndexViewModel viewModel) {
        super(viewModel);
    }

    @Override
    protected void handleEvent(ListiniIndexViewModel viewModel, Event event) throws Exception {
        viewModel.setButtonDisabled(false);
    }
}
