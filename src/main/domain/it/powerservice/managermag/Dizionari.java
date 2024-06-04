package it.powerservice.managermag;

import it.powerservice.managermag.keys.DizionariPKId;
import jakarta.persistence.*;

@Entity
@Table(name = "dizionari")
@IdClass(DizionariPKId.class)
public class Dizionari {
    @Id
    @Column(name = "codice", length = 50, nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
    String Codice;
    @Id
    @ManyToOne
    @JoinColumn(name = "categoria", nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    DizionariCategorie Categoria;
    @Column(name = "descrizione", length = 255, nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    String Descrizione;
    @Column(name = "modificabile", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean modificabile;
}
