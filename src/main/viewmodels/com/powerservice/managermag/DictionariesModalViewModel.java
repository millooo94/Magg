package com.powerservice.managermag;

import it.powerservice.managermag.*;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class DictionariesModalViewModel {
    @WireVariable
    DizionariCategorieService dizionariCategorieService;
    @WireVariable
    DizionariService dizionariService;
    List<DizionariCategorie> dizionariCategorie = new ArrayList<>();
    List<Dizionari> dizionari = new ArrayList<>();

    DizionariCategorie selectedDizionariCategoria;

    @Init
    public void init(@ContextParam(ContextType.COMPONENT) Window w) throws Exception {
        initDizionari();
    }

    public void initDizionari() throws SQLException {
        dizionariCategorie = dizionariCategorieService.getDizionariCategorie();
        selectedDizionariCategoria = dizionariCategorie.get(0);
        dizionari = dizionariService.getDizionari();
        int dizionariCount = 0;
        for (int i = 0; i < dizionari.size(); i++) {
            Dizionari dizionario = dizionari.get(i);
            dizionariCount++;
            if (dizionariCount <= 16) {
                dizionario.setPrimaColonna(true);
            } else {
                dizionario.setSecondaColonna(true);
            }
        }
    }

    @Command
    public void updateDescrizioneDizionario() {
        for (Dizionari dizionario : dizionari) {
            dizionariService.updateDescrizioneDizionario(dizionario.getCodice(), dizionario.getDescrizione());
        }
    }

    public List<DizionariCategorie> getDizionariCategorie() {
        return dizionariCategorie;
    }
    public DizionariCategorie getSelectedDizionariCategoria() {
        return selectedDizionariCategoria;
    }

    public void setSelectedDizionariCategoria(DizionariCategorie selectedCategoria) {
        this.selectedDizionariCategoria = selectedCategoria;
    }

    public List<Dizionari> getDizionari() {
        return dizionari;
    }
}
