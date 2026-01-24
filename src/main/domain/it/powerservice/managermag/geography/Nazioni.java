package it.powerservice.managermag.geography;

import jakarta.persistence.*;

@Entity
@Table(name = "nazioni")
public class Nazioni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "nazione")
    private String nazione;
    @Column(name = "nazionalita")
    private String nazionalità;
    @Column(name = "countrycode")
    private String countryCode;
    @Column(name = "continente")
    private String continente;
    @Column(name = "europeanunion")
    private Boolean europeanUnion;
    @Column(name = "viesmode")
    private Boolean viesMode;
    @Column(name = "formatopartitaiva")
    private String formatoPartitaIva;
    @Column(name = "descrformatopartitaiva")
    private String descrFormatoPartitaIva;
    @Column(name = "maxlengthpartitaiva")
    private String maxLengthPartitaIva;
    @Column(name = "patternpregmatchpartitaiva")
    private String patternPregMatchPartitaIva;
    @Column(name = "solonumeripartitaiva")
    private Boolean soloNumeriPartitaIVA;

    public Nazioni() {
    }

    public Nazioni (String nazione) {
        this.nazione = nazione;
    }

    public Nazioni(long id, String nazione, String nazionalità, String countryCode, String continente, Boolean europeanUnion, Boolean viesMode, String formatoPartitaIva, String descrFormatoPartitaIva, String maxLengthPartitaIva, String patternPregMatchPartitaIva, Boolean soloNumeriPartitaIVA) {
        this.id = id;
        this.nazione = nazione;
        this.nazionalità = nazionalità;
        this.countryCode = countryCode;
        this.continente = continente;
        this.europeanUnion = europeanUnion;
        this.viesMode = viesMode;
        this.formatoPartitaIva = formatoPartitaIva;
        this.descrFormatoPartitaIva = descrFormatoPartitaIva;
        this.maxLengthPartitaIva = maxLengthPartitaIva;
        this.patternPregMatchPartitaIva = patternPregMatchPartitaIva;
        this.soloNumeriPartitaIVA = soloNumeriPartitaIVA;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getNazionalità() {
        return nazionalità;
    }

    public void setNazionalità(String nazionalità) {
        this.nazionalità = nazionalità;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public Boolean getEuropeanUnion() {
        return europeanUnion;
    }

    public void setEuropeanUnion(Boolean europeanUnion) {
        this.europeanUnion = europeanUnion;
    }

    public Boolean getViesMode() {
        return viesMode;
    }

    public void setViesMode(Boolean viesMode) {
        this.viesMode = viesMode;
    }

    public String getFormatoPartitaIva() {
        return formatoPartitaIva;
    }

    public void setFormatoPartitaIva(String formatoPartitaIva) {
        this.formatoPartitaIva = formatoPartitaIva;
    }

    public String getDescrPartitaIva() {
        return descrFormatoPartitaIva;
    }

    public void setDescrPartitaIva(String descrPartitaIva) {
        this.descrFormatoPartitaIva = descrPartitaIva;
    }

    public String getMaxLengthPartitaIva() {
        return maxLengthPartitaIva;
    }

    public void setMaxLengthPartitaIva(String maxLengthPartitaIva) {
        this.maxLengthPartitaIva = maxLengthPartitaIva;
    }

    public String getPatternPregMatchPartitaIva() {
        return patternPregMatchPartitaIva;
    }

    public void setPatternPregMatchPartitaIva(String patternPregMatchPartitaIva) {
        this.patternPregMatchPartitaIva = patternPregMatchPartitaIva;
    }

    public Boolean getSoloNumeriPartitaIVA() {
        return soloNumeriPartitaIVA;
    }

    public void setSoloNumeriPartitaIVA(Boolean soloNumeriPartitaIVA) {
        this.soloNumeriPartitaIVA = soloNumeriPartitaIVA;
    }

    @Override
    public String toString() {
        return "Nazioni{" +
                "id=" + id +
                ", nazione='" + nazione + '\'' +
                ", nazionalità='" + nazionalità + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", continente='" + continente + '\'' +
                ", europeanUnion=" + europeanUnion +
                ", viesMode=" + viesMode +
                ", formatoPartitaIva='" + formatoPartitaIva + '\'' +
                ", descrFormatoPartitaIva='" + descrFormatoPartitaIva + '\'' +
                ", maxLengthPartitaIva='" + maxLengthPartitaIva + '\'' +
                ", patternPregMatchPartitaIva='" + patternPregMatchPartitaIva + '\'' +
                ", soloNumeriPartitaIVA=" + soloNumeriPartitaIVA +
                '}';
    }
}
