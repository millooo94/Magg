package it.powerservice.managermag;

import jakarta.persistence.*;

@Entity
@Table(name = "articolivarianticonfigurazioni")
public class ArticoliVariantiConfigurazioni {
    @Id
    @Column(name = "idarticoloconfigurazione", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long id;
    @Column(name = "idvariante", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idvariante;
    @Column(name = "ordine", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL DEFAULT '0'")
    Integer ordine;
}
