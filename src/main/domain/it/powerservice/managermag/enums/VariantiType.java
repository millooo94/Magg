package it.powerservice.managermag.enums;

public enum VariantiType {
    CATEGORIA("C"),
    TIPOLOGIA("T"),
    VALORE("V");

    private final String label;

    // Constructor
    VariantiType(String label) {
        this.label = label;
    }

    // Getter for the label
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
