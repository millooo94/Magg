package com.powerservice.managermag.depositi;

import com.powerservice.managermag.depositi.utilities.DepositiModalCloseListener;
import it.powerservice.managermag.Depositi;
import it.powerservice.managermag.DepositiService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class DepositiIndexViewModel {

    @WireVariable
    DepositiService depositiService;
    List<Depositi> depositi = new ArrayList<>();
    Boolean showDataNonInUso = false;
    Boolean saveButtonDisabled = true;
    Boolean newButtonDisabled = false;
    Map<String, Boolean> validator = new HashMap<>() {{
        put("nome", true);
        put("codice", true);
    }};

    @Init
    public void init() {
        depositi = depositiService.getDepositi();
        for (Depositi d: depositi) {
            System.out.println(d);
        }
    }

    @Command
    @NotifyChange({"showDataNonInUso"})
    public void onChangeVisibility() {
        showDataNonInUso = !showDataNonInUso;
    }

    @Command
    @NotifyChange({"saveButtonDisabled"})
    public void onChangeDeposito(@BindingParam("deposito") Depositi deposito) {
        deposito.setIsUpdating(true);
        saveButtonDisabled = false;
    }

    @Command
    @NotifyChange({"depositi", "saveButtonDisabled"})
    public void onSaveDepositi() {
        for(Depositi deposito: depositi) {
            System.out.println(deposito);
            validate(deposito);
            if (deposito.getIsUpdating() && validator.get("codice") && validator.get("nome")) {
                deposito.setIsUpdating(false);
                depositiService.saveDeposito(deposito);
                saveButtonDisabled = true;
                init();
                Notification.show("Salvati");
            }
        }
    }

    @Command
    @NotifyChange({"newButtonDisabled"})
    public void onCreateDeposito() {
        DepositiNewlViewModel.apriPopup(this).addEventListener(Events.ON_CLOSE, new DepositiModalCloseListener(this));
        newButtonDisabled = true;
    }


    public void initVisibility(Depositi deposito) {
        if (deposito.getDataNonInUso() != null) {
            LocalDate localDateNonInUso = deposito.getDataNonInUso().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            if (localDateNonInUso.isBefore(LocalDate.now())) {
                deposito.setVisibile(false);
            }
        }
        BindUtils.postNotifyChange(null, null, this, "depositi");
    }

    public  void refresh() {
        depositi = depositiService.getDepositi();
        newButtonDisabled = false;
        BindUtils.postNotifyChange(null, null, this, "depositi");
        BindUtils.postNotifyChange(null, null, this, "newButtonDisabled");
    }

    public void validate(Depositi deposito) {
        boolean isCodiceValid = deposito.getCodice() != null && !deposito.getCodice().isEmpty();
        boolean isNomeValid = deposito.getNome() != null && !deposito.getNome().isEmpty();

        validator.put("codice", isCodiceValid);
        validator.put("nome", isNomeValid);

        BindUtils.postNotifyChange(null, null, this, "validator");
    }

    public String getValidationClass(String field) {
        if (field != null && !field.isEmpty()) {
            return "";
        } else {
            return "invalid";
        }
    }

    public void setNewButtonDisabled(Boolean newButtonDisabled) {
        this.newButtonDisabled = newButtonDisabled;
        BindUtils.postNotifyChange(null, null, this, "newButtonDisabled");
    }


    public List<Depositi> getDepositi() {
        return depositi;
    }

    public Boolean getShowDataNonInUso() {
        return showDataNonInUso;
    }

    public Boolean getSaveButtonDisabled() {
        return saveButtonDisabled;
    }

    public void setSaveButtonDisabled(Boolean saveButtonDisabled) {
        this.saveButtonDisabled = saveButtonDisabled;
    }

    public Boolean getNewButtonDisabled() {
        return newButtonDisabled;
    }

    public Map<String, Boolean> getValidator() {
        return validator;
    }
}
