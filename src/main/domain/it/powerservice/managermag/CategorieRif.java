package it.powerservice.managermag;

import it.powerservice.managermag.keys.CategorieRifPKId;
import jakarta.persistence.*;

@Entity
@Table(name = "categorierif")
@IdClass(CategorieRifPKId.class)
public class CategorieRif {
    @Id
    @Column(name = "idcategoriapartenza", nullable = false, precision = 11, columnDefinition = "INT(11) NOT NULL")
    Long idCategoriaPartenza;
    @Id
    @Column(name = "idcategoriaarrivo", nullable = false, precision = 11, columnDefinition = "INT(11) NOT NULL")
    Long idCategoriaArrivo;
}
