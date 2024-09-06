package it.powerservice.managermag;

import jakarta.persistence.*;
import org.aspectj.weaver.patterns.ThisOrTargetPointcut;

import java.math.BigDecimal;

@Entity
@Table(name = "listini")
public class Listini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "nome")
    String nome;
    @Column(name = "modificabile")
    Boolean modificabile = true;
    @Column(name = "eliminato")
    Boolean eliminato = false;
    @Column(name = "ricarico")
    BigDecimal ricarico;
    @Column(name = "scontodalistino")
    BigDecimal scontoDaListino;
    @Column(name = "scontodavendita")
    BigDecimal scontoDaVendita;
    @Column(name = "defaultsuweb")
    Boolean defaultSuWeb = false;
    @Column(name = "visivato")
    Boolean visIvato = false;

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
