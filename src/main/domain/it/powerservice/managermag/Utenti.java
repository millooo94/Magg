package it.powerservice.managermag;

import jakarta.persistence.*;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDateTime;

@Entity
@Table(name = "utenti")
public class Utenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, precision = 11, columnDefinition = "BIGINT(11) NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "utente", length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    String Utente;
    @Column(name = "password", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String Password;
    @Column(name = "datains", columnDefinition = "DATETIME NOT NULL")
    LocalDateTime dataIns;
    @Column(name = "dataupd", columnDefinition = "DATETIME NULL DEFAULT NULL")
    LocalDateTime dataUpd;
}
