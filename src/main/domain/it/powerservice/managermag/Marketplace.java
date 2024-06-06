package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "marketplace")
public class Marketplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT")
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idlistino", columnDefinition = "INT(11) NULL DEFAULT NULL", referencedColumnName = "id")
    Listini idListino;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idlistinoinglese", columnDefinition = "INT(11) NULL DEFAULT NULL", referencedColumnName = "id")
    Listini idListinoInglese;
    @Column(name = "tipo", length = 20, nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    @Comment("W=Web Interno, E=Ebay, A=Amazon, T=TrovaPrezzi,  S=Subito, M=manomano, EP=EPrice, F=Facebook, LM=LeroyMerlin")
    String tipo;
    @Column(name = "pathexport", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String pathExport;
    @Column(name = "nonesportaregiacenzamaga", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short nonEsportareGiacenzaMaga;
    @Column(name = "disponibilitamenoscmin", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short disponibilitamenoScMin;
    @Column(name = "nonesportaresedispneg", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short nonEsportareSeDispNeg;
    @Column(name = "disponibilitafissa", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short disponibilitaFissa;
    @Column(name = "disponibilitanonzero", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short disponibilitaNonZero;
    @Column(name = "spesesped", precision = 13, scale = 2, columnDefinition = "DECIMAL(13, 2) NULL DEFAULT NULL")
    Short speseSped;
    @Column(name = "tempispedizione", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String tempiSpedizione;
    @Column(name = "prezzoperkg", precision = 13, scale = 2, columnDefinition = "DECIMAL(13, 2) NULL DEFAULT NULL")
    Short prezzoperkg;
    @Column(name = "nonmodificabile", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short nonModificabile;
}
