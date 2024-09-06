package it.powerservice.managermag;

import com.powerservice.managermag.tipologiePagamenti.utilities.PagFattPa;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Table(name = "tipologiepagamenti")
public class TipologiePagamenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "idbanca", columnDefinition = "INT(11) NULL DEFAULT NULL")
    Long idBanca;
    @Column(name = "codice", length = 50, nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
    String codice;
    @Column(name = "descrizione", length = 150, nullable = false, columnDefinition = "VARCHAR(150) NOT NULL")
    String descrizione;
    @Column(name = "tipo", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL")
    @Comment("0=Azienda - 1=Cli/For - 2=Nessuno dei due")
    Boolean tipo;
    @Column(name = "riba", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean riba = false;
    @Column(name = "regolaauto", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean regolaAuto = false;
    @Column(name = "avvisi", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean avvisi = false;
    @Column(name = "modpagfattpa", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String modPagFattPA;
    @Column(name = "condpagfattpa", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String condPagFattPA;
    @Column(name = "sconto", precision = 13, scale = 2, columnDefinition = "DECIMAL(13, 2) NULL DEFAULT NULL")
    BigDecimal sconto;
    @Column(name = "speseincasso", precision = 12, scale = 2, columnDefinition = "DECIMAL(13, 2) NULL DEFAULT NULL")
    BigDecimal speseIncasso;
    @Column(name = "nonmodificabile", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean nonModificabile = false;
    @Column(name = "eliminato", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean eliminato = false;

    @Transient
    private Banche selectedBanca;
    @Transient
    private PagFattPa selectedModPagFattPA;
    @Transient
    private PagFattPa selectedCondPagFattPA;
    @Transient
    private Boolean isUpdating = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBanca() {
        return idBanca;
    }

    public void setIdBanca(Long idBanca) {
        this.idBanca = idBanca;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Boolean getTipo() {
        return tipo;
    }

    public void setTipo(Boolean tipo) {
        this.tipo = tipo;
    }

    public Boolean getRiba() {
        return riba;
    }

    public void setRiba(Boolean riba) {
        this.riba = riba;
    }

    public Boolean getRegolaAuto() {
        return regolaAuto;
    }

    public void setRegolaAuto(Boolean regolaAuto) {
        this.regolaAuto = regolaAuto;
    }

    public Boolean getAvvisi() {
        return avvisi;
    }

    public void setAvvisi(Boolean avvisi) {
        this.avvisi = avvisi;
    }

    public String getModPagFattPA() {
        return modPagFattPA;
    }

    public void setModPagFattPA(String modPagFattPA) {
        this.modPagFattPA = modPagFattPA;
    }

    public String getCondPagFattPA() {
        return condPagFattPA;
    }

    public void setCondPagFattPA(String condPagFattPA) {
        this.condPagFattPA = condPagFattPA;
    }

    public BigDecimal getSconto() {
        return sconto;
    }

    public void setSconto(BigDecimal sconto) {
        this.sconto = sconto;
    }

    public BigDecimal getSpeseIncasso() {
        return speseIncasso;
    }

    public void setSpeseIncasso(BigDecimal speseIncasso) {
        this.speseIncasso = speseIncasso;
    }

    public Boolean getNonModificabile() {
        return nonModificabile;
    }

    public void setNonModificabile(Boolean nonModificabile) {
        this.nonModificabile = nonModificabile;
    }

    public Boolean getEliminato() {
        return eliminato;
    }

    public void setEliminato(Boolean eliminato) {
        this.eliminato = eliminato;
    }


    public Banche getSelectedBanca() {
        return selectedBanca;
    }
    public void setSelectedBanca(Banche selectedBanca) {
        this.selectedBanca = selectedBanca;
    }
    public PagFattPa getSelectedModPagFattPA() {
        return selectedModPagFattPA;
    }
    public void setSelectedModPagFattPA(PagFattPa selectedModPagFattPA) {
        this.selectedModPagFattPA = selectedModPagFattPA;
    }
    public PagFattPa getSelectedCondPagFattPA() {
        return selectedCondPagFattPA;
    }
    public void setSelectedCondPagFattPA(PagFattPa selectedCondPagFattPA) {
        this.selectedCondPagFattPA = selectedCondPagFattPA;
    }
    public Boolean getIsUpdating() {
        return isUpdating;
    }
    public void setIsUpdating(Boolean updating) {
        isUpdating = updating;
    }

    @Override
    public String toString() {
        return "TipologiePagamenti{" +
                "id=" + id +
                ", idBanca=" + idBanca +
                ", codice='" + codice + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", tipo=" + tipo +
                ", riba=" + riba +
                ", regolaAuto=" + regolaAuto +
                ", avvisi=" + avvisi +
                ", modPagFattPA='" + modPagFattPA + '\'' +
                ", condPagFattPA='" + condPagFattPA + '\'' +
                ", sconto=" + sconto +
                ", speseIncasso=" + speseIncasso +
                ", nonModificabile=" + nonModificabile +
                ", eliminato=" + eliminato +
                ", selectedBanca=" + selectedBanca +
                ", selectedModPagFattPA=" + selectedModPagFattPA +
                ", selectedCondPagFattPA=" + selectedCondPagFattPA +
                ", isUpdating=" + isUpdating +
                '}';
    }
}
