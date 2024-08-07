package com.powerservice.managermag.utilities;

public class ListItem {

    protected String fieldName;
    protected Object fieldValue;
    protected String label;
    protected Boolean isTextbox = true;
    protected Boolean isDateInput = false;
    protected Boolean isDropdownInput = false;
    protected Boolean isNumericInput = false;

    protected String textboxType = "text";
    protected Boolean isUpdating = false;

    public ListItem(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        initField();
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldValue=" + fieldValue +
                ", label='" + label + '\'' +
                ", isUpdating=" + isUpdating +
                '}';
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
        // Ensure label is updated when fieldName changes
        this.label = formatLabel(fieldName);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private String formatLabel(String fieldName) {
        if (fieldName == null || fieldName.isEmpty()) {
            return fieldName;
        }

        StringBuilder formattedName = new StringBuilder();

        formattedName.append(Character.toUpperCase(fieldName.charAt(0)));

        for (int i = 1; i < fieldName.length(); i++) {
            char currentChar = fieldName.charAt(i);
            char prevChar = fieldName.charAt(i - 1);

            if (Character.isUpperCase(currentChar) && !Character.isUpperCase(prevChar)) {
                formattedName.append(' ');
            }
            formattedName.append(currentChar);
        }
        return formattedName.toString();
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Boolean getIsUpdating() {
        return isUpdating;
    }

    public void setIsUpdating(Boolean updating) {
        isUpdating = updating;
    }

    public String getTextboxType() {
        return textboxType;
    }

    public void setTextboxType(String textboxType) {
        this.textboxType = textboxType;
    }

    public Boolean getIsTextbox() {
        return isTextbox;
    }

    public void setIsTextbox(Boolean textbox) {
        isTextbox = textbox;
    }

    public Boolean getIsDateInput() {
        return isDateInput;
    }

    public void setIsDateInput(Boolean dateInput) {
        isDateInput = dateInput;
    }

    public Boolean getIsDropdownInput() {
        return isDropdownInput;
    }

    public void setIsDropdownInput(Boolean dropdownInput) {
        isDropdownInput = dropdownInput;
    }

    public Boolean getIsNumericInput() {
        return isNumericInput;
    }

    public void setIsNumericInput(Boolean numericInput) {
        isNumericInput = numericInput;
    }

    private void initField() {
        this.label = formatLabel(this.fieldName);
        setInputType();
    }

    public void setInputType() {
    }
}
