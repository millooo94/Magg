package it.powerservice.managermag;

import jakarta.persistence.*;

@Entity
@Table(name = "tipologiepagamentidettagli")
public class TipologiePagamentiDettagli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "idpagamento", nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long tipologiePagamenti;
    @Column(name = "giorni", precision = 4, nullable = false, columnDefinition = "INT(4) NOT NULL")
    Integer giorni;
    @Column(name = "finemese", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean fineMese;
    @Column(name = "mese", precision = 2, nullable = false, columnDefinition = "TINYINT(2) NULL DEFAULT NULL")
    Boolean mese;
    @Column(name = "giorniaggiunti", precision = 4, columnDefinition = "INT(4) NULL DEFAULT NULL")
    Integer giorniAggiunti;
    @Column(name = "iva", precision = 1, columnDefinition = "TINYINT(1) NULL DEFAULT '0'")
    Boolean iva;

}
