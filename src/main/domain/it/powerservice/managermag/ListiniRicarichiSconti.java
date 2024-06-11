package it.powerservice.managermag;

import it.powerservice.managermag.keys.ListiniRicarichiScontiPKId;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "listiniricarichisconti")
@IdClass(ListiniRicarichiScontiPKId.class)
public class ListiniRicarichiSconti {
    @Id
    @Column(name = "idlistino", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idListino;
    @Id
    @Column(name = "idMarca", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idMarca;
    @Id
    @Column(name = "idCategoria", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long IdCategoria;
    @Column(name = "ricarico", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal ricarico;
    @Column(name = "sconto", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal sconto;
    @Column(name = "scontodavenditabase", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal scontoDaVenditaBase;


}
