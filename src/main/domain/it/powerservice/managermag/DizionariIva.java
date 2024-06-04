package it.powerservice.managermag;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "dizionariiva")
public class DizionariIva {
    @Id
    @Column(name = "codiva", length = 20, nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    String codIva;
    @Column(name = "iva", precision = 13, scale = 2, nullable = false, columnDefinition = "DECIMAL(13, 2) NOT NULL")
    BigDecimal iva;
    @Column(name = "descrizione", length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    String descrizione;
    @Column(name = "default", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean default_;
    @Column(name = "nonmodificabile", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean nonModificabile;
    @Column(name = "ivaacquistoest_rc", precision = 13, scale = 2, columnDefinition = "DECIMAL(13, 2) NULL DEFAULT NULL")
    BigDecimal ivaAcquistoEst_RC;
    @Column(name = "naturafattpa", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String naturaFattPa;
    @Column(name = "reparto", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String reparto;
    @Column(name = "percdetraibilita", precision = 13, scale = 2, columnDefinition = "DECIMAL(13, 2) NULL DEFAULT NULL")
    BigDecimal percDetraibilita;
    @Column(name = "eliminato", precision = 1, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean eliminato;



}
