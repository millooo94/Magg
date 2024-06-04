package it.powerservice.managermag;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "depositi")
public class Depositi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "codice", length = 50, nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
    String codice;
    @Column(name = "nome", length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    String nome;
    @Column(name = "nonmodificabile", nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    Boolean nonModificabile;
    @Column(name = "datanoninuso", nullable = false, columnDefinition = "DATE NULL DEFAULT NULL")
    LocalDate dataNonInUso;
    @Column(name = "esterno", nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    Boolean merceIndisponibile;
}
