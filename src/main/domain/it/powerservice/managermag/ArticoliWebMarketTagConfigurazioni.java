package it.powerservice.managermag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articoliwebmarkettagconfigurazioni")
public class ArticoliWebMarketTagConfigurazioni {
    @Id
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long id;
    @Column(name = "idwebmarketconfigurazione", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idWebMarketConfigurazione;
    @Column(name = "tag", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String tag;
}
