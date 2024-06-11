package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;


@Entity
@Table(name = "indirizzi")
public class Indirizzi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL AUTO_INCREMENT")
    Long id;
    @Column(name = "idanagrafica", precision = 11, columnDefinition = "INT(11) NULL DEFAULT NULL")
    Long idAnagrafica;
    @Column(name = "iddocumento", precision = 11, columnDefinition = "INT(11) NULL DEFAULT NULL")
    @Comment("Per il futuro quando ci sarà la tabella documenti ricordardi si fare la FK di ccd")
    Long idDocumento;
    @Column(name = "tipoindirizzo", length = 5, nullable = false, columnDefinition = "VARCHAR(5) NOT NULL")
    @Comment("S=SEDE nD=Destinazione Principale nSD=Destinazione Secondaria nR=Referente (sostituisce la tabella referentiFornitori) nA=Altro")
    String tipoIndirizzo;
    @Column(name = "denominazionealternativa", length = 150, columnDefinition = "VARCHAR(150) NULL DEFAULT NULL")
    String denominazioneAlternativa;
    @Column(name = "nazione", length = 150, nullable = false, columnDefinition = "VARCHAR(150) NOT NULL")
    String nazione;
    @Column(name = "indirizzo", length = 255, nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    String indirizzo;
    @Column(name = "provincia", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String provincia;
    @Column(name = "comune", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String comune;
    @Column(name = "frazione", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String frazione;
    @Column(name = "regione", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String regione;
    @Column(name = "cap", length = 20, columnDefinition = "VARCHAR(20) NULL DEFAULT NULL")
    String cap;
    @Column(name = "codsdi", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String codSdi;
    @Column(name = "telefono1", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String telefono1;
    @Column(name = "telefono2", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String telefono2;
    @Column(name = "telefono3", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String telefono3;
    @Column(name = "fax", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String fax;
    @Column(name = "email", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String email;
    @Column(name = "pec", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String pec;
    @Column(name = "sito", length = 150, columnDefinition = "VARCHAR(150) NULL DEFAULT NULL")
    String sito;
    @Column(name = "referente", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String referente;
    @Column(name = "recapitoreferente", length = 150, columnDefinition = "VARCHAR(150) NULL DEFAULT NULL")
    String recapitoReferente;
    @Column(name = "notes", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String notes;
}
