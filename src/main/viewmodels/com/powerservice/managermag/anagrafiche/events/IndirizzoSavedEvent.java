package com.powerservice.managermag.anagrafiche.events;

import it.powerservice.managermag.Indirizzi;

public class IndirizzoSavedEvent {
    private final Indirizzi indirizzo;

    public IndirizzoSavedEvent(Indirizzi indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Indirizzi getIndirizzo() {
        return indirizzo;
    }
}
