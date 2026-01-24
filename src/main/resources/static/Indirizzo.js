export default class Indirizzo {
  id;
  idAnagrafica;
  idDocumento;
  tipoIndirizzo;
  denominazioneAlternativa;
  nazione;
  indirizzo;
  provincia;
  comune;
  frazione;
  regione;
  cap;
  codSdi;
  telefono1;
  telefono2;
  cellulare;
  telefono3;
  fax;
  email;
  pec;
  sito;
  referente;
  recapitoReferente;
  notes;

  constructor(
    idAnagrafica,
    tipoIndirizzo;
    indirizzo,
    nazione,
    regione,
    provincia,
    comune,
    cap
  ) {
    this.idAnagrafica = idAnagrafica;
    this.tipoIndirizzo = tipoIndirizzo;
    this.indirizzo = indirizzo;
    this.nazione = nazione;
    this.regione = regione;
    this.provincia = provincia;
    this.comune = comune;
    this.cap = cap;
  }
}
