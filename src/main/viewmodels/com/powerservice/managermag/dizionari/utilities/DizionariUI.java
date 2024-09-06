package com.powerservice.managermag.dizionari.utilities;

import it.powerservice.managermag.customClass.UiRecord;

public class DizionariUI extends UiRecord {
    String Categoria;
    String descrizioneCategoria;
    String colonnaRestituita;
    Boolean mostraCodice;
    String Codice;
    String descrizioneDizionario;
    Boolean modificabile;

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDescrizioneCategoria() {
        return descrizioneCategoria;
    }

    public void setDescrizioneCategoria(String descrizioneCategoria) {
        this.descrizioneCategoria = descrizioneCategoria;
    }

    public String getColonnaRestituita() {
        return colonnaRestituita;
    }

    public void setColonnaRestituita(String colonnaRestituita) {
        this.colonnaRestituita = colonnaRestituita;
    }

    public Boolean getMostraCodice() {
        return mostraCodice;
    }

    public void setMostraCodice(Boolean mostraCodice) {
        this.mostraCodice = mostraCodice;
    }

    public String getCodice() {
        return Codice;
    }

    public void setCodice(String codice) {
        Codice = codice;
    }

    public String getDescrizioneDizionario() {
        return descrizioneDizionario;
    }

    public void setDescrizioneDizionario(String descrizioneDizionario) {
        this.descrizioneDizionario = descrizioneDizionario;
    }

    public Boolean getModificabile() {
        return modificabile;
    }

    public void setModificabile(Boolean modificabile) {
        this.modificabile = modificabile;
    }

    @Override
    public String toString() {
        return "DizionariUI{" +
                "Categoria='" + Categoria + '\'' +
                ", descrizioneCategoria='" + descrizioneCategoria + '\'' +
                ", colonnaRestituita='" + colonnaRestituita + '\'' +
                ", mostraCodice=" + mostraCodice +
                ", Codice='" + Codice + '\'' +
                ", descrizioneDizionario='" + descrizioneDizionario + '\'' +
                ", modificabile=" + modificabile +
                '}';
    }
}
