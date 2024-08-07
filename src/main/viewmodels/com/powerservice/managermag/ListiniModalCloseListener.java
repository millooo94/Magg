package com.powerservice.managermag;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class ListiniModalCloseListener implements EventListener<Event> {
    ListiniModalViewModel listiniModalViewModel;

    public ListiniModalCloseListener(ListiniModalViewModel listiniModalViewModel) {
        this.listiniModalViewModel = listiniModalViewModel;
    }


    @Override
    public void onEvent(Event event) throws Exception {
        listiniModalViewModel.setButtonDisabled(false);
    }
}
