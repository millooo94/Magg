package com.powerservice.managermag.anagrafiche.utilities;
import com.powerservice.managermag.anagrafiche.AnagraficheIndexViewModel;
import com.powerservice.managermag.marche.MarcheIndexViewModel;
import com.powerservice.managermag.utilities.ModalCloseListener;
import org.zkoss.zk.ui.event.Event;

public class AnagraficheMonoCloseListener extends ModalCloseListener<AnagraficheIndexViewModel> {

    public AnagraficheMonoCloseListener(AnagraficheIndexViewModel viewModel) {
        super(viewModel);
    }

    @Override
    protected void handleEvent(AnagraficheIndexViewModel viewModel, Event event) throws Exception {
        viewModel.setEditButtonDisabled(true);
        viewModel.setRemoveButtonDisabled(true);
        viewModel.setSelectedAnagrafica(null);
    }
}
