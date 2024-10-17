package it.powerservice.managermag.geography;

public class Cap {
    private String codice;

    public Cap(String codice) {
        this.codice = codice;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    @Override
    public String toString() {
        return "Cap{" +
                "codice='" + codice + '\'' +
                '}';
    }
}
