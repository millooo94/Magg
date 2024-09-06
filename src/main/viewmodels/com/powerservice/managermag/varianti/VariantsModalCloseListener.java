package com.powerservice.managermag.varianti;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class VariantsModalCloseListener implements EventListener<Event> {
    VariantiIndexViewModel vmvm;

    public VariantsModalCloseListener(VariantiIndexViewModel vmvm) {
        this.vmvm = vmvm;

    }

    @Override
    public void onEvent(Event event) throws Exception {
        vmvm.refresh();
    }

}
