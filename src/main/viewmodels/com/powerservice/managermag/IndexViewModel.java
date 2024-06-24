package com.powerservice.managermag;

import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver(DelegatingVariableResolver.class)
public class IndexViewModel {



    @Command
    public void onOpenModal() {
        Window window = (Window)Executions.createComponents(
                "/settingsModal.zul", null, null);
        window.doModal();
    }
}