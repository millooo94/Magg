package com.powerservice.managermag.anagrafiche;

import com.powerservice.managermag.anagrafiche.utilities.AnagraficheMonoCloseListener;
import com.powerservice.managermag.anagrafiche.utilities.General;
import it.powerservice.managermag.Anagrafiche;
import it.powerservice.managermag.AnagraficheService;
import it.powerservice.managermag.customClass.CodDesc;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class AnagraficheIndexViewModel {

    @WireVariable
    AnagraficheService anagraficheService;
    private List<CodDesc> tipiAnagrafica = new ArrayList<>();
    private List<Anagrafiche> anagrafiche = new ArrayList<>();
    private Anagrafiche selectedAnagrafica = null;
    private Integer selectedTipoAnagraficaIndex = 0;
    private Boolean createButtonDisabled = false;
    private Boolean editButtonDisabled = true;
    private Boolean removeButtonDisabled = true;

    private boolean ckClienti = false;
    private boolean ckFornitori = false;
    private boolean ckTrasportatori = true;
    private boolean ckAgenti = false;
    private boolean ckPersonale = false;

    @Init
    public void init() {
        tipiAnagrafica = General.getTipiAnagrafica();
        initAnagrafiche(ckClienti, ckFornitori, ckTrasportatori, ckAgenti, ckPersonale);
    }

    @Command
    @NotifyChange({"anagrafiche"})
    public void onCheckTipoAnagrafica() {
        initAnagrafiche(ckClienti, ckFornitori, ckTrasportatori, ckAgenti, ckPersonale);
    }
    @Command
    @NotifyChange({"selectedAnagrafica", "editButtonDisabled", "removeButtonDisabled"})
    public void onClickAnagrafica(@BindingParam("anagrafica") Anagrafiche anagrafica) {
        selectedAnagrafica = anagrafica;
        editButtonDisabled = false;
        removeButtonDisabled = false;
    }

    @Command
    @NotifyChange({"anagrafiche", "removeButtonDisabled"})
    public void onDeleteAnagrafica() {
        selectedAnagrafica.setEliminato(true);
        anagraficheService.saveAnagrafica(selectedAnagrafica);
        removeButtonDisabled = true;
        initAnagrafiche(ckClienti, ckFornitori, ckTrasportatori, ckPersonale, ckAgenti);
    }

    @Command
    public void onOpenAnagraficheMono(@BindingParam("type") String actionType) {
        Map<String, Object> params = new HashMap<>();
        switch (actionType) {
            case "EDIT":
                params.put("anagraficaToSave", selectedAnagrafica);
                params.put("monoType", "EDIT");
                /*
                params.put("tipoAnagrafica", selectedAnagrafica.getTipo());
                */
                break;
            case "CREATE":
                params.put("anagraficaToSave", new Anagrafiche());
                params.put("monoType", "CREATE");
                params.put("tipoAnagrafica", tipiAnagrafica.get(selectedTipoAnagraficaIndex).getCodice());
                break;
        }
        String script = "localStorage.removeItem('idAnagrafica');";
        Clients.evalJavaScript(script);
        AnagraficheMonoViewModel.apriPopup(this, params).addEventListener(Events.ON_CLOSE, new AnagraficheMonoCloseListener(this));
    }

    public void initAnagrafiche(boolean ckClienti, boolean ckFornitori, boolean ckTrasportatori, boolean ckAgenti, boolean ckPersonale) {
        anagrafiche = anagraficheService.getAnagraficheFromTipo(ckClienti, ckFornitori, ckTrasportatori, ckAgenti, ckPersonale);
        BindUtils.postNotifyChange(null, null, this, "anagrafiche");
    }

    public List<CodDesc> getTipiAnagrafica() {
        return tipiAnagrafica;
    }

    public void setSelectedAnagrafica(Anagrafiche selectedAnagrafica) {
        this.selectedAnagrafica = selectedAnagrafica;
        BindUtils.postNotifyChange(null, null, this, "selectedAnagrafica");
    }

    public Integer getSelectedTipoAnagraficaIndex() {
        return selectedTipoAnagraficaIndex;
    }

    public void setSelectedTipoAnagraficaIndex(Integer selectedTipoAnagraficaIndex) {
        this.selectedTipoAnagraficaIndex = selectedTipoAnagraficaIndex;
    }

    public List<Anagrafiche> getAnagrafiche() {
        return anagrafiche;
    }

    public Boolean getRemoveButtonDisabled() {
        return removeButtonDisabled;
    }

    public Boolean getEditButtonDisabled() {
        return editButtonDisabled;
    }

    public Boolean getCreateButtonDisabled() {
        return createButtonDisabled;
    }

    public void setEditButtonDisabled(Boolean editButtonDisabled) {
        this.editButtonDisabled = editButtonDisabled;
        BindUtils.postNotifyChange(null, null, this, "editButtonDisabled");
    }

    public void setRemoveButtonDisabled(Boolean removeButtonDisabled) {
        this.removeButtonDisabled = removeButtonDisabled;
        BindUtils.postNotifyChange(null, null, this, "removeButtonDisabled");
    }


    public boolean isCkClienti() {
        return ckClienti;
    }

    public boolean isCkFornitori() {
        return ckFornitori;
    }

    public boolean isCkTrasportatori() {
        return ckTrasportatori;
    }

    public boolean isCkAgenti() {
        return ckAgenti;
    }

    public boolean isCkPersonale() {
        return ckPersonale;
    }

    public void setCkClienti(boolean ckClienti) {
        this.ckClienti = ckClienti;
    }

    public void setCkFornitori(boolean ckFornitori) {
        this.ckFornitori = ckFornitori;
    }

    public void setCkTrasportatori(boolean ckTrasportatori) {
        this.ckTrasportatori = ckTrasportatori;
    }

    public void setCkAgenti(boolean ckAgenti) {
        this.ckAgenti = ckAgenti;
    }

    public void setCkPersonale(boolean ckPersonale) {
        this.ckPersonale = ckPersonale;
    }
}
