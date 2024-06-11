package it.powerservice.managermag;

import jakarta.persistence.*;

@Entity
@Table(name = "listini")
public class Listini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "nome", length = 50, nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
    String nome;
    @Column(name = "predefinito", nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean predefinito;
    @Column(name = "modificabile", nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '1'")
    Boolean modificabile;
    @Column(name = "eliminato", nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean eliminato;
}
