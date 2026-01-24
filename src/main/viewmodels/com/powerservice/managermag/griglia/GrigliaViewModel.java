package com.powerservice.managermag.griglia;


import it.powerservice.managermag.griglia.GridColumnsService;
import it.powerservice.managermag.griglia.GridColumn;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class GrigliaViewModel extends SelectorComposer<Window> {

    public static void reload() {
        var script = "gridManager.src = '/ManagerMag/angular/grid-manager/browser/index.html '";
        Clients.evalJavaScript(script);
    }

    @Init
    public void init() {
    }
}
