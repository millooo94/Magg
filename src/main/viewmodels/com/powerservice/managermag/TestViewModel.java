package com.powerservice.managermag;

import it.powerservice.managermag.ImpostazioniCampi;
import it.powerservice.managermag.ImpostazioniCampiService;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.cdi.DelegatingVariableResolver;

import java.awt.*;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class TestViewModel {
    Window self;
    List<ImpostazioniCampi> impostazioniCampi;
    @WireVariable
    ImpostazioniCampiService impostazioniCampiService;

    @Init
    public void init() {
        var response = this.impostazioniCampiService.getImpostazioniCampi();
        impostazioniCampi.addAll(response);
    }


}
