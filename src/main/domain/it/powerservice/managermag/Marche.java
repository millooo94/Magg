package it.powerservice.managermag;

import jakarta.persistence.*;

@Entity
@Table(name = "marche")
public class Marche {
    @Id
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "BIGINT NOT NULL")
    Long id;
    @Column(name = "marca", nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    String marca;
    @Column(name = "eliminato", nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    Boolean eliminato;
}
