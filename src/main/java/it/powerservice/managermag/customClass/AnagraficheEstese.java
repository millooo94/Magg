package it.powerservice.managermag.customClass;

import it.powerservice.managermag.Anagrafiche;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class AnagraficheEstese extends Anagrafiche {
    String pIva;
    String codiceFiscale;
    String regione;
    String provincia;
    String comune;
    Date provaData;

    public AnagraficheEstese (Long id, Long idTrasportatore, Long idListino, Long idTipologiaPagamento, String codice, String tipo, Boolean ckCliente, Boolean ckFornitore, Boolean ckTrasportatore, Boolean ckAgente, Boolean ckPersonale, String soggetto, String subCategoria, String cognome, String nome, String ragioneSociale, String codSDI, String sesso, String prodottiEServizi, String codIVA, BigDecimal iva, String descrizioneIva, BigDecimal fido, BigDecimal sconto1, BigDecimal sconto2, BigDecimal sconto3, String notes, String noteFisseDocumento, String noteRapide, String lingua, String fasciaAppartenenza, Boolean certificazioneAlimentare, Boolean assicurazione, LocalDate dataAssicurazione, String ggChiusura, String hhChiusura, String status, LocalDate dataNonInUso, String cciiaa, String enasarco, Boolean newsLetter, String testoInvioMail, Boolean revCharge, Boolean splitPayment, Boolean ceeExtraCee, String colore, String tipoDocumentoidentita, String numeroDocumentoidentita, String emessoDaDocumentoidentita, LocalDate dataEmissioneDocumentoIdentita, String provinciaNascita, String comuneNascita, String codiceFidelity, LocalDateTime dataIns, LocalDateTime dataUpd, Boolean eliminato, String pIva, String codiceFiscale, String regione, String provincia, String comune) {
        super(id, idTrasportatore, idListino, idTipologiaPagamento, codice, ckCliente, ckFornitore, ckTrasportatore, ckAgente, ckPersonale, soggetto, subCategoria, cognome, nome, ragioneSociale, codSDI, sesso, prodottiEServizi, codIVA, iva, descrizioneIva, fido, sconto1, sconto2, sconto3, notes, noteFisseDocumento, noteRapide, lingua, fasciaAppartenenza, certificazioneAlimentare, assicurazione, dataAssicurazione, ggChiusura, hhChiusura, status, dataNonInUso, cciiaa, enasarco, newsLetter, testoInvioMail, revCharge, splitPayment, ceeExtraCee, colore, tipoDocumentoidentita, numeroDocumentoidentita, emessoDaDocumentoidentita, dataEmissioneDocumentoIdentita, provinciaNascita, comuneNascita, codiceFidelity, dataIns, dataUpd, eliminato);
        this.pIva = pIva;
        this.codiceFiscale = codiceFiscale;
        this.comune = comune;
        this.provincia = provincia;
        this.regione = regione;
    }

    public AnagraficheEstese (Long id, String codice, String cognome, String nome, String ragioneSociale, String sesso, String pIva, String codiceFiscale, String regione, String provincia, String comune, Date provaData) {
        super(id, codice, cognome, nome, ragioneSociale, sesso);
        this.pIva = pIva;
        this.codiceFiscale = codiceFiscale;
        this.comune = comune;
        this.regione = regione;
        this.provincia = provincia;
        this.provaData = provaData;
    }

    public String getPIva() {
        return pIva;
    }

    public void setPIva(String pIva) {
        this.pIva = pIva;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public Date getProvaData() {
        return provaData;
    }

    public void setProvaData(Date provaData) {
        this.provaData = provaData;
    }

    @Override
    public String toString() {
        return "AnagraficheEstese{" +
                "pIva='" + pIva + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", provincia='" + provincia + '\'' +
                ", comune='" + comune + '\'' +
                ", provaData=" + provaData +
                '}';
    }
}
