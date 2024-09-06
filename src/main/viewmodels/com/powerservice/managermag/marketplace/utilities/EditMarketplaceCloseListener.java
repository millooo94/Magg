package com.powerservice.managermag.marketplace.utilities;
import com.powerservice.managermag.listini.ListiniIndexViewModel;
import com.powerservice.managermag.marketplace.MarketplaceIndexViewModel;
import com.powerservice.managermag.utilities.ModalCloseListener;
import org.zkoss.zk.ui.event.Event;

public class EditMarketplaceCloseListener extends ModalCloseListener<MarketplaceIndexViewModel> {

    public EditMarketplaceCloseListener(MarketplaceIndexViewModel viewModel) {
        super(viewModel);
    }

    @Override
    protected void handleEvent(MarketplaceIndexViewModel viewModel, Event event) throws Exception {
        viewModel.setButtonDisabled(false);
    }
}
