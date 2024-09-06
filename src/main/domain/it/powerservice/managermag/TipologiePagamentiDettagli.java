package it.powerservice.managermag;

import jakarta.persistence.*;

@Entity
@Table(name = "tipologiepagamentidettagli")
public class TipologiePagamentiDettagli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "idpagamento", nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idPagamento;
    @Column(name = "giorni", precision = 4, nullable = false, columnDefinition = "INT(4) NOT NULL")
    Integer giorni;
    @Column(name = "finemese", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean fineMese;
    @Column(name = "mese", precision = 2, nullable = false, columnDefinition = "TINYINT(2) NULL DEFAULT NULL")
    Integer mese;
    @Column(name = "giorniaggiunti", precision = 4, columnDefinition = "INT(4) NULL DEFAULT NULL")
    Integer giorniAggiunti;
    @Column(name = "iva", precision = 1, columnDefinition = "TINYINT(1) NULL DEFAULT '0'")
    Boolean iva;

    @Transient
    private Boolean isUpdating = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Integer getGiorni() {
        return giorni;
    }

    public void setGiorni(Integer giorni) {
        this.giorni = giorni;
    }

    public Boolean getFineMese() {
        return fineMese;
    }

    public void setFineMese(Boolean fineMese) {
        this.fineMese = fineMese;
    }

    public Integer getMese() {
        return mese;
    }

    public void setMese(Integer mese) {
        this.mese = mese;
    }

    public Integer getGiorniAggiunti() {
        return giorniAggiunti;
    }

    public void setGiorniAggiunti(Integer giorniAggiunti) {
        this.giorniAggiunti = giorniAggiunti;
    }

    public Boolean getIva() {
        return iva;
    }

    public void setIva(Boolean iva) {
        this.iva = iva;
    }

    public Boolean getIsUpdating() {
        return isUpdating;
    }

    public void setisUpdating(Boolean updating) {
        isUpdating = updating;
    }
}
