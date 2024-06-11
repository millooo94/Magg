package it.powerservice.managermag;

import it.powerservice.managermag.keys.ArticoliAllocazioniConfigurazioniPKId;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import javax.print.attribute.standard.MediaSize;

@Entity
@Table(name = "articoliallocazioniconfigurazioni")
@IdClass(ArticoliAllocazioniConfigurazioniPKId.class)
public class ArticoliAllocazioniConfigurazioni {
    @Id
    @Column(name = "idarticoloconfigurazione", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idArticoloConfigurazione;
    @Id
    @Column(name = "iddeposito", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long idDeposito;
    @Id
    @Column(name = "tipoallocazione", length = 5, nullable = false)
    @Comment("P=Principale, S=Secondaria, T=Terza Allocazione, A=Altro")
    String tipoAllocazione;
    @Column(name = "allocazione", length = 255, nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    String allocazione;
    @Column(name = "scminima", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    String scMinima;
}
