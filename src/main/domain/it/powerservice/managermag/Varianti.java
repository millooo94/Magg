package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "varianti")
public class Varianti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "idarticolo", columnDefinition = "INT(11) NULL DEFAULT NULL")
    @Comment("Serve solo per tipo=V'")
    Long idArticolo;
    @Column(name = "tipo", length = 10, nullable = false, columnDefinition = "VARCHAR(10) NOT NULL")
    @Comment("C=Categoria, T=Tipologia, V=Variante")
    String tipo;
    @Column(name = "descrizione", length = 255, nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    String descrizione;
    @Column(name = "descrizioneeng", length = 255, nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    String descrizioneEng;
    @Column(name = "obbligatorio", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("Serve solo per tipo=T")
    Boolean obbligatorio;
    @Column(name = "escludistampa", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("Serve solo per tipo=T e V")
    Boolean escludiStampa;
    @Column(name = "multiselezionefigli", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("Serve solo per tipo=T")
    Boolean multiSelezioneFigli;
    @Column(name = "filtroweb", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("Serve solo per tipo=T")
    Boolean filtroWeb;
    @Column(name = "chkzeroarticolo", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("Serve solo per tipo=V")
    Boolean chkZeroArticolo;
    @Column(name = "codcertalimentare", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    @Comment("Serve solo per tipo=V")
    String codCertAlimentare;
    @Column(name = "note", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String note;
    @Column(name = "eliminato", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean eliminato;
    @Column(name = "invisibile", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean invisibile;



}
