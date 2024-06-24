package com.powerservice.managermag;

import it.powerservice.managermag.ImpostazioniGridService;
import it.powerservice.managermag.customClass.CustomImpostazioniRow;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class SettingsModalViewModel {
    @WireVariable
    ImpostazioniGridService impostazioniGridService;

    List<CustomImpostazioniRow> impostazioniRows;

    @Init
    public void init(@ContextParam(ContextType.COMPONENT) Window w) throws Exception {
        impostazioniRows = impostazioniGridService.getImpostazioniRows();
    }
}
