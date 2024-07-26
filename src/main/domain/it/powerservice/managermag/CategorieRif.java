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
}
