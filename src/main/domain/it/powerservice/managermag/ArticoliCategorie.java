package it.powerservice.managermag;

import it.powerservice.managermag.keys.ArticoliCategoriePKId;
import jakarta.persistence.*;

@Entity
@Table(name = "articolicategorie")
@IdClass(ArticoliCategoriePKId.class)
public class ArticoliCategorie {
    @Id
    @Column(name = "idarticolo", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idArticolo;
    @Id
    @Column(name = "idcategoria", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idCategoria;
}
