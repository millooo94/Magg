package it.powerservice.managermag.keys;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class ListiniRicarichiScontiPKId implements Serializable {
    Long idListino;
    Long idMarca;
    Long IdCategoria;

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
