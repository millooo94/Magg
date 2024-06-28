package it.powerservice.managermag.customClass;

import org.zkoss.zhtml.Small;

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
                '}';
    }
}
