package com.powerservice.managermag.varianti;

import com.powerservice.managermag.tipologiePagamenti.TipologiePagamentiIndexViewModel;
import it.powerservice.managermag.VariantiRifService;
import it.powerservice.managermag.VariantiService;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.sql.SQLException;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class VariantiNewViewModel {
    private Window window;
    @WireVariable
    VariantiService variantiService;
    @WireVariable
    VariantiRifService variantiRifService;
    private String descrizione;
    private String descrizioneEng;
    private String tipo;
    private Long idVariantePartenza;
    private static VariantiIndexViewModel variantiIndexViewModel;

    @Init
    void init() {
        setTipo((String) Executions.getCurrent().getArg().get("type"));
        setIdVariantePartenza((Long) Executions.getCurrent().getArg().get("idVariantePartenza"));
    }

    @Command
    @NotifyChange({"descrizione", "descrizioneEng", "tipo", "idVariantePartenza"})
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }

    static Window apriPopup(VariantiIndexViewModel parentModel ,Map<String, Object> params) {
        variantiIndexViewModel = parentModel;
        Window window = (Window) Executions.createComponents(
                "varianti/varianti.new.zul", null, params);
        window.setAttribute("type", params.get("type"));
        window.setAttribute("type", params.get("idVariantePartenza"));
        return window;
    }

    @Command
    public void saveVariant() throws SQLException {
        variantiService.createVariant(descrizione, descrizioneEng, tipo, idVariantePartenza);
        System.out.println("SIAMO QUI");
        if (window != null) {
            variantiIndexViewModel.refresh();
            window.detach();
        }
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getDescrizioneEng() {
        return descrizioneEng;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setDescrizioneEng(String descrizioneEng) {
        this.descrizioneEng = descrizioneEng;
    }

    public void setIdVariantePartenza(Long idVariantePartenza) {
        this.idVariantePartenza = idVariantePartenza;
    }
}
