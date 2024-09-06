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
    @Column(name = "idtrasportatore", precision = 11, columnDefinition = "INT(11) NULL DEFAULT NULL")
    Long idTrasportatore;
    @Column(name = "idlistino", precision = 11, nullable = false, columnDefinition = "INT(11) NOT NULL DEFAULT '0'")
    Long idListino;
    @Column(name = "idtipologiapagamento", precision = 11, columnDefinition = "INT(11) NULL DEFAULT NULL")
    Long idTipologiaPagamento;
    @Column(name = "codice", length = 20, nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    String codice;
    @Column(name = "tipo", length = 20, nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    @Comment("Un anag. può essere C Cliente / F fornitore / T Trasportatore / A Agente / P Personale")
    String tipo;
    @Column(name = "ckcliente", columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean ckCliente;
    @Column(name = "ckfornitore", columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean ckFornitore;
    @Column(name = "cktrasportatore", columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean ckTrasportatore;
    @Column(name = "ckagente", columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean ckAgente;
    @Column(name = "ckpersonale", columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean ckPersonale;
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
    Boolean certificazioneAlimentare;
    @Column(name = "assicurazione", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean assicurazione;
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
    Boolean newsLetter;
    @Column(name = "testoinviomail", columnDefinition = "MEDIUMTEXT NULL DEFAULT NULL")
    String testoInvioMail;
    @Column(name = "revcharge", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean revCharge;
    @Column(name = "splitpayment", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAUlT '0'")
    Boolean splitPayment;
    @Column(name = "ceeextracee", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAUlT '0'")
    @Comment("0=cee 1=extraCee")
    Boolean ceeExtraCee;
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
    @Column(name = "codicefidelity", length = 100, columnDefinition = "VARCHAR(100) NULL DEFAULT NULL")
    String codiceFidelity;
    @Column(name = "datains", nullable = false, columnDefinition = "DATETIME NOT NULL")
    @Comment("usa vecchio campo data_creazione")
    LocalDateTime dataIns;
    @Column(name = "dataupd", columnDefinition = "DATETIME NULL DEFAULT NULL")
    LocalDateTime dataUpd;
    @Column(name = "eliminato", precision = 1, nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0'")
    Boolean eliminato;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getIdTrasportatore() {
        return idTrasportatore;
    }

    public void setIdTrasportatore(Long idTrasportatore) {
        this.idTrasportatore = idTrasportatore;
    }

    public Long getIdListino() {
        return idListino;
    }

    public void setIdListino(Long idListino) {
        this.idListino = idListino;
    }

    public Long getIdTipologiaPagamento() {
        return idTipologiaPagamento;
    }

    public void setIdTipologiaPagamento(Long idTipologiaPagamento) {
        this.idTipologiaPagamento = idTipologiaPagamento;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String getCognomeRgs() {
        return cognomeRgs;
    }

    public void setCognomeRgs(String cognomeRgs) {
        this.cognomeRgs = cognomeRgs;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodSDI() {
        return codSDI;
    }

    public void setCodSDI(String codSDI) {
        this.codSDI = codSDI;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getProdottiEServizi() {
        return prodottiEServizi;
    }

    public void setProdottiEServizi(String prodottiEServizi) {
        this.prodottiEServizi = prodottiEServizi;
    }

    public String getCodIVA() {
        return codIVA;
    }

    public void setCodIVA(String codIVA) {
        this.codIVA = codIVA;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public String getDescrizioneIva() {
        return descrizioneIva;
    }

    public void setDescrizioneIva(String descrizioneIva) {
        this.descrizioneIva = descrizioneIva;
    }

    public BigDecimal getFido() {
        return fido;
    }

    public void setFido(BigDecimal fido) {
        this.fido = fido;
    }

    public BigDecimal getSconto1() {
        return sconto1;
    }

    public void setSconto1(BigDecimal sconto1) {
        this.sconto1 = sconto1;
    }

    public BigDecimal getSconto2() {
        return sconto2;
    }

    public void setSconto2(BigDecimal sconto2) {
        this.sconto2 = sconto2;
    }

    public BigDecimal getSconto3() {
        return sconto3;
    }

    public void setSconto3(BigDecimal sconto3) {
        this.sconto3 = sconto3;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNoteFisseDocumento() {
        return noteFisseDocumento;
    }

    public void setNoteFisseDocumento(String noteFisseDocumento) {
        this.noteFisseDocumento = noteFisseDocumento;
    }

    public String getNoteRapide() {
        return noteRapide;
    }

    public void setNoteRapide(String noteRapide) {
        this.noteRapide = noteRapide;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getFasciaAppartenenza() {
        return fasciaAppartenenza;
    }

    public void setFasciaAppartenenza(String fasciaAppartenenza) {
        this.fasciaAppartenenza = fasciaAppartenenza;
    }

    public Boolean getCertificazioneAlimentare() {
        return certificazioneAlimentare;
    }

    public void setCertificazioneAlimentare(Boolean certificazioneAlimentare) {
        this.certificazioneAlimentare = certificazioneAlimentare;
    }

    public Boolean getAssicurazione() {
        return assicurazione;
    }

    public void setAssicurazione(Boolean assicurazione) {
        this.assicurazione = assicurazione;
    }

    public LocalDate getDataAssicurazione() {
        return dataAssicurazione;
    }

    public void setDataAssicurazione(LocalDate dataAssicurazione) {
        this.dataAssicurazione = dataAssicurazione;
    }

    public String getGgChiusura() {
        return ggChiusura;
    }

    public void setGgChiusura(String ggChiusura) {
        this.ggChiusura = ggChiusura;
    }

    public String getHhChiusura() {
        return hhChiusura;
    }

    public void setHhChiusura(String hhChiusura) {
        this.hhChiusura = hhChiusura;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataNonInUso() {
        return dataNonInUso;
    }

    public void setDataNonInUso(LocalDate dataNonInUso) {
        this.dataNonInUso = dataNonInUso;
    }

    public String getCciiaa() {
        return cciiaa;
    }

    public void setCciiaa(String cciiaa) {
        this.cciiaa = cciiaa;
    }

    public String getEnasarco() {
        return enasarco;
    }

    public void setEnasarco(String enasarco) {
        this.enasarco = enasarco;
    }

    public Boolean getNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(Boolean newsLetter) {
        this.newsLetter = newsLetter;
    }

    public String getTestoInvioMail() {
        return testoInvioMail;
    }

    public void setTestoInvioMail(String testoInvioMail) {
        this.testoInvioMail = testoInvioMail;
    }

    public Boolean getRevCharge() {
        return revCharge;
    }

    public void setRevCharge(Boolean revCharge) {
        this.revCharge = revCharge;
    }

    public Boolean getSplitPayment() {
        return splitPayment;
    }

    public void setSplitPayment(Boolean splitPayment) {
        this.splitPayment = splitPayment;
    }

    public Boolean getCeeExtraCee() {
        return ceeExtraCee;
    }

    public void setCeeExtraCee(Boolean ceeExtraCee) {
        this.ceeExtraCee = ceeExtraCee;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getTipoDocumentoidentita() {
        return tipoDocumentoidentita;
    }

    public void setTipoDocumentoidentita(String tipoDocumentoidentita) {
        this.tipoDocumentoidentita = tipoDocumentoidentita;
    }

    public String getNumeroDocumentoidentita() {
        return numeroDocumentoidentita;
    }

    public void setNumeroDocumentoidentita(String numeroDocumentoidentita) {
        this.numeroDocumentoidentita = numeroDocumentoidentita;
    }

    public String getEmessoDaDocumentoidentita() {
        return emessoDaDocumentoidentita;
    }

    public void setEmessoDaDocumentoidentita(String emessoDaDocumentoidentita) {
        this.emessoDaDocumentoidentita = emessoDaDocumentoidentita;
    }

    public LocalDate getDataEmissioneDocumentoIdentita() {
        return dataEmissioneDocumentoIdentita;
    }

    public void setDataEmissioneDocumentoIdentita(LocalDate dataEmissioneDocumentoIdentita) {
        this.dataEmissioneDocumentoIdentita = dataEmissioneDocumentoIdentita;
    }

    public String getProvinciaNascita() {
        return provinciaNascita;
    }

    public void setProvinciaNascita(String provinciaNascita) {
        this.provinciaNascita = provinciaNascita;
    }

    public String getComuneNascita() {
        return comuneNascita;
    }

    public void setComuneNascita(String comuneNascita) {
        this.comuneNascita = comuneNascita;
    }

    public LocalDateTime getDataIns() {
        return dataIns;
    }

    public void setDataIns(LocalDateTime dataIns) {
        this.dataIns = dataIns;
    }

    public LocalDateTime getDataUpd() {
        return dataUpd;
    }

    public void setDataUpd(LocalDateTime dataUpd) {
        this.dataUpd = dataUpd;
    }

    public Boolean getEliminato() {
        return eliminato;
    }

    public void setEliminato(Boolean eliminato) {
        this.eliminato = eliminato;
    }

    public Boolean getCkCliente() {
        return ckCliente;
    }

    public void setCkCliente(Boolean ckCliente) {
        this.ckCliente = ckCliente;
    }

    public Boolean getCkFornitore() {
        return ckFornitore;
    }

    public void setCkFornitore(Boolean ckFornitore) {
        this.ckFornitore = ckFornitore;
    }

    public Boolean getCkTrasportatore() {
        return ckTrasportatore;
    }

    public void setCkTrasportatore(Boolean ckTrasportatore) {
        this.ckTrasportatore = ckTrasportatore;
    }

    public Boolean getCkAgente() {
        return ckAgente;
    }

    public void setCkAgente(Boolean ckAgente) {
        this.ckAgente = ckAgente;
    }

    public Boolean getCkPersonale() {
        return ckPersonale;
    }

    public void setCkPersonale(Boolean ckPersonale) {
        this.ckPersonale = ckPersonale;
    }

    public String getCodiceFidelity() {
        return codiceFidelity;
    }

    public void setCodiceFidelity(String codiceFidelity) {
        this.codiceFidelity = codiceFidelity;
    }

    @Override
    public String toString() {
        return "Anagrafiche{" +
                "Id=" + Id +
                ", idTrasportatore=" + idTrasportatore +
                ", idListino=" + idListino +
                ", idTipologiaPagamento=" + idTipologiaPagamento +
                ", codice='" + codice + '\'' +
                ", tipo='" + tipo + '\'' +
                ", ckCliente=" + ckCliente +
                ", ckFornitore=" + ckFornitore +
                ", ckTrasportatore=" + ckTrasportatore +
                ", ckAgente=" + ckAgente +
                ", ckPersonale=" + ckPersonale +
                ", subCategoria='" + subCategoria + '\'' +
                ", cognomeRgs='" + cognomeRgs + '\'' +
                ", nome='" + nome + '\'' +
                ", codSDI='" + codSDI + '\'' +
                ", sesso='" + sesso + '\'' +
                ", prodottiEServizi='" + prodottiEServizi + '\'' +
                ", codIVA='" + codIVA + '\'' +
                ", iva=" + iva +
                ", descrizioneIva='" + descrizioneIva + '\'' +
                ", fido=" + fido +
                ", sconto1=" + sconto1 +
                ", sconto2=" + sconto2 +
                ", sconto3=" + sconto3 +
                ", notes='" + notes + '\'' +
                ", noteFisseDocumento='" + noteFisseDocumento + '\'' +
                ", noteRapide='" + noteRapide + '\'' +
                ", lingua='" + lingua + '\'' +
                ", fasciaAppartenenza='" + fasciaAppartenenza + '\'' +
                ", certificazioneAlimentare=" + certificazioneAlimentare +
                ", assicurazione=" + assicurazione +
                ", dataAssicurazione=" + dataAssicurazione +
                ", ggChiusura='" + ggChiusura + '\'' +
                ", hhChiusura='" + hhChiusura + '\'' +
                ", status='" + status + '\'' +
                ", dataNonInUso=" + dataNonInUso +
                ", cciiaa='" + cciiaa + '\'' +
                ", enasarco='" + enasarco + '\'' +
                ", newsLetter=" + newsLetter +
                ", testoInvioMail='" + testoInvioMail + '\'' +
                ", revCharge=" + revCharge +
                ", splitPayment=" + splitPayment +
                ", ceeExtraCee=" + ceeExtraCee +
                ", colore='" + colore + '\'' +
                ", tipoDocumentoidentita='" + tipoDocumentoidentita + '\'' +
                ", numeroDocumentoidentita='" + numeroDocumentoidentita + '\'' +
                ", emessoDaDocumentoidentita='" + emessoDaDocumentoidentita + '\'' +
                ", dataEmissioneDocumentoIdentita=" + dataEmissioneDocumentoIdentita +
                ", provinciaNascita='" + provinciaNascita + '\'' +
                ", comuneNascita='" + comuneNascita + '\'' +
                ", dataIns=" + dataIns +
                ", dataUpd=" + dataUpd +
                ", eliminato=" + eliminato +
                '}';
    }
}
