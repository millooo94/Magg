package com.powerservice.managermag.anagrafiche.sezioni;

import com.powerservice.managermag.anagrafiche.utilities.General;
import it.powerservice.managermag.Indirizzi;
import it.powerservice.managermag.IndirizziService;
import it.powerservice.managermag.NazioniService;
import it.powerservice.managermag.customClass.CodDesc;
import it.powerservice.managermag.customClass.GridColumn;
import it.powerservice.managermag.geography.Nazioni;
import it.powerservice.managermag.geography.Regioni;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class IndirizziIndexViewModel extends SelectorComposer<Window> {

    @Wire("#indirizzi-columns")
    Columns indirizziColumns = new Columns();
    @WireVariable
    private IndirizziService indirizziService;
    @WireVariable
    private NazioniService nazioniService;
    private List<Indirizzi> indirizzi = new ArrayList<>();
    private List<CodDesc> tipiIndirizzo = new ArrayList<>();
    private List<Nazioni> nazioni = new ArrayList<>();
    private List<Regioni> regioni = new ArrayList<>();
    private Indirizzi selectedIndirizzo = null;
    private int selectedTipoIndirizzoIndex = 0;
    private int selectedNazioneIndex = 0;
    private Boolean allIndirizziChecked = false;
    private Boolean fieldsetVisible = false;




    @Init
    private void init() {
        indirizzi = indirizziService.getIndirizziFromIdAnagrafica(1);
        tipiIndirizzo = General.getTipiIndirizzo();
        nazioni = nazioniService.getNazioni();
    }

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        generateIndirizziColumns();
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
    @NotifyChange({"indirizzi", "selectedIndirizzo", "selectedTipoIndirizzoIndex", "selectedNazioneIndex", "fieldsetVisible"})
    public void onIndirizzoSelected(@BindingParam("indirizzo") Indirizzi indirizzo) {
        System.out.println(fieldsetVisible);
        selectedIndirizzo = indirizzo;
        fieldsetVisible = true;
        initTipoIndirizzo();
        for (Indirizzi i: indirizzi) {
            if (!i.getId().equals(indirizzo.getId())) {
                i.setSelected(false);
            }
        }
        indirizzo.setSelected(!indirizzo.getSelected());
    }

    public  void initTipoIndirizzo() {
        for (CodDesc ti: tipiIndirizzo) {
            if (selectedIndirizzo != null && selectedIndirizzo.getTipoIndirizzo().equals(ti.getCodice())) {
                selectedTipoIndirizzoIndex = tipiIndirizzo.indexOf(ti);
            }
        }
    }
    public void generateIndirizziColumns() {
        List<GridColumn> indirizziColumnRefs = General.getIndirizziColumns();
        for (GridColumn columnRef: indirizziColumnRefs) {
            Column column = new Column(columnRef.getLabel());
            column.setWidth(columnRef.getWidth());
            indirizziColumns.appendChild(column);
        }
    }


    public List<Indirizzi> getIndirizzi() {
        return indirizzi;
    }

    public List<CodDesc> getTipiIndirizzo() {
        return tipiIndirizzo;
    }

    public List<Nazioni> getNazioni() {
        return nazioni;
    }

    public List<Regioni> getRegioni() {
        return regioni;
    }

    public int getSelectedNazioneIndex() {
        return selectedNazioneIndex;
    }


    public Indirizzi getSelectedIndirizzo() {
        return selectedIndirizzo;
    }

    public int getSelectedTipoIndirizzoIndex() {
        return selectedTipoIndirizzoIndex;
    }

    public Boolean getAllIndirizziChecked() {
        return allIndirizziChecked;
    }

    public void setAllIndirizziChecked(Boolean allIndirizziChecked) {
        this.allIndirizziChecked = allIndirizziChecked;
    }

    public Boolean getFieldsetVisible() {
        return fieldsetVisible;
    }
}
