package it.powerservice.managermag;

import it.powerservice.managermag.keys.ArticoliListiniConfigurazioniPKId;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "articolilistiniconfigurazioni")
@IdClass(ArticoliListiniConfigurazioniPKId.class)
public class ArticoliListiniConfigurazioni {
    @Id
    @Column(name = "idarticoloconfigurazione", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idarticoloconfigurazione;
    @Id
    @Column(name = "idlistino", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idListino;
    @Column(name = "ricarico", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal ricarico;
    @Column(name = "ricaricopercentuale", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal ricaricoPercentuale;
    @Column(name = "prezzovenditafissa", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal prezzoVenditaFissa;
    @Column(name = "sc1listinovendita", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal sc1ListinoVendita;
    @Column(name = "sc2listinovendita", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal sc2ListinoVendita;
    @Column(name = "sc3listinovendita", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal sc3ListinoVendita;
    @Column(name = "qta1", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal qta1;
    @Column(name = "qta2", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal qta2;
    @Column(name = "qta3", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal qta3;
    @Column(name = "scqta1", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal scQta1;
    @Column(name = "scqta2", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal scQta2;
    @Column(name = "scqta3", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal scQta3;
    @Column(name = "offdal", columnDefinition = "DATE NULL DEFAULT NULL")
    LocalDate offDal;
    @Column(name = "offal", columnDefinition = "DATE NULL DEFAULT NULL")
    LocalDate offal;
    @Column(name = "scoffdalal", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal scOffDalAl;
    @Column(name = "qtaminordinabile", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal qtaMinOrdinabile;
    @Column(name = "ricaricominimo", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal ricaricoMinimo;
    @Column(name = "nondisponibile", precision = 1, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean nonDisponibile;

}
