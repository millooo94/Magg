package com.powerservice.managermag;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class CategoriesModalCloseListener implements EventListener<Event> {
    CategoriesModalViewModel cmvm;

    public CategoriesModalCloseListener(CategoriesModalViewModel cmvm) {
        this.cmvm = cmvm;

    }

    @Override
    public void onEvent(Event event) throws Exception {
        cmvm.refreshTreeModel();
    }

}
