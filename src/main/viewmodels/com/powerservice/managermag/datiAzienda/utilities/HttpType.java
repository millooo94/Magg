package com.powerservice.managermag.datiAzienda.utilities;

public class HttpType {
    private String label;
    private Integer value;

    public HttpType(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HttpType{" +
                "label='" + label + '\'' +
                ", value=" + value +
                '}';
    }
}
