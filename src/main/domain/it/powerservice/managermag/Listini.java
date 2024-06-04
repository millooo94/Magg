package it.powerservice.managermag;

import jakarta.persistence.*;

@Entity
@Table(name = "listini")
public class Listini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "nome", length = 50, nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
    String nome;
    @Column(name = "predefinito", nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    Boolean predefinito;
    @Column(name = "modificabile", nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT TRUE")
    Boolean modificabile;
    @Column(name = "eliminato", nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    Boolean eliminato;

    @Override
    public String toString() {
        return "Listini{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", predefinito=" + predefinito +
                ", modificabile=" + modificabile +
                ", eliminato=" + eliminato +
                '}';
    }
}
