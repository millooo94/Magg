package com.powerservice.managermag.anagrafiche;

import com.powerservice.managermag.anagrafiche.utilities.AnagraficheMonoCloseListener;
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
    private List<CodDesc> tipiAnagrafiche = new ArrayList<>();
    private List<Anagrafiche> anagrafiche = new ArrayList<>();
    private Anagrafiche selectedAnagrafica = null;
    private Integer selectedTipoAnagraficaIndex = 0;
    private Boolean createButtonDisabled = false;
    private Boolean editButtonDisabled = true;
    private Boolean removeButtonDisabled = true;


    @Init
    public void init() {
        initTipiAnagrafiche();
        initAnagrafiche(selectedTipoAnagraficaIndex);
    }

    @Command
    @NotifyChange({"anagrafiche"})
    public void onCheckTipoAnagrafica() {
        initAnagrafiche(selectedTipoAnagraficaIndex);
    }
    @Command
    @NotifyChange({"selectedAnagrafica", "editButtonDisabled", "removeButtonDisabled"})
    public void onClickAnagrafica(@BindingParam("anagrafica") Anagrafiche anagrafica) {
        selectedAnagrafica = anagrafica;
        editButtonDisabled = false;
        removeButtonDisabled = false;

        String script = "localStorage.setItem('idAnagrafica', '" + anagrafica.getId() + "');";
        Clients.evalJavaScript(script);
    }
    @Command
    @NotifyChange({"anagrafiche", "removeButtonDisabled"})
    public void onDeleteAnagrafica() {
        selectedAnagrafica.setEliminato(true);
        anagraficheService.saveAnagrafica(selectedAnagrafica);
        removeButtonDisabled = true;
        initAnagrafiche(selectedTipoAnagraficaIndex);
    }

    @Command
    public void onOpenAnagraficheMono(@BindingParam("type") String actionType) {
        Map<String, Object> params = new HashMap<>();
        System.out.println("INDEX ===> " + tipiAnagrafiche.get(selectedTipoAnagraficaIndex));
        switch (actionType) {
            case "EDIT":
                params.put("anagraficaToSave", selectedAnagrafica);
                params.put("monoType", "EDIT");
                params.put("tipoAnagrafica", selectedAnagrafica.getTipo());
                break;
            case "CREATE":
                params.put("anagraficaToSave", new Anagrafiche());
                params.put("monoType", "CREATE");
                params.put("tipoAnagrafica", tipiAnagrafiche.get(selectedTipoAnagraficaIndex).getCodice());
                break;
        }
        AnagraficheMonoViewModel.apriPopup(this, params).addEventListener(Events.ON_CLOSE, new AnagraficheMonoCloseListener(this));
    }

    public void initTipiAnagrafiche() {
        tipiAnagrafiche.add(new CodDesc("C", "Clienti"));
        tipiAnagrafiche.add(new CodDesc("F", "Fornitori"));
        tipiAnagrafiche.add(new CodDesc("T", "Trasportatori"));
        tipiAnagrafiche.add(new CodDesc("A", "Agenti"));
        tipiAnagrafiche.add(new CodDesc("P", "Personale"));
    }

    public void initAnagrafiche(Integer selectedTipoAnagraficaIndex) {
        anagrafiche = anagraficheService.getAnagraficheFromTipo(tipiAnagrafiche.get(selectedTipoAnagraficaIndex).getCodice(), tipiAnagrafiche.get(selectedTipoAnagraficaIndex).getDescrizione());
        BindUtils.postNotifyChange(null, null, this, "anagrafiche");
    }

    public List<CodDesc> getTipiAnagrafiche() {
        return tipiAnagrafiche;
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

}
