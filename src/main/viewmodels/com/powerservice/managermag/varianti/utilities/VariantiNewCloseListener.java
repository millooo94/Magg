package com.powerservice.managermag.varianti.utilities;

import com.powerservice.managermag.varianti.VariantiIndexViewModel;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class VariantiNewCloseListener implements EventListener<Event> {
    VariantiIndexViewModel vmvm;

    public VariantiNewCloseListener(VariantiIndexViewModel vmvm) {
        this.vmvm = vmvm;

    }

    @Override
    public void onEvent(Event event) throws Exception {
        vmvm.refresh();
    }

}
