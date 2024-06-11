package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Table(name = "tipologiepagamenti")
public class TipologiePagamenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "idbanca", columnDefinition = "INT(11) NULL DEFAULT NULL")
    Long idBanca;
    @Column(name = "codice", length = 50, nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
    String codice;
    @Column(name = "descrizione", length = 150, nullable = false, columnDefinition = "VARCHAR(150) NOT NULL")
    String descrizione;
    @Column(name = "tipo", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL")
    @Comment("0=Azienda - 1=Cli/For - 2=Nessuno dei due")
    Boolean tipo;
    @Column(name = "riba", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean riba;
    @Column(name = "regolaauto", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean regolaAuto;
    @Column(name = "avvisi", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean avvisi;
    @Column(name = "modpagfattpa", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String modPagFattPA;
    @Column(name = "condpagfattpa", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String condPagFattPA;
    @Column(name = "sconto", precision = 13, scale = 2, columnDefinition = "DECIMAL(13, 2) NULL DEFAULT NULL")
    BigDecimal sconto;
    @Column(name = "speseincasso", precision = 12, scale = 2, columnDefinition = "DECIMAL(13, 2) NULL DEFAULT NULL")
    BigDecimal speseIncasso;
    @Column(name = "nonmodificabile", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean nonModificabile;
    @Column(name = "eliminato", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean eliminato;
}
