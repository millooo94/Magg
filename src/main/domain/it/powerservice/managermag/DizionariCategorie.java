package it.powerservice.managermag;

import it.powerservice.managermag.enums.ColonnaRestituita;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "dizionaricategorie")
public class DizionariCategorie {
    @Id
    @Column(name = "categoria", length = 20, nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    String Categoria;
    @Column(name = "descrizione", length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    String descrizione;
    @Column(name = "colonnarestituita", length = 1, nullable = false, columnDefinition = "VARCHAR(1) NOT NULL")
    @Comment("C=Codice D=Descrizione")
    ColonnaRestituita colonnaRestituita;
    @Column(name = "mostracodice", nullable = false, columnDefinition = "TINYINT NOT NULL DEFAULT '1'")
    Short mostraCodice;

}
