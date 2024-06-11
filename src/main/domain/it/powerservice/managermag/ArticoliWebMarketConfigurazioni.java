package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Entity
@Table(name = "articoliwebmarketconfigurazioni")
public class ArticoliWebMarketConfigurazioni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "idarticoloconfigurazione", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idArticoloConfigurazione;
    @Column(name = "idmarketplace", precision = 11, nullable = false, columnDefinition = "iINT(11) NOT NULL DEFAULT '0'")
    Integer idMarketplace;
    @Column(name = "ereditaconfigbasepercampivuoti", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT'0'")
    Short ereditaCofigBasePerCampiVuoti;
    @Column(name = "visibile", precision = 1, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("sostituisce il visibileWeb se market=web, mandasuebay se market=EBAY1, mandasuEbay2 se market =EBAY2 etc...")
    Short visibile;
    @Column(name = "descrizione", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String descrizione;
    @Column(name = "descrizioneinglese", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String descrizioneInglese;
    @Column(name = "descrizionelungainglese", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String descrizioneLungaInglese;
    @Column(name = "metatitle", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String metatitle;
    @Column(name = "metatitleinglese", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String metatitleInglese;
    @Column(name = "metadescription", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String metadescription;
    @Column(name = "metadescriptioninglese", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String metadescriptionInglese;
    @Column(name = "condizione", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    @Comment("campo condizione se web, condizione di ogni altro tipo di market (se serve)")
    String condizione;
    @Column(name = "condizionetesto", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String condizioneTesto;
    @Column(name = "novita", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT'0'")
    Boolean novita;
    @Column(name = "ordine", precision = 7, columnDefinition = "INT(7) NOT NULL DEFAULT '0'")
    Integer ordine;
    @Column(name = "vetrina", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean vetrina;
    @Column(name = "ggdisponibilita", precision = 5, columnDefinition = "INT(5) NULL DEFAUL NULL")
    @Comment("sostituisce campo ggProduzione")
    Integer ggDisponibilita;
    @Column(name = "datadisponibilita", columnDefinition = "DATE NULL DEFAULT NULL")
    @Comment("ex campo datadisponibilita di articolo")
    LocalDate dataDisponibilita;
    @Column(name = "ggriassortimento", precision = 5, columnDefinition = "INT(5) NULL DEFAULT NULL")
    @Comment("sostituisce campo settLavoroProduzione")
    Integer giorniRiassortimento;
    @Column(name = "tempodiconsegna", precision = 5, columnDefinition = "INT(5) NULL DEFAULT NULL")
    Integer tempoDiConsegna;
    @Column(name = "bestseller", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean bestSeller;
    @Column(name = "countdown", precision = 1, nullable = false, columnDefinition = "TINYINT NOT NULL DEFAULT '0'")
    Boolean countDown;
}