package com.powerservice.managermag;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class VariantsModalCloseListener implements EventListener<Event> {
    VariantiModalViewModel vmvm;

    public VariantsModalCloseListener(VariantiModalViewModel vmvm) {
        this.vmvm = vmvm;

    }

    @Override
    public void onEvent(Event event) throws Exception {
        vmvm.refresh();
    }

}
