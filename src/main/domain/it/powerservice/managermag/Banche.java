package it.powerservice.managermag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "banche")
public class Banche {
    @Id
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "agenzia", length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL" )
    @Comment("Se nullo è banca aziendale")
    String agenzia;
    @Column(name = "iban", length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    String iban;
    @Column(name = "abi", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String abi;
    @Column(name = "cab", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String cab;
    @Column(name = "cin", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String cin;
    @Column(name = "cc", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String cc;
    @Column(name = "swift", length = 20, columnDefinition = "VARCHAR(20) NULL DEFAULT NULL")
    String swift;
    @Column(name = "localita", length = 20, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String localita;
    @Column(name = "indirizzo", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String indirizzo;
    @Column(name = "codsia", length = 20, columnDefinition = "VARCHAR(20) NULL DEFAULT NULL")
    String codSIA;
    @Column(name = "predefinito", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    @Comment("per ogni anag si crea se è la 1° per quella anag, quella predefinita")
    Short predefinito;
    @Column(name = "eliminato", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short eliminato;

}
