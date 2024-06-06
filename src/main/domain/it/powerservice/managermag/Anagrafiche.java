package it.powerservice.managermag;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "anagrafiche")
public class Anagrafiche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", precision = 11, nullable = false, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    Long Id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtrasportatore", referencedColumnName = "id")
    Anagrafiche idTrasportatore;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idlistino", referencedColumnName = "id")
    Listini idListino;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipologiapagamento", referencedColumnName = "id")
    Listini idTipologiaPagamento;
    @Column(name = "codice", length = 20, nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    String codice;
    @Column(name = "tipo", length = 20, nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    @Comment("Un anag. può essere C Cliente / F fornitore / T Trasportatore / A Agente / P Personale - Gestirla con delle @. Mettendole sempre tutte Es. C@@@@ Se multi C@@T@@P")
    String tipo;
    @Column(name = "subcategoria", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    @Comment("Collegata con tabella DIZIONARI con categoriaDizionario=SUBCAT_ANAG , restituisce il campo descrizione del dizionario")
    String subCategoria;
    @Column(name = "cognomergs", length = 150, nullable = false, columnDefinition = "VARCHAR(150) NOT NULL")
    String cognomeRgs;
    @Column(name = "nome", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String nome;
    @Column(name = "codsdi", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String codSDI;
    @Column(name = "sesso", length = 1, columnDefinition = "VARCHAR(1) NULL DEFAULT NULL")
    String sesso;
    @Column(name = "prodottieservizi", length = 150, columnDefinition = "VARCHAR(150) NULL DEFAULT NULL")
    String prodottiEServizi;
    @Column(name = "codiva", length = 20, columnDefinition = "VARCHAR(20) NULL DEFAULT NULL")
    String codIVA;
    @Column(name = "iva", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal iva;
    @Column(name = "descrizioneiva", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String descrizioneIva;
    @Column(name = "fido", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal fido;
    @Column(name = "sconto1", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal sconto1;
    @Column(name = "sconto2", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal sconto2;
    @Column(name = "sconto3", precision = 13, scale = 2, columnDefinition = "DECIMAL(13,2) NULL DEFAULT NULL")
    BigDecimal sconto3;
    @Column(name = "notes", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String notes;
    @Column(name = "notefissedocumento", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    @Comment("ex campo NoteFisseFattura")
    String noteFisseDocumento;
    @Column(name = "noterapide", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String noteRapide;
    @Column(name = "lingua", length = 1, nullable = false, columnDefinition = "VARCHAR(1) NOT NULL DEFAULT 'I'")
    @Comment("Ex campo LuguaWeb")
    String lingua;
    @Column(name = "fasciaappartenenza", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String fasciaAppartenenza;
    @Column(name = "certificazionealimentare", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short certificazioneAlimentare;
    @Column(name = "assicurazione", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short assicurazione;
    @Column(name = "dataassicurazione", columnDefinition = "DATE NULL DEFAULT NULL")
    LocalDate dataAssicurazione;
    @Column(name = "ggchiusura", length = 50, columnDefinition = "VARCHAR(50) NULL DEFUALT NULL")
    String ggChiusura;
    @Column(name = "hhchiusura", length = 50, columnDefinition = "VARCHAR(50) NULL DEFUALT NULL")
    String hhChiusura;
    @Column(name = "status", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    @Comment("Collegata con tabella DIZIONARI con categoriaDizionario=STATUS_ANAG , restituisce il campo codice del dizionario")
    String status;
    @Column(name = "datanoninuso", columnDefinition = "DATE NULL DEFAULT NULL")
    LocalDate dataNonInUso;
    @Column(name = "cciiaa", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String cciiaa;
    @Column(name = "enasarco", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String enasarco;
    @Column(name = "newsletter", precision = 1, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short newsLetter;
    @Column(name = "testoinviomail", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String testoInvioMail;
    @Column(name = "revcharge", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short revCharge;
    @Column(name = "splitpayment", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAUlT '0'")
    Short splitPayment;
    @Column(name = "ceeextracee", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAUlT '0'")
    @Comment("0=cee 1=extraCee")
    Short ceeExtraCee;
    @Column(name = "colore", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String colore;
    @Column(name = "tipodocumentoidentita", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    @Comment("CI=Carta Identità, PA=Patente, PT=Passaporto")
    String tipoDocumentoidentita;
    @Column(name = "numerodocumentoidentita", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT NULL")
    String numeroDocumentoidentita;
    @Column(name = "emessodadocumentoidentita", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String emessoDaDocumentoidentita;
    @Column(name = "dataemissionedocumentoidentita", columnDefinition = "DATE NULL DEFAULT NULL")
    LocalDate dataEmissioneDocumentoIdentita;
    @Column(name = "provincianascita", length = 10, columnDefinition = "VARCHAR(10) NULL DEFAULT NULL")
    String provinciaNascita;
    @Column(name = "comunenascita", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String comuneNascita;
    @Column(name = "datains", nullable = false, columnDefinition = "DATETIME NOT NULL")
    @Comment("usa vecchio campo data_creazione")
    LocalDateTime dataIns;
    @Column(name = "dataupd", columnDefinition = "DATETIME NULL DEFAULT NULL")
    LocalDateTime dataUpd;
    @Column(name = "eliminato", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Short eliminato;
}
