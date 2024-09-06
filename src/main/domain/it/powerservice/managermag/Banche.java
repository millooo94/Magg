package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "banche")
public class Banche {
    @Id
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    Long id;

    @Transient
    Long idAnagrafica;
    @Column(name = "agenzia", length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL" )
    @Comment("Se nullo è banca aziendale")
    String agenzia;
    @Column(name = "iban", length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    String iban;
    @Column(name = "abi", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String abi;
    @Column(name = "cab", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String cab;
    @Column(name = "cin", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String cin;
    @Column(name = "cc", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String cc;
    @Column(name = "swift", length = 20, columnDefinition = "VARCHAR(20) NULL DEFAULT NULL")
    String swift;
    @Column(name = "localita", length = 20, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String localita;
    @Column(name = "indirizzo", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String indirizzo;
    @Column(name = "codsia", length = 20, columnDefinition = "VARCHAR(20) NULL DEFAULT NULL")
    String codSIA;
    @Column(name = "predefinito", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("per ogni anag si crea se è la 1° per quella anag, quella predefinita")
    Boolean predefinito;
    @Column(name = "eliminato", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean eliminato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAnagrafica() {
        return idAnagrafica;
    }

    public void setIdAnagrafica(Long idAnagrafica) {
        this.idAnagrafica = idAnagrafica;
    }

    public String getAgenzia() {
        return agenzia;
    }

    public void setAgenzia(String agenzia) {
        this.agenzia = agenzia;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAbi() {
        return abi;
    }

    public void setAbi(String abi) {
        this.abi = abi;
    }

    public String getCab() {
        return cab;
    }

    public void setCab(String cab) {
        this.cab = cab;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getLocalita() {
        return localita;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCodSIA() {
        return codSIA;
    }

    public void setCodSIA(String codSIA) {
        this.codSIA = codSIA;
    }

    public Boolean getPredefinito() {
        return predefinito;
    }

    public void setPredefinito(Boolean predefinito) {
        this.predefinito = predefinito;
    }

    public Boolean getEliminato() {
        return eliminato;
    }

    public void setEliminato(Boolean eliminato) {
        this.eliminato = eliminato;
    }
}
