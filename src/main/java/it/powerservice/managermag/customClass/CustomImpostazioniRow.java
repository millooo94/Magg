package it.powerservice.managermag.customClass;

public class CustomImpostazioniRow {
    String codice;
    String etichettaCampo;
    String Categoria;
    String aspettoCampo;
    String valorestringa;
    String valoreclob;
    String valorenumero;

    public CustomImpostazioniRow(String codice, String etichettaCampo, String categoria, String aspettoCampo, String valorestringa, String valoreclob, String valorenumero) {
        this.codice = codice;
        this.etichettaCampo = etichettaCampo;
        Categoria = categoria;
        this.aspettoCampo = aspettoCampo;
        this.valorestringa = valorestringa;
        this.valoreclob = valoreclob;
        this.valorenumero = valorenumero;
    }
}
