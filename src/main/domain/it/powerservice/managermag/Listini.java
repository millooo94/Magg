package it.powerservice.managermag;

import jakarta.persistence.*;
import org.aspectj.weaver.patterns.ThisOrTargetPointcut;

import java.math.BigDecimal;

@Entity
@Table(name = "listini")
public class Listini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "nome", length = 50, nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
    String nome;
    @Column(name = "modificabile", nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '1'")
    Boolean modificabile = true;
    @Column(name = "eliminato", nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean eliminato = false;
    @Column(name = "ricarico", columnDefinition = "DECIMAL(13, 2) DEFAULT NULL")
    BigDecimal ricarico;
    @Column(name = "scontodalistino", columnDefinition = "DECIMAL(13, 2) DEFAULT NULL")
    BigDecimal scontoDaListino;
    @Column(name = "scontodavendita", columnDefinition = "DECIMAL(13, 2) DEFAULT NULL")
    BigDecimal scontoDaVendita;
    @Column(name = "defaultsuweb", nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean defaultSuWeb;
    @Column(name = "visivato", nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean visIvato;

    @Transient
    Boolean isUpdating = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getModificabile() {
        return modificabile;
    }

    public void setModificabile(Boolean modificabile) {
        this.modificabile = modificabile;
    }

    public Boolean getEliminato() {
        return eliminato;
    }

    public void setEliminato(Boolean eliminato) {
        this.eliminato = eliminato;
    }

    public BigDecimal getRicarico() {
        return ricarico;
    }

    public void setRicarico(BigDecimal ricarico) {
        this.ricarico = ricarico;
    }

    public BigDecimal getScontoDaListino() {
        return scontoDaListino;
    }

    public void setScontoDaListino(BigDecimal scontoDaListino) {
        this.scontoDaListino = scontoDaListino;
    }

    public BigDecimal getScontoDaVendita() {
        return scontoDaVendita;
    }

    public void setScontoDaVendita(BigDecimal scontoDaVendita) {
        this.scontoDaVendita = scontoDaVendita;
    }

    public Boolean getDefaultSuWeb() {
        return defaultSuWeb;
    }

    public void setDefaultSuWeb(Boolean defaultSuWeb) {
        this.defaultSuWeb = defaultSuWeb;
    }

    public Boolean getVisIvato() {
        return visIvato;
    }

    public void setVisIvato(Boolean visIvato) {
        this.visIvato = visIvato;
    }

    public Boolean getIsUpdating() {
        return isUpdating;
    }

    public void setIsUpdating(Boolean isUpdating) {
        this.isUpdating = isUpdating;
    }

    @Override
    public String toString() {
        return "Listini{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", modificabile=" + modificabile +
                ", eliminato=" + eliminato +
                ", ricarico=" + ricarico +
                ", scontoDaListino=" + scontoDaListino +
                ", scontoDaVendita=" + scontoDaVendita +
                ", defaultSuWeb=" + defaultSuWeb +
                ", visIvato=" + visIvato +
                ", isUpdating=" + isUpdating +
                '}';
    }
}
