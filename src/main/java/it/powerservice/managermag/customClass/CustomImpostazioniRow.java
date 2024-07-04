package it.powerservice.managermag.customClass;

import it.powerservice.managermag.enums.TipoImpostazioni;

public class CustomImpostazioniRow {
    String codice;
    String etichettaCampo;
    String categoria;
    String aspettoCampo;
    String valoreStringa;
    String valoreClob;
    Double valoreNumero;
    String tipoCampo;
    Short isNew;
    Boolean isCheckboxVisible;
    Boolean isCheckboxChecked;
    Boolean isStringBoxVisible;
    Boolean isNumberBoxVisible;
    Boolean isClobBoxVisible;
    Boolean isStringSelectBoxVisible;
    Boolean isSenzaNomeCategory;
    Boolean isModelliAggiuntiviCategory;
    Boolean isAltreImpostazioniCategory;
    Boolean isAltreImpostazioniPrimaColonna = false;
    Boolean isAltreImpostazioniSecondaColonna = false;
    private String highlightedEtichettaCampo;
    private Integer occurrenceNumber = 0;
    private Boolean isCurrentOccurrence = false;
    private Integer tab;



    public CustomImpostazioniRow(String codice, String tipoCampo, String etichettaCampo, String categoria, String aspettoCampo, String valoreStringa, String valoreClob, Double valoreNumero, Short isnew) {
        this.codice = codice;
        this.etichettaCampo = etichettaCampo;
        this.categoria = categoria;
        this.aspettoCampo = aspettoCampo;
        this.valoreStringa = valoreStringa;
        this.valoreClob = valoreClob;
        this.valoreNumero = valoreNumero;
        this.tipoCampo = tipoCampo;
        this.isNew = isnew;
        setTab();
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getEtichettaCampo() {
        return etichettaCampo;
    }

    public void setEtichettaCampo(String etichettaCampo) {
        this.etichettaCampo = etichettaCampo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAspettoCampo() {
        return aspettoCampo;
    }

    public void setAspettoCampo(String aspettoCampo) {
        this.aspettoCampo = aspettoCampo;
    }

    public String getValoreStringa() {
        return valoreStringa;
    }

    public void setValoreStringa(String valoreStringa) {
        this.valoreStringa = valoreStringa;
    }

    public String getValoreClob() {
        return valoreClob;
    }

    public void setValoreClob(String valoreClob) {
        this.valoreClob = valoreClob;
    }

    public Double getValoreNumero() {
        return valoreNumero;
    }

    public void setValoreNumero(Double valoreNumero) {
        this.valoreNumero = valoreNumero;
    }

    public String getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(String tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    public Short getIsNew() {
        return isNew;
    }

    public void setIsNew(Short isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsCheckboxVisible() {
        return aspettoCampo.equals("3") && tipoCampo.equals("S");
    }

    public Boolean getIsCheckboxChecked() {
        return valoreStringa != null && valoreStringa.equals("Y");
    }

    public Boolean getIsStringBoxVisible() {
        return aspettoCampo.equals("0") && tipoCampo.equals("S");
    }
    public Boolean getIsNumberBoxVisible() {
        return aspettoCampo.equals("0") && tipoCampo.equals("N");
    }
    public Boolean getIsClobBoxVisible() {
        return aspettoCampo.equals("0") && tipoCampo.equals("C");
    }
    public Boolean getIsStringSelectBoxVisible() {
        return aspettoCampo.equals("1") && tipoCampo.equals("S");
    }
    public Boolean getIsSenzaNomeCategory() {
        return categoria.equals("SENZA NOME");
    }
    public Boolean getIsModelliAggiuntiviCategory() {
        return categoria.equals("MODELLI AGGIUNTIVI");
    }
    public Boolean getIsAltreImpostazioniCategory() {
        return categoria.equals("ALTRE IMPOSTAZIONI");
    }

    public String getHighlightedEtichettaCampo() {
        return highlightedEtichettaCampo;
    }

    public Boolean getIsAltreImpostazioniPrimaColonna() {
        return isAltreImpostazioniPrimaColonna;
    }

    public void setIsAltreImpostazioniPrimaColonna(Boolean altreImpostazioniPrimaColonna) {
        isAltreImpostazioniPrimaColonna = altreImpostazioniPrimaColonna;
    }

    public Boolean getIsAltreImpostazioniSecondaColonna() {
        return isAltreImpostazioniSecondaColonna;
    }

    public void setIsAltreImpostazioniSecondaColonna(Boolean altreImpostazioniSecondaColonna) {
        isAltreImpostazioniSecondaColonna = altreImpostazioniSecondaColonna;
    }
    public void setHighlightedEtichettaCampo(String highlightedEtichettaCampo) {
        this.highlightedEtichettaCampo = highlightedEtichettaCampo;
    }
    public Integer getOccurrenceNumber() {
        return occurrenceNumber;
    }
    public void setOccurrenceNumber(Integer occurrenceNumber) {
        this.occurrenceNumber = occurrenceNumber;
    }

    public Boolean getCurrentOccurrence() {
        return isCurrentOccurrence;
    }

    public void setCurrentOccurrence(Boolean currentOccurrence) {
        isCurrentOccurrence = currentOccurrence;
    }

    public Integer getTab() {
        return tab;
    }

    public void setTab() {
        switch (categoria) {
            case "SENZA NOME":
                tab = 0;
                break;
            case "MODELLI AGGIUNTIVI":
                tab = 1;
                break;
            case "ALTRE IMPOSTAZIONI":
                tab = 2;
                break;
            default:
                break;
        }
    }


    @Override
    public String toString() {
        return "CustomImpostazioniRow{" +
                "codice='" + codice + '\'' +
                ", etichettaCampo='" + etichettaCampo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", aspettoCampo='" + aspettoCampo + '\'' +
                ", valoreStringa='" + valoreStringa + '\'' +
                ", valoreClob='" + valoreClob + '\'' +
                ", valoreNumero=" + valoreNumero +
                ", tipoCampo='" + tipoCampo + '\'' +
                ", isNew='" + isNew + '\'' +
                ", isCheckboxVisible=" + isCheckboxVisible +
                ", isCheckboxChecked=" + isCheckboxChecked +
                ", highlightedEtichettaCampo=" + highlightedEtichettaCampo +
                ", occurrenceNumber=" + occurrenceNumber +
                '}';
    }
}
