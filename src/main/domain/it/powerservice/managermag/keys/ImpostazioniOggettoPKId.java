package it.powerservice.managermag.keys;

import java.io.Serializable;

public class ImpostazioniOggettoPKId implements Serializable {
    String CodiceImpostazione;
    Long idOggetto;
    String tipoOggetto;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
