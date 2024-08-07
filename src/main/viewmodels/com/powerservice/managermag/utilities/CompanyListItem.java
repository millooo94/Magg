package com.powerservice.managermag.utilities;

import java.util.ArrayList;
import java.util.List;

public class CompanyListItem extends ListItem {

    private List<HttpType> dropdownItems = new ArrayList<>();

    public CompanyListItem(String fieldName, Object fieldValue) {
        super(fieldName, fieldValue);
        initDropdownItems();
    }


    @Override
    public void setInputType() {
        if (!fieldName.equals("CapSociale") && !fieldName.equals("dataAutorizzazione") && !fieldName.equals("TipoHttp")) {
            switch (fieldName) {
                case "Email":
                case "pecAziendale":
                    textboxType = "email";
                    break;
                case "Sito":
                    textboxType = "url";
                    break;
                case "telefono":
                    textboxType = "tel";
                    break;
                case "passwordLicenza":
                    textboxType = "password";
                    break;
                default:
                    textboxType = "text";
                    break;
            }
        } else {
            isTextbox = false;
            switch (fieldName) {
                case "CapSociale":
                    isNumericInput = true;
                    break;
                case "TipoHttp":
                    isDropdownInput = true;
                    break;
                case "dataAutorizzazione":
                    isDateInput = true;
                    break;
            }
        }
    }

    public void initDropdownItems() {
        if (isDropdownInput && fieldName.equals("TipoHttp")) {
            dropdownItems.add(new HttpType("Http", 1));
            dropdownItems.add((new HttpType("Https", 2)));
        }
    }

    public List<HttpType> getDropdownItems() {
        return dropdownItems;
    }

    public void setDropdownItems(List<HttpType> dropdownItems) {
        this.dropdownItems = dropdownItems;
    }

    @Override
    public String toString() {
        return "CompanyListItem{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldValue=" + fieldValue +
                ", label='" + label + '\'' +
                ", isTextbox=" + isTextbox +
                ", isDateInput=" + isDateInput +
                ", isDropdownInput=" + isDropdownInput +
                ", isNumericInput=" + isNumericInput +
                ", textboxType='" + textboxType + '\'' +
                ", isUpdating=" + isUpdating +
                '}';
    }
}
