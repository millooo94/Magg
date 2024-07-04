package it.powerservice.managermag.enums;

public enum TipoImpostazioni {
    SENZANOME("SENZA NOME"),
    MODELLIAGGIUNTIVI("MODELLI AGGIUNTIVI"),
    ALTREIMPOSTAZIONI("ALTRE IMPOSTAZIONI");

    private final String value;

    TipoImpostazioni(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
