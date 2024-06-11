package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "datiazienda")
public class DatiAzienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL")
    Long id;
    @Column(name = "ragionesociale", length = 60, columnDefinition = "VARCHAR(60) NULL DEFAULT NULL")
    String ragioneSociale;
    @Column(name = "denominazione", length = 60, columnDefinition = "VARCHAR(60) NULL DEFAULT NULL")
    String denominazione;
    @Column(name = "indirizzo", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String indirizzo;
    @Column(name = "telefono", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String telefono;
    @Column(name = "comune", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String comune;
    @Column(name = "provincia", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String provincia;
    @Column(name = "cap", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String cap;
    @Column(name = "regimprese", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String RegImprese;
    @Column(name = "ccia", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String CCIA;
    @Column(name = "PIVA", length = 20, columnDefinition = "VARCHAR(20) NULL DEFAULT NULL")
    String PIVA;
    @Column(name = "codicefiscale", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String CodiceFiscale;
    @Column(name = "fax", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String Fax;
    @Column(name = "email", length = 70, columnDefinition = "VARCHAR(70) NULL DEFAULT NULL")
    String Email;
    @Column(name = "emailconferma", length = 70, columnDefinition = "VARCHAR(70) NULL DEFAULT NULL")
    String EmailConferma;
    @Column(name = "sito", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String Sito;
    @Column(name = "tipohttp", precision = 11,columnDefinition = "INT(11) NULL DEFAULT NULL")
    @Comment("1=Http - 2=Https")
    Boolean TipoHttp;
    @Column(name = "capsociale", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal CapSociale;
    @Column(name = "regimefiscale", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String RegimeFiscale;
    @Column(name = "codiceaziendafattpa", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String codiceAziendaFATTPA;
    @Column(name = "rapprlegale_privacy", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String rapprlegalePrivacy;
    @Column(name = "rapprlegale_email", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String rapprlegaleEmail;
    @Column(name = "pecaziendale", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String pecAziendale;
    @Column(name = "sdiazienda", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String sdiAzienda;
    @Column(name = "provinciafinanza", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String provinciaFinanza;
    @Column(name = "numeroautorizzazione", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String numeroAutorizzazione;
    @Column(name = "dataautorizzazione", columnDefinition = "DATETIME NULL DEFAULT NULL")
    LocalDateTime dataAutorizzazione;
    @Column(name = "userlicenza", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String userLicenza;
    @Column(name = "passwordlicenza", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String passwordLicenza;
    @Column(name = "userdownloadlicenza", length = 255, columnDefinition = "VARCHAR(255) NULL DEFAULT NULL")
    String userDownloadLicenza;


}
