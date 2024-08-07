package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "varianti")
public class Varianti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "idarticolo", columnDefinition = "INT(11) NULL DEFAULT NULL")
    @Comment("Serve solo per tipo=V'")
    Long idArticolo;
    @Column(name = "tipo", length = 10, nullable = false, columnDefinition = "VARCHAR(10) NOT NULL")
    @Comment("C=Categoria, T=Tipologia, V=Variante")
    String tipo;
    @Column(name = "descrizione", length = 255, nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    String descrizione;
    @Column(name = "descrizioneeng", length = 255, nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    String descrizioneEng;
    @Column(name = "obbligatorio", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("Serve solo per tipo=T")
    Boolean obbligatorio;
    @Column(name = "escludistampa", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("Serve solo per tipo=T e V")
    Boolean escludiStampa;
    @Column(name = "multiselezionefigli", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("Serve solo per tipo=T")
    Boolean multiSelezioneFigli;
    @Column(name = "filtroweb", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("Serve solo per tipo=T")
    Boolean filtroWeb;
    @Column(name = "chkzeroarticolo", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("Serve solo per tipo=V")
    Boolean chkZeroArticolo;
    @Column(name = "codcertalimentare", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    @Comment("Serve solo per tipo=V")
    String codCertAlimentare;
    @Column(name = "note", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String note;
    @Column(name = "eliminato", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean eliminato;
    @Column(name = "invisibile", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean invisibile;
    @Transient
    Boolean isUpdating = false;
    @Transient
    private String highlightedDescrizione;
    @Transient
    private Integer occurrenceNumber = 0;
    @Transient
    private Boolean isCurrentOccurrence = false;

    public Varianti() {
    }

    public Varianti(String descrizione, String descrizioneEng, String tipo) {
        this.descrizione = descrizione;
        this.descrizioneEng = descrizioneEng;
        this.tipo = tipo;
        this.idArticolo = null;
        this.obbligatorio = false;
        this.escludiStampa = false;
        this.multiSelezioneFigli = true;
        this.filtroWeb = false;
        this.chkZeroArticolo = false;
        this.codCertAlimentare = null;
        this.note = null;
        this.eliminato = false;
        this.invisibile = false;
    }

    public Varianti(Long id, Long idArticolo, String tipo, String descrizione, String descrizioneEng, Boolean obbligatorio, Boolean escludiStampa, Boolean multiSelezioneFigli, Boolean filtroWeb, Boolean chkZeroArticolo, String codCertAlimentare, String note, Boolean eliminato, Boolean invisibile) {
        this.id = id;
        this.idArticolo = idArticolo;
        this.tipo = tipo;
        this.descrizione = descrizione;
        this.descrizioneEng = descrizioneEng;
        this.obbligatorio = obbligatorio;
        this.escludiStampa = escludiStampa;
        this.multiSelezioneFigli = multiSelezioneFigli;
        this.filtroWeb = filtroWeb;
        this.chkZeroArticolo = chkZeroArticolo;
        this.codCertAlimentare = codCertAlimentare;
        this.note = note;
        this.eliminato = eliminato;
        this.invisibile = invisibile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(Long idArticolo) {
        this.idArticolo = idArticolo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizioneEng() {
        return descrizioneEng;
    }

    public void setDescrizioneEng(String descrizioneEng) {
        this.descrizioneEng = descrizioneEng;
    }

    public Boolean getObbligatorio() {
        return obbligatorio;
    }

    public void setObbligatorio(Boolean obbligatorio) {
        this.obbligatorio = obbligatorio;
    }

    public Boolean getEscludiStampa() {
        return escludiStampa;
    }

    public void setEscludiStampa(Boolean escludiStampa) {
        this.escludiStampa = escludiStampa;
    }

    public Boolean getMultiSelezioneFigli() {
        return multiSelezioneFigli;
    }

    public void setMultiSelezioneFigli(Boolean multiSelezioneFigli) {
        this.multiSelezioneFigli = multiSelezioneFigli;
    }

    public Boolean getFiltroWeb() {
        return filtroWeb;
    }

    public void setFiltroWeb(Boolean filtroWeb) {
        this.filtroWeb = filtroWeb;
    }

    public Boolean getChkZeroArticolo() {
        return chkZeroArticolo;
    }

    public void setChkZeroArticolo(Boolean chkZeroArticolo) {
        this.chkZeroArticolo = chkZeroArticolo;
    }

    public String getCodCertAlimentare() {
        return codCertAlimentare;
    }

    public void setCodCertAlimentare(String codCertAlimentare) {
        this.codCertAlimentare = codCertAlimentare;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getEliminato() {
        return eliminato;
    }

    public void setEliminato(Boolean eliminato) {
        this.eliminato = eliminato;
    }

    public Boolean getInvisibile() {
        return invisibile;
    }

    public void setInvisibile(Boolean invisibile) {
        this.invisibile = invisibile;
    }

    public Boolean getIsUpdating() {
        return isUpdating;
    }

    public void setIsUpdating(Boolean updating) {
        isUpdating = updating;
    }

    public String getHighlightedDescrizione() {
        return highlightedDescrizione;
    }

    public void setHighlightedDescrizione(String highlightedDescrizione) {
        this.highlightedDescrizione = highlightedDescrizione;
    }

    public Integer getOccurrenceNumber() {
        return occurrenceNumber;
    }

    public void setOccurrenceNumber(Integer occurrenceNumber) {
        this.occurrenceNumber = occurrenceNumber;
    }

    public Boolean getIsCurrentOccurrence() {
        return isCurrentOccurrence;
    }

    public void setCurrentOccurrence(Boolean currentOccurrence) {
        isCurrentOccurrence = currentOccurrence;
    }

    @Override
    public String toString() {
        return "Varianti{" +
                "id=" + id +
                ", idArticolo=" + idArticolo +
                ", tipo='" + tipo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", descrizioneEng='" + descrizioneEng + '\'' +
                ", obbligatorio=" + obbligatorio +
                ", escludiStampa=" + escludiStampa +
                ", multiSelezioneFigli=" + multiSelezioneFigli +
                ", filtroWeb=" + filtroWeb +
                ", chkZeroArticolo=" + chkZeroArticolo +
                ", codCertAlimentare='" + codCertAlimentare + '\'' +
                ", note='" + note + '\'' +
                ", eliminato=" + eliminato +
                ", invisibile=" + invisibile +
                '}';
    }
}
