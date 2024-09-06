package it.powerservice.managermag;

import jakarta.persistence.*;
import org.zkoss.bind.BindUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "depositi")
public class Depositi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "codice", length = 50, nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
    String codice;
    @Column(name = "nome", length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    String nome;
    @Column(name = "nonmodificabile", nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean nonModificabile = false;
    @Column(name = "datanoninuso", columnDefinition = "DATE NULL DEFAULT NULL")
    Date dataNonInUso;
    @Column(name = "esterno", nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean esterno = false;
    @Column(name = "merceindisponibile", nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean merceIndisponibile = false;
    @Transient
    Boolean visibile = true;
    @Transient
    Boolean isUpdating = false;
    @Transient
    Boolean isOutdated = false;
    @Transient
    Boolean isValid = true;

    public Depositi() {
        isOutdated = calculateIsOutdated();
    }

    private boolean calculateIsOutdated() {
        if (dataNonInUso != null) {
            LocalDate localDateNonInUso = dataNonInUso.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            return localDateNonInUso.isBefore(LocalDate.now());
        }
        return false;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getNonModificabile() {
        return nonModificabile;
    }

    public void setNonModificabile(Boolean nonModificabile) {
        this.nonModificabile = nonModificabile;
    }

    public Date getDataNonInUso() {
        return dataNonInUso;
    }

    public void setDataNonInUso(Date dataNonInUso) {
        this.dataNonInUso = dataNonInUso;
    }

    public Boolean getEsterno() {
        return esterno;
    }

    public void setEsterno(Boolean esterno) {
        this.esterno = esterno;
    }

    public Boolean getMerceIndisponibile() {
        return merceIndisponibile;
    }

    public void setMerceIndisponibile(Boolean merceIndisponibile) {
        this.merceIndisponibile = merceIndisponibile;
    }

    public Boolean getVisibile() {
        return visibile;
    }

    public Boolean getIsUpdating() {
        return isUpdating;
    }

    public void setIsUpdating(Boolean updating) {
        isUpdating = updating;
    }

    public void setVisibile(Boolean visibile) {
        this.visibile = visibile;
    }

    public Boolean getIsOutdated() {
        return isOutdated;
    }

    public void setIsOutdated(Boolean outdated) {
        isOutdated = outdated;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean valid) {
        isValid = valid;
    }

    @PostLoad
    public void postLoad() {
        this.isOutdated = calculateIsOutdated();
    }

    @Override
    public String toString() {
        return "Depositi{" +
                "id=" + id +
                ", codice='" + codice + '\'' +
                ", nome='" + nome + '\'' +
                ", nonModificabile=" + nonModificabile +
                ", dataNonInUso=" + dataNonInUso +
                ", esterno=" + esterno +
                ", merceIndisponibile=" + merceIndisponibile +
                ", visibile=" + visibile +
                ", isUpdating=" + isUpdating +
                ", isOutdated=" + isOutdated +
                '}';
    }
}