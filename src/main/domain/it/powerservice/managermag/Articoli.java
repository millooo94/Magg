package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "articoli")
public class Articoli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "codice", length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    String codice;
    @Column(name = "codarticolosost", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String codArticoloSost;
    @Column(name = "descrizione", nullable = false, columnDefinition = "MEDIUMTEXT NOT NULL")
    String descrizione;
    @Column(name = "serialnumber", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("0=no sn, 1=sn duplicati, 2=sn senza duplicati. sostituisce i campi serialnumber e snduplicati")
    Short serialNumber;
    @Column(name = "fuoriproduzione", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short fuoriProduzione;
    @Column(name = "datafuoriproduzionedal", precision = 1, columnDefinition = "DATE NULL DEFAULT NULL")
    @Comment("ex campo DataNoninUso")
    LocalDate dataFuoriProduzioneDal;
    @Column(name = "materiaprima", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short materiaPrima;
    @Column(name = "servizio", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short servizio;
    @Column(name = "codfornitore", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String codFornitore;
    @Column(name = "descrfornitore", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String descrFornitore;
    @Column(name = "confezionamento", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short confezionamento;
    @Column(name = "scaricoinconfda", precision = 5, columnDefinition = "INT(5) NULL DEFAULT NULL")
    Integer scaricoInConfDa;
    @Column(name = "codcatalogo", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    @Comment("prende dal campo codice dei dizionari con categoria CATALOGHI")
    String codCatalogo;
    @Column(name = "unitamisura", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String unitaMisura;
    @Column(name = "unitamisuraacq", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String unitaMisuraAcq;
    @Column(name = "esenteivaart144", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("vecchio campo cknoivaart1c44")
    Short esenteIvaArt144;
    @Column(name = "codiva", length = 20, columnDefinition = "VARCHAR(20) NULL DEFAULT NULL")
    String codIva;
    @Column(name = "iva", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal iva;
    @Column(name = "descrizioneiva", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String descrizioneIva;
    @Column(name = "revcharge", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("ex campo revchange2015")
    Short revCharge;
    @Column(name = "codivarevcharge", length = 20, columnDefinition = "VARCHAR(20) NULL DEFAULT NULL")
    String codIvaRevCharge;
    @Column(name = "ivarevcharge", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal ivaRevCharge;
    @Column(name = "descrizioneivarevcharge", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String descrizioneIvaRevCharge;
    @Column(name = "ivato", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short ivato;
    @Column(name = "codcertalim", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String codCertAlim;
    @Column(name = "tempodirotazione", precision = 7, columnDefinition = "INT(7) NULL DEFAULT NULL")
    Integer tempoDiRotazione;
    @Column(name = "datains", columnDefinition = "DATETIME NOT NULL")
    LocalDateTime dataIns;
    @Column(name = "dataupd", columnDefinition = "DATETIME NULL DEFAULT NULL")
    LocalDateTime dataUpd;
    @Column(name = "notes", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String notes;
    @Column(name = "eliminato", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short eliminato;

}