package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Table(name = "anagraficheinfotrasportatori")
public class AnagraficheInfoTrasportatori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long id;
    @Column(name = "iscrizionealbo", length = 200, columnDefinition = "VARCHAR(200) NULL DEFAULT NULL")
    @Comment("ex campo iscrizionealbotrasp")
    String iscrizionealbo;
    @Column(name = "notespedizione", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String noteSpedizione;
    @Column(name = "tempoconsegna", precision = 11, columnDefinition = "INT(11) NULL DEFUALT NULL")
    Integer tempoConsegna;
    @Column(name = "costospedizione", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal costoSpedizione;
    @Column(name = "codserviziotrasportatore", length = 30, columnDefinition = "VARCHAR(30) NULL DEFAULT NULL")
    String codServizioTrasportatore;
    @Column(name = "codmittentetrasportatore", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String codMittenteTrasportatore;
    @Column(name = "codfilialetrasportatore", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String codFilialeTrasportatore;
    @Column(name = "codtariffatrasportatore", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String codTariffaTrasportatore;
}
