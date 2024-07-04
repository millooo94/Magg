package it.powerservice.managermag;


import jakarta.persistence.*;
import org.hibernate.annotations.Comment;


public class ImpostazioniValori {

    String codiceImpostazione;
    String ValoreMostrato;

    String valoreStringa;

    Double valoreNumero;

    String valoreClob;

    public ImpostazioniValori() {
    }

    public ImpostazioniValori(String codiceImpostazione, String valoreMostrato, String valoreStringa, Double valoreNumero, String valoreClob) {
        this.codiceImpostazione = codiceImpostazione;
        ValoreMostrato = valoreMostrato;
        this.valoreStringa = valoreStringa;
        this.valoreNumero = valoreNumero;
        this.valoreClob = valoreClob;
    }

    @Override
    public String toString() {
        return "ImpostazioniValori{" +
                "CodiceImpostazione='" + codiceImpostazione + '\'' +
                ", ValoreMostrato='" + ValoreMostrato + '\'' +
                ", valoreStringa='" + valoreStringa + '\'' +
                ", valoreNumero=" + valoreNumero +
                ", valoreClob='" + valoreClob + '\'' +
                '}';
    }

    public String getCodiceImpostazione() {
        return codiceImpostazione;
    }

    public void setCodiceImpostazione(String codiceImpostazione) {
        this.codiceImpostazione = codiceImpostazione;
    }

    public String getValoreMostrato() {
        return ValoreMostrato;
    }

    public void setValoreMostrato(String valoreMostrato) {
        ValoreMostrato = valoreMostrato;
    }

    public String getValoreStringa() {
        return valoreStringa;
    }

    public void setValoreStringa(String valoreStringa) {
        this.valoreStringa = valoreStringa;
    }

    public Double getValoreNumero() {
        return valoreNumero;
    }

    public void setValoreNumero(Double valoreNumero) {
        this.valoreNumero = valoreNumero;
    }

    public String getValoreClob() {
        return valoreClob;
    }

    public void setValoreClob(String valoreClob) {
        this.valoreClob = valoreClob;
    }

}
