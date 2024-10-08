package com.powerservice.managermag.datiAzienda;

import com.powerservice.managermag.datiAzienda.utilities.DatiAziendaListItem;
import it.powerservice.managermag.DatiAzienda;
import it.powerservice.managermag.DatiAziendaService;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class DatiAziendaIndexlViewModel {
    @WireVariable
    DatiAziendaService datiAziendaService;
    DatiAzienda datiAzienda;

    Boolean isDataUpdating = false;
    List<DatiAziendaListItem> fieldList = new ArrayList<DatiAziendaListItem>();

    @Init
    public void init() throws IllegalAccessException {
        datiAzienda = datiAziendaService.getDatiAzienda().get(0);
        initFields();
    }

    public void initFields() throws IllegalAccessException {
        Class<?> datiAziendaClass = DatiAzienda.class;
        Field[] fields = datiAziendaClass.getDeclaredFields();
        for (Field da: fields) {
            if (!da.getName().equals("id") && !da.getName().equals("isUpdating")) {
                da.setAccessible(true);
                var fieldName = da.getName();
                var fieldValue = da.get(datiAzienda);
                var field = new DatiAziendaListItem(fieldName, fieldValue);
                fieldList.add(field);
            }
        }
        for (DatiAziendaListItem x: fieldList) {
            System.out.println(x);
        }
    }

    @Command
    @NotifyChange({"fieldList", "isDataUpdating"})
    public void onEdit() {
        for (DatiAziendaListItem cli: fieldList) {
            cli.setIsUpdating(true);
        }
        isDataUpdating = true;
    }
    @Command
    @NotifyChange({"fieldList", "isDataUpdating"})
    public void onSave() throws NoSuchFieldException, IllegalAccessException {
        for (DatiAziendaListItem cli: fieldList) {
            Field field = DatiAzienda.class.getDeclaredField(cli.getFieldName());
            field.setAccessible(true);
            System.out.println(cli.getFieldValue().getClass().getName() + " " + cli.getFieldValue());
            field.set(datiAzienda, cli.getFieldValue());
            cli.setIsUpdating(false);
        }
        datiAziendaService.saveDatiAzienda(datiAzienda);
        isDataUpdating = false;
    }


    public List<DatiAziendaListItem> getFieldList() {
        return fieldList;
    }

    public Boolean getIsDataUpdating() {
        return isDataUpdating;
    }
}

