package com.powerservice.managermag.places;

import it.powerservice.managermag.geography.Province;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import java.util.ArrayList;
import java.util.List;

public class PlacesIndexViewModel extends SelectorComposer<Component> {

    private String stato;
    private String regione;
    private String provincia = "";
    private String citta;

    private int selectedProvinciaIndex = 0;

    private List<Province> province = new ArrayList<>();

    @Init
    public void init() {
        province.add(new Province("(Nessuno)"));
        province.add(new Province("CT", "Catania", "001"));
        province.add(new Province("MI", "Milano", "002"));

    }
    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getProvincia() {
        return provincia;
    }

    @Command
    @NotifyChange("selectedProvinciaIndex")
    public void setProvincia(String provincia) {
        if (provincia != null) {
            this.provincia = provincia;

            for (Province p: province) {
                if (provincia.contains(p.getNomeCompleto())) {
                    selectedProvinciaIndex = province.indexOf(p);
                }
            }
        }

    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public List<Province> getProvince() {
        return province;
    }

    public void setProvince(List<Province> province) {
        this.province = province;
    }

    public int getSelectedProvinciaIndex() {
        return selectedProvinciaIndex;
    }

    public void setSelectedProvinciaIndex(int selectedProvinciaIndex) {
        this.selectedProvinciaIndex = selectedProvinciaIndex;
    }

    @Command
    public void onClick() {
        System.out.println("STATO ==> " + stato);
        System.out.println("REGIONE ==> " + regione);
        System.out.println("PROVINCIA ==> " + provincia);
        System.out.println("CITTA' ==> " + citta);
    }
}
