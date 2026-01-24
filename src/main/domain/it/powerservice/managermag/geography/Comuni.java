package it.powerservice.managermag.geography;

import jakarta.persistence.*;

@Entity
@Table(name = "comuni")
public class Comuni {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "regione")
    private String regione;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "citta")
    private String citta;
    @Column(name = "cap")
    private String cap;
    @Column(name = "codice")
    private String codice;
    @Column(name = "provinciacompleta")
    private String provinciaCompleta;
    @Column(name = "codcom")
    private String codCom;
    @Column(name = "codprov")
    private String codProv;
    @Column(name = "codreg")
    private String codReg;
    @Column(name = "idrif_ipt")
    private int idfRifIpt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getProvinciaCompleta() {
        return provinciaCompleta;
    }

    public void setProvinciaCompleta(String provinciaCompleta) {
        this.provinciaCompleta = provinciaCompleta;
    }

    public String getCodCom() {
        return codCom;
    }

    public void setCodCom(String codCom) {
        this.codCom = codCom;
    }

    public String getCodProv() {
        return codProv;
    }

    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }

    public String getCodReg() {
        return codReg;
    }

    public void setCodReg(String codReg) {
        this.codReg = codReg;
    }

    public int getIdfRifIpt() {
        return idfRifIpt;
    }

    public void setIdfRifIpt(int idfRifIpt) {
        this.idfRifIpt = idfRifIpt;
    }

    public Comuni(String citta) {
        this.citta = citta;
        this.regione = "";
        this.provincia = "";
        this.cap = "";
        this.codice = "";
        this.provinciaCompleta = "";
        this.codCom = "";
        this.codProv = "";
        this.codReg = "";
        this.idfRifIpt = 0;
    }

    public Comuni(String citta, String codCom) {
        this.citta = citta;
        this.codCom = codCom;
    }

    public Comuni(Long id, String regione, String provincia, String citta, String cap, String codice, String provinciaCompleta, String codCom, String codProv, String codReg, int idfRifIpt) {
        this.id = id;
        this.regione = regione;
        this.provincia = provincia;
        this.citta = citta;
        this.cap = cap;
        this.codice = codice;
        this.provinciaCompleta = provinciaCompleta;
        this.codCom = codCom;
        this.codProv = codProv;
        this.codReg = codReg;
        this.idfRifIpt = idfRifIpt;
    }

    @Override
    public String toString() {
        return "Comuni{" +
                "id=" + id +
                ", regione='" + regione + '\'' +
                ", provincia='" + provincia + '\'' +
                ", citta='" + citta + '\'' +
                ", cap='" + cap + '\'' +
                ", codice='" + codice + '\'' +
                ", provinciaCompleta='" + provinciaCompleta + '\'' +
                ", codCom='" + codCom + '\'' +
                ", codProv='" + codProv + '\'' +
                ", codReg='" + codReg + '\'' +
                ", idfRifIpt=" + idfRifIpt +
                '}';
    }
}
