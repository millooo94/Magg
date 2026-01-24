package it.powerservice.managermag;

import it.powerservice.managermag.keys.CategorieRifPKId;
import jakarta.persistence.*;

@Entity
@Table(name = "categorierif")
@IdClass(CategorieRifPKId.class)
public class CategorieRif {

    public CategorieRif() {
    }

    public CategorieRif(Long idCategoriaPartenza, Long idCategoriaArrivo) {
        this.idCategoriaPartenza = idCategoriaPartenza;
        this.idCategoriaArrivo = idCategoriaArrivo;
    }

    @Id
    @Column(name = "idcategoriapartenza", nullable = false, precision = 11, columnDefinition = "INT(11) NOT NULL")
    Long idCategoriaPartenza;
    @Id
    @Column(name = "idcategoriaarrivo", nullable = false, precision = 11, columnDefinition = "INT(11) NOT NULL")
    Long idCategoriaArrivo;

    public Long getIdCategoriaPartenza() {
        return idCategoriaPartenza;
    }

    public void setIdCategoriaPartenza(Long idCategoriaPartenza) {
        this.idCategoriaPartenza = idCategoriaPartenza;
    }

    public Long getIdCategoriaArrivo() {
        return idCategoriaArrivo;
    }

    public void setIdCategoriaArrivo(Long idCategoriaArrivo) {
        this.idCategoriaArrivo = idCategoriaArrivo;
    }

    @Override
    public String toString() {
        return "CategorieRif{" +
                "idCategoriaPartenza=" + idCategoriaPartenza +
                ", idCategoriaArrivo=" + idCategoriaArrivo +
                '}';
    }
}
