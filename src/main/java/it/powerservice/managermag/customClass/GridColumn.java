package it.powerservice.managermag.customClass;

public class GridColumn {
    private String label;
    private String value;
    private String width;

    public GridColumn(String label, String value, String width) {
        this.label = label;
        this.value = value;
        this.width = width;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "GridColumn{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
