package com.powerservice.managermag.depositi.utilities;

import com.powerservice.managermag.depositi.DepositiIndexViewModel;
import com.powerservice.managermag.utilities.ModalCloseListener;
import org.zkoss.zk.ui.event.Event;

public class DepositiModalCloseListener extends ModalCloseListener<DepositiIndexViewModel> {

    public DepositiModalCloseListener(DepositiIndexViewModel viewModel) {
        super(viewModel);
    }

    @Override
    protected void handleEvent(DepositiIndexViewModel viewModel, Event event) throws Exception {
        viewModel.setNewButtonDisabled(false);
    }
}
