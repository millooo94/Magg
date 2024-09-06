package com.powerservice.managermag.marche.utilities;
import com.powerservice.managermag.marche.MarcheIndexViewModel;
import com.powerservice.managermag.marketplace.MarketplaceIndexViewModel;
import com.powerservice.managermag.utilities.ModalCloseListener;
import org.zkoss.zk.ui.event.Event;

public class NewMarcheCloseListener extends ModalCloseListener<MarcheIndexViewModel> {

    public NewMarcheCloseListener(MarcheIndexViewModel viewModel) {
        super(viewModel);
    }

    @Override
    protected void handleEvent(MarcheIndexViewModel viewModel, Event event) throws Exception {
        viewModel.setButtonsVisibility(true, false, false, false, true);
    }
}
