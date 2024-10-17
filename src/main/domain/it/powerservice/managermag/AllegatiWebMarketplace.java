package it.powerservice.managermag;

import it.powerservice.managermag.keys.AllegatiWebMarketplacePKId;
import jakarta.persistence.*;

@Entity
@Table(name = "allegatiwebmarketplace")
@IdClass(AllegatiWebMarketplacePKId.class)
public class  AllegatiWebMarketplace {
    @Id
    @Column(name = "idallegato", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idAllegato;
    @Id
    @Column(name = "idmarketplace", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idMarketplace;
    @Column(name = "escludi", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean escludi;
    @Column(name = "tagordinamento", precision = 5, columnDefinition = "INT(5) NULL DEFAULT NULL")
    Integer tagOrdinamento;
}
