package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "datiazienda")
public class DatiAzienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long id;
    @Column(name = "ragionesociale", length = 60, columnDefinition = "VARCHAR(60) NULL DEFAULT NULL")
    String ragioneSociale;
    @Column(name = "denominazione", length = 60, columnDefinition = "VARCHAR(60) NULL DEFAULT NULL")
    String denominazione;
    @Column(name = "indirizzo", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String indirizzo;
    @Column(name = "telefono", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String telefono;
    @Column(name = "comune", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String comune;
    @Column(name = "provincia", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String provincia;
    @Column(name = "cap", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String cap;
    @Column(name = "regimprese", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String RegImprese;
    @Column(name = "ccia", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String CCIA;
    @Column(name = "PIVA", length = 20, columnDefinition = "VARCHAR(20) NULL DEFAULT NULL")
    String PIVA;
    @Column(name = "codicefiscale", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String CodiceFiscale;
    @Column(name = "fax", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String Fax;
    @Column(name = "email", length = 70, columnDefinition = "VARCHAR(70) NULL DEFAULT NULL")
    String Email;
    @Column(name = "emailconferma", length = 70, columnDefinition = "VARCHAR(70) NULL DEFAULT NULL")
    String EmailConferma;
    @Column(name = "sito", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String Sito;
    @Column(name = "tipohttp", precision = 11,columnDefinition = "INT(11) NULL DEFAULT NULL")
    @Comment("1=Http - 2=Https")
    Integer TipoHttp;
    @Column(name = "capsociale", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal CapSociale;
    @Column(name = "regimefiscale", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String RegimeFiscale;
    @Column(name = "codiceaziendafattpa", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String codiceAziendaFATTPA;
    @Column(name = "rapprlegale_privacy", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String rapprlegalePrivacy;
    @Column(name = "rapprlegale_email", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String rapprlegaleEmail;
    @Column(name = "pecaziendale", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String pecAziendale;
    @Column(name = "sdiazienda", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String sdiAzienda;
    @Column(name = "provinciafinanza", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String provinciaFinanza;
    @Column(name = "numeroautorizzazione", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String numeroAutorizzazione;
    @Column(name = "dataautorizzazione", columnDefinition = "DATETIME NULL DEFAULT NULL")
    Date dataAutorizzazione;
    @Column(name = "userlicenza", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String userLicenza;
    @Column(name = "passwordlicenza", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String passwordLicenza;
    @Column(name = "userdownloadlicenza", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String userDownloadLicenza;
    @Transient
    Boolean isUpdating = false;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getRegImprese() {
        return RegImprese;
    }

    public void setRegImprese(String regImprese) {
        RegImprese = regImprese;
    }

    public String getCCIA() {
        return CCIA;
    }

    public void setCCIA(String CCIA) {
        this.CCIA = CCIA;
    }

    public String getPIVA() {
        return PIVA;
    }

    public void setPIVA(String PIVA) {
        this.PIVA = PIVA;
    }

    public String getCodiceFiscale() {
        return CodiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        CodiceFiscale = codiceFiscale;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEmailConferma() {
        return EmailConferma;
    }

    public void setEmailConferma(String emailConferma) {
        EmailConferma = emailConferma;
    }

    public String getSito() {
        return Sito;
    }

    public void setSito(String sito) {
        Sito = sito;
    }

    public Integer getTipoHttp() {
        return TipoHttp;
    }

    public void setTipoHttp(Integer tipoHttp) {
        TipoHttp = tipoHttp;
    }

    public BigDecimal getCapSociale() {
        return CapSociale;
    }

    public void setCapSociale(BigDecimal capSociale) {
        CapSociale = capSociale;
    }

    public String getRegimeFiscale() {
        return RegimeFiscale;
    }

    public void setRegimeFiscale(String regimeFiscale) {
        RegimeFiscale = regimeFiscale;
    }

    public String getCodiceAziendaFATTPA() {
        return codiceAziendaFATTPA;
    }

    public void setCodiceAziendaFATTPA(String codiceAziendaFATTPA) {
        this.codiceAziendaFATTPA = codiceAziendaFATTPA;
    }

    public String getRapprlegalePrivacy() {
        return rapprlegalePrivacy;
    }

    public void setRapprlegalePrivacy(String rapprlegalePrivacy) {
        this.rapprlegalePrivacy = rapprlegalePrivacy;
    }

    public String getRapprlegaleEmail() {
        return rapprlegaleEmail;
    }

    public void setRapprlegaleEmail(String rapprlegaleEmail) {
        this.rapprlegaleEmail = rapprlegaleEmail;
    }

    public String getPecAziendale() {
        return pecAziendale;
    }

    public void setPecAziendale(String pecAziendale) {
        this.pecAziendale = pecAziendale;
    }

    public String getSdiAzienda() {
        return sdiAzienda;
    }

    public void setSdiAzienda(String sdiAzienda) {
        this.sdiAzienda = sdiAzienda;
    }

    public String getProvinciaFinanza() {
        return provinciaFinanza;
    }

    public void setProvinciaFinanza(String provinciaFinanza) {
        this.provinciaFinanza = provinciaFinanza;
    }

    public String getNumeroAutorizzazione() {
        return numeroAutorizzazione;
    }

    public void setNumeroAutorizzazione(String numeroAutorizzazione) {
        this.numeroAutorizzazione = numeroAutorizzazione;
    }

    public Date getDataAutorizzazione() {
        return dataAutorizzazione;
    }

    public void setDataAutorizzazione(Date dataAutorizzazione) {
        this.dataAutorizzazione = dataAutorizzazione;
    }

    public String getUserLicenza() {
        return userLicenza;
    }

    public void setUserLicenza(String userLicenza) {
        this.userLicenza = userLicenza;
    }

    public String getPasswordLicenza() {
        return passwordLicenza;
    }

    public void setPasswordLicenza(String passwordLicenza) {
        this.passwordLicenza = passwordLicenza;
    }

    public String getUserDownloadLicenza() {
        return userDownloadLicenza;
    }

    public void setUserDownloadLicenza(String userDownloadLicenza) {
        this.userDownloadLicenza = userDownloadLicenza;
    }

    public Boolean getUpdating() {
        return isUpdating;
    }

    public void setUpdating(Boolean updating) {
        isUpdating = updating;
    }

    @Override
    public String toString() {
        return "DatiAzienda{" +
                "id=" + id +
                ", ragioneSociale='" + ragioneSociale + '\'' +
                ", denominazione='" + denominazione + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", comune='" + comune + '\'' +
                ", provincia='" + provincia + '\'' +
                ", cap='" + cap + '\'' +
                ", RegImprese='" + RegImprese + '\'' +
                ", CCIA='" + CCIA + '\'' +
                ", PIVA='" + PIVA + '\'' +
                ", CodiceFiscale='" + CodiceFiscale + '\'' +
                ", Fax='" + Fax + '\'' +
                ", Email='" + Email + '\'' +
                ", EmailConferma='" + EmailConferma + '\'' +
                ", Sito='" + Sito + '\'' +
                ", TipoHttp=" + TipoHttp +
                ", CapSociale=" + CapSociale +
                ", RegimeFiscale='" + RegimeFiscale + '\'' +
                ", codiceAziendaFATTPA='" + codiceAziendaFATTPA + '\'' +
                ", rapprlegalePrivacy='" + rapprlegalePrivacy + '\'' +
                ", rapprlegaleEmail='" + rapprlegaleEmail + '\'' +
                ", pecAziendale='" + pecAziendale + '\'' +
                ", sdiAzienda='" + sdiAzienda + '\'' +
                ", provinciaFinanza='" + provinciaFinanza + '\'' +
                ", numeroAutorizzazione='" + numeroAutorizzazione + '\'' +
                ", dataAutorizzazione=" + dataAutorizzazione +
                ", userLicenza='" + userLicenza + '\'' +
                ", passwordLicenza='" + passwordLicenza + '\'' +
                ", userDownloadLicenza='" + userDownloadLicenza + '\'' +
                '}';
    }
}
