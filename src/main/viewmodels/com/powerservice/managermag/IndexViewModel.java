package com.powerservice.managermag;

import org.apache.commons.io.LineIterator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver(DelegatingVariableResolver.class)
public class IndexViewModel {
    public String zulSezione = "";
    public String versioneApplicazione ="V1.0.0";

    @Init
    void init(@ContextParam(ContextType.COMPONENT) Window w) {

    }

    public String getZulSezione() {
        return zulSezione;
    }

    public void setZulSezione(String zulSezione) {
        this.zulSezione = zulSezione;
    }

    public String getVersioneApplicazione() {
        return versioneApplicazione;
    }

    public void setVersioneApplicazione(String versioneApplicazione) {
        this.versioneApplicazione = versioneApplicazione;
    }

    @Command
    public static Window apriParametriPopup() {
        Window w = (Window) Executions.createComponents("/sezioni/parametri.zul", null, null);
        //Window w = (Window) Executions.createComponents("sezioni/parametri.zul", null, null);
        w.doModal();
        return w;
    }
}