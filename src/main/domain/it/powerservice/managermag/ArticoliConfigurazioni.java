package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import javax.print.attribute.standard.MediaSize;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "articoliconfigurazioni")
public class ArticoliConfigurazioni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "idarticolo", columnDefinition = "INT(11) NOT NULL")
    Long idArticolo;
    @Column(name = "tipo", length = 1, columnDefinition = "VARCHAR(1) NULL DEFAULT NULL")
    @Comment("B=base, null=le altre config per articolo")
    String tipo;
    @Column(name = "nomeconfigurazione", length = 50, nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
    String nomeConfigurazione;
    @Column(name = "codice", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String codice;
    @Column(name = "codicebarre", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String codiceBarre;
    @Column(name = "peso", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAUL NULL")
    BigDecimal peso;
    @Column(name = "pesonetto", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal pesoNetto;
    @Column(name = "costo", precision = 13, scale = 2, nullable = false, columnDefinition = "DECIMAL(13,2) NOT NULL")
    BigDecimal costo;
    @Column(name = "listinovendita", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    @Comment("vecchio campo listino tabella articolo")
    BigDecimal listinoVendita;
    @Column(name = "usalistinovendita", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("vecchio campo ckUsaListino tabella articolo")
    Boolean usaListinoVendita;
    @Column(name = "venditafissa", precision = 1, nullable = false, columnDefinition = "TINYINT NOT NULL DEFAULT '0'")
    @Comment("ex campo SeVenditaFissa su articolo")
    Boolean venditaFissa;
    @Column(name = "listinoacquisto", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    @Comment("ex campo listinoAcquisto di articolo")
    BigDecimal listinoAcquisto;
    @Column(name = "sc1listinoacquisto", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    @Comment("ex campo scontoalistino1 di articolo")
    BigDecimal sc1ListinoAcquisto;
    @Column(name = "sc2listinoacquisto", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    @Comment("ex campo scontoalistino2  di articolo")
    BigDecimal sc2ListinoAcquisto;
    @Column(name = "sc3listinoacquisto", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    @Comment("ex campo scontoalistino3  di articolo")
    BigDecimal sc3ListinoAcquisto;
    @Column(name = "contributo", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal contributo;
    @Column(name = "alertscortaminima", precision = 5, columnDefinition = "INT(5) NULL DEFAULT NULL")
    Integer alertScortaMinima;
    @Column(name = "qtaminordinabileacq", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    @Comment("ex campo qtaminordinabileAcq di articolo")
    BigDecimal qtaminordinabileAcq;
    @Column(name = "ricaricominimo", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    @Comment("campo ricaricoMinimo di articolo")
    BigDecimal ricaricoMinimo;
    @Column(name = "codicehs", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String codiceHS;
    @Column(name = "codicempn", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String codiceMPN;
    @Column(name = "imballo", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal imballo;
    @Column(name = "sottoimballo", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal sottoimballo;
    @Column(name = "pallet", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    @Comment("campo qtapallet di articolo")
    BigDecimal pallet;
    @Column(name = "altezza", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    @Comment("campo H articolo")
    BigDecimal altezza;
    @Column(name = "larghezza", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    @Comment("campo L di articolo")
    BigDecimal larghezza;
    @Column(name = "profondita", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    @Comment("campo P di articolo")
    BigDecimal profondita;
    @Column(name = "escludidaagglistino", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean escludidaagglistino;
    @Column(name = "escludidaarrotondamento", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean escludiDaArrotondamento;
    @Column(name = "fasciaprezzo", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    @Comment("presa da dizionari (campo codice) con categoria FASCE_PREZZO. Sostituisce il vecchio idfascia di articolo")
    String fasciaPrezzo;
    @Column(name = "richiediinvendita", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean richiediInVendita;
    @Column(name = "datains", columnDefinition = "DATETIME NOT NULL")
    LocalDateTime dataIns;
    @Column(name = "dataupd", columnDefinition = "DATETIME NULL DEFAULT NULL")
    LocalDateTime dataUpd;
    @Column(name = "noninuso", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean nonInUso;


}