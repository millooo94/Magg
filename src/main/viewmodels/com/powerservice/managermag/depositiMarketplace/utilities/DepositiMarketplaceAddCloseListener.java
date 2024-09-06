package com.powerservice.managermag.depositiMarketplace.utilities;
import com.powerservice.managermag.depositiMarketplace.DepositiMarketplaceIndexViewModel;
import com.powerservice.managermag.marche.MarcheIndexViewModel;
import com.powerservice.managermag.utilities.ModalCloseListener;
import org.zkoss.zk.ui.event.Event;

public class DepositiMarketplaceAddCloseListener extends ModalCloseListener<DepositiMarketplaceIndexViewModel> {

    public DepositiMarketplaceAddCloseListener(DepositiMarketplaceIndexViewModel viewModel) {
        super(viewModel);
    }

    @Override
    protected void handleEvent(DepositiMarketplaceIndexViewModel viewModel, Event event) throws Exception {
    }
}
