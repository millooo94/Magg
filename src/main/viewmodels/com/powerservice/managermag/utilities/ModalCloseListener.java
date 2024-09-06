package com.powerservice.managermag.utilities;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public abstract class ModalCloseListener<T> implements EventListener<Event> {
    protected T viewModel;

    public ModalCloseListener(T viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onEvent(Event event) throws Exception {
        handleEvent(viewModel, event);
    }

    protected abstract void handleEvent(T viewModel, Event event) throws Exception;
}
