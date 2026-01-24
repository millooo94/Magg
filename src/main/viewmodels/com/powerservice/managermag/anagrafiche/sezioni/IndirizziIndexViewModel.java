package com.powerservice.managermag.anagrafiche.sezioni;

import com.powerservice.managermag.anagrafiche.utilities.General;
import it.powerservice.managermag.Indirizzi;
import it.powerservice.managermag.customClass.CodDesc;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class IndirizziIndexViewModel extends SelectorComposer<Window> {

    private List<Indirizzi> indirizzi = new ArrayList<>();

    private List<CodDesc> tipiIndirizzo = new ArrayList<>();
    private Boolean allIndirizziChecked = false;

    private Indirizzi selectedIndirizzo = new Indirizzi();
    private int selectedTipoIndirizzoIndex = 0;

    @Init
    private void init() {
        initTipoIndirizzo();
    }
    public  void initTipoIndirizzo() {
        tipiIndirizzo = General.getTipiIndirizzo();
        for (CodDesc ti: tipiIndirizzo) {
            if (selectedIndirizzo != null && selectedIndirizzo.getTipoIndirizzo().equals(ti.getCodice())) {
                selectedTipoIndirizzoIndex = tipiIndirizzo.indexOf(ti);
            }
        }
    }

    @Command
    @NotifyChange({"indirizzi"})
    public void onAllIndirizziChecked() {
        if (allIndirizziChecked) {
            for (Indirizzi i: indirizzi) {
                i.setChecked(true);
            }
        } else {
            for (Indirizzi i: indirizzi) {
                i.setChecked(!i.getChecked());
            }
        }
    }

    @Command
    @NotifyChange({"indirizzi","allIndirizziChecked"})
    public void onIndirizzoChecked() {
        for (Indirizzi i: indirizzi) {
            if (!i.getChecked()) {
                allIndirizziChecked = false;
                break;
            } else {
                allIndirizziChecked = true;
            }
        }
    }
    @Command
    @NotifyChange(
            {
                    "indirizzi",
                    "selectedIndirizzo",
                    "selectedTipoIndirizzoIndex",
                    "selectedIndirizzoNazioneIndex",
                    "isSelectedIndirizzoInItaly",
                    "selectedIndirizzoRegioneIndex",
                    "provinceSelectedIndirizzo",
                    "selectedIndirizzoProvinciaIndex",
                    "comuniSelectedIndirizzo",
                    "selectedIndirizzoComuneIndex",
                    "capSelectedIndirizzo",
                    "selectedIndirizzoCapIndex",
            })
    public void onIndirizzoSelected(@BindingParam("indirizzo") Indirizzi indirizzo) {

        selectedIndirizzo = indirizzo;

        initTipoIndirizzo();

        for (Indirizzi i: indirizzi) {
            if (!i.getId().equals(indirizzo.getId())) {
                i.setSelected(false);
            }
        }

        indirizzo.setSelected(!indirizzo.getSelected());

        String script = "localStorage.setItem('idIndirizzoSelezionato', '" + selectedIndirizzo.getId() + "');";
        Clients.evalJavaScript(script);
    }

    public List<Indirizzi> getIndirizzi() {
        return indirizzi;
    }

    public Boolean getAllIndirizziChecked() {
        return allIndirizziChecked;
    }

    public void setAllIndirizziChecked(Boolean allIndirizziChecked) {
        this.allIndirizziChecked = allIndirizziChecked;
    }

    public Indirizzi getSelectedIndirizzo() {
        return selectedIndirizzo;
    }

    public int getSelectedTipoIndirizzoIndex() {
        return selectedTipoIndirizzoIndex;
    }

    public IndirizziIndexViewModel(List<CodDesc> tipiIndirizzo) {
        this.tipiIndirizzo = tipiIndirizzo;
    }

    public void setIndirizzi(List<Indirizzi> indirizzi) {
        this.indirizzi = indirizzi;
    }

    public void setTipiIndirizzo(List<CodDesc> tipiIndirizzo) {
        this.tipiIndirizzo = tipiIndirizzo;
    }

    public void setSelectedIndirizzo(Indirizzi selectedIndirizzo) {
        this.selectedIndirizzo = selectedIndirizzo;
    }

    public void setSelectedTipoIndirizzoIndex(int selectedTipoIndirizzoIndex) {
        this.selectedTipoIndirizzoIndex = selectedTipoIndirizzoIndex;
    }
}
