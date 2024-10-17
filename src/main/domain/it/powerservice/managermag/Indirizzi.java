package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;


@Entity
@Table(name = "indirizzi")
public class Indirizzi implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "idanagrafica", precision = 11, columnDefinition = "INT(11) NULL DEFAULT NULL")
    Long idAnagrafica;
    @Column(name = "iddocumento", precision = 11, columnDefinition = "INT(11) NULL DEFAULT NULL")
    @Comment("Per il futuro quando ci sarà la tabella documenti ricordardi si fare la FK di ccd")
    Long idDocumento;
    @Column(name = "tipoindirizzo", length = 5, nullable = false, columnDefinition = "VARCHAR(5) NOT NULL")
    @Comment("S=SEDE nD=Destinazione Principale nSD=Destinazione Secondaria nR=Referente (sostituisce la tabella referentiFornitori) nA=Altro")
    String tipoIndirizzo;
    @Column(name = "denominazionealternativa", length = 150, columnDefinition = "VARCHAR(150) NULL DEFAULT NULL")
    String denominazioneAlternativa;
    @Column(name = "nazione", length = 150, nullable = false, columnDefinition = "VARCHAR(150) NOT NULL")
    String nazione;
    @Column(name = "indirizzo", length = 255, nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    String indirizzo;
    @Column(name = "provincia", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String provincia;
    @Column(name = "comune", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String comune;
    @Column(name = "frazione", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String frazione;
    @Column(name = "regione", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String regione;
    @Column(name = "cap", length = 20, columnDefinition = "VARCHAR(20) NULL DEFAULT NULL")
    String cap;
    @Column(name = "codsdi", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String codSdi;
    @Column(name = "telefono1", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String telefono1;
    @Column(name = "telefono2", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String telefono2;
    @Column(name = "telefono3", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String telefono3;
    @Column(name = "cellulare", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String cellulare;
    @Column(name = "fax", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String fax;
    @Column(name = "email", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String email;
    @Column(name = "pec", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String pec;
    @Column(name = "sito", length = 150, columnDefinition = "VARCHAR(150) NULL DEFAULT NULL")
    String sito;
    @Column(name = "referente", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String referente;
    @Column(name = "recapitoreferente", length = 150, columnDefinition = "VARCHAR(150) NULL DEFAULT NULL")
    String recapitoReferente;
    @Column(name = "notes", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String notes;

    @Transient
    private Boolean selected = false;
    @Transient
    private Boolean checked = false;

    @Override
    public Indirizzi clone() {
        try {
            Indirizzi cloned = (Indirizzi) super.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

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

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTipoIndirizzo() {
        return tipoIndirizzo;
    }

    public void setTipoIndirizzo(String tipoIndirizzo) {
        this.tipoIndirizzo = tipoIndirizzo;
    }

    public String getDenominazioneAlternativa() {
        return denominazioneAlternativa;
    }

    public void setDenominazioneAlternativa(String denominazioneAlternativa) {
        this.denominazioneAlternativa = denominazioneAlternativa;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getFrazione() {
        return frazione;
    }

    public void setFrazione(String frazione) {
        this.frazione = frazione;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCodSdi() {
        return codSdi;
    }

    public void setCodSdi(String codSdi) {
        this.codSdi = codSdi;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getTelefono3() {
        return telefono3;
    }

    public void setTelefono3(String telefono3) {
        this.telefono3 = telefono3;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPec() {
        return pec;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public String getSito() {
        return sito;
    }

    public void setSito(String sito) {
        this.sito = sito;
    }

    public String getReferente() {
        return referente;
    }

    public void setReferente(String referente) {
        this.referente = referente;
    }

    public String getRecapitoReferente() {
        return recapitoReferente;
    }

    public void setRecapitoReferente(String recapitoReferente) {
        this.recapitoReferente = recapitoReferente;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Indirizzi{" +
                "id=" + id +
                ", idAnagrafica=" + idAnagrafica +
                ", idDocumento=" + idDocumento +
                ", tipoIndirizzo='" + tipoIndirizzo + '\'' +
                ", denominazioneAlternativa='" + denominazioneAlternativa + '\'' +
                ", nazione='" + nazione + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", provincia='" + provincia + '\'' +
                ", comune='" + comune + '\'' +
                ", frazione='" + frazione + '\'' +
                ", regione='" + regione + '\'' +
                ", cap='" + cap + '\'' +
                ", codSdi='" + codSdi + '\'' +
                ", telefono1='" + telefono1 + '\'' +
                ", telefono2='" + telefono2 + '\'' +
                ", telefono3='" + telefono3 + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", pec='" + pec + '\'' +
                ", sito='" + sito + '\'' +
                ", referente='" + referente + '\'' +
                ", recapitoReferente='" + recapitoReferente + '\'' +
                ", notes='" + notes + '\'' +
                ", selected=" + selected +
                ", checked=" + checked +
                '}';
    }
}
