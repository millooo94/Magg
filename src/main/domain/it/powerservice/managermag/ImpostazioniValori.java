package it.powerservice.managermag;


import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "impostazionivalori")
public class ImpostazioniValori {
    @Id
    @Column(name = "codiceimpostazione", nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    String codiceImpostazione;
    @Column(name = "valoremostrato", length = 100, columnDefinition = "VARCHAR(100) DEFAULT NULL")
    @Comment("Serve principalmente per le tendine) Se pieno viene mostrato questo al posto del relativo valore stringa / numero / clob")
    String ValoreMostrato;
    @Column(name = "valorestringa", length = 100, columnDefinition = "VARCHAR(100) DEFAULT NULL")
    String valoreStringa;
    @Column(name = "valorenumero", columnDefinition = "DOUBLE DEFAULT NULL")
    Double valoreNumero;
    @Column(name = "valoreclob", columnDefinition = "MEDIUMTEXT")
    String valoreClob;

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
