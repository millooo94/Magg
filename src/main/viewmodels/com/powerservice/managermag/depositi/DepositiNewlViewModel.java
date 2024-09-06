package com.powerservice.managermag.depositi;

import it.powerservice.managermag.Depositi;
import it.powerservice.managermag.DepositiService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class DepositiNewlViewModel {

    private Window window;
    @WireVariable
    private DepositiService depositiService;
    private Depositi depositoToSave;
    private Boolean buttonDisabled = true;
    private static DepositiIndexViewModel depositiIndexViewModel;
    Map<String, Boolean> validator = new HashMap<>() {{
        put("nome", true);
        put("codice", true);
    }};


    @Init
    public void init() {
        depositoToSave = new Depositi();
    }

        static Window apriPopup(DepositiIndexViewModel parentModel) {
            depositiIndexViewModel = parentModel;
        return (Window) Executions.createComponents(
                "/zul/depositi/depositi.new.zul", null, null);
    }

    @Command
    @NotifyChange({"depositoToSave"})
    public void onSaveDeposito() {
        validate();
        if (depositoToSave.getIsUpdating() && validator.get("codice") && validator.get("nome")) {
            try {
                depositiService.saveDeposito(depositoToSave);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (window != null) {
                    window.detach();
                }
            }
            depositiIndexViewModel.refresh();
            Notification.show("Salvato");
        } else {
            System.out.println("NON VALIDO!");
        }
    }

    @Command
    @NotifyChange({"depositoToSave","buttonDisabled"})
    public void onChangeDeposito() {
        depositoToSave.setIsUpdating(true);
        buttonDisabled = false;
    }

    @Command
    @NotifyChange("depositoToSave")
    public void setWindow(@BindingParam("window") Window window) {
        this.window = window;
    }


    public void validate() {
        boolean isCodiceValid = depositoToSave.getCodice() != null && !depositoToSave.getCodice().isEmpty();
        boolean isNomeValid = depositoToSave.getNome() != null && !depositoToSave.getNome().isEmpty();

        validator.put("codice", isCodiceValid);
        validator.put("nome", isNomeValid);

        BindUtils.postNotifyChange(null, null, this, "validator");
    }

    public Depositi getDepositoToSave() {
        return depositoToSave;
    }

    public Boolean getButtonDisabled() {
        return buttonDisabled;
    }

    public void setButtonDisabled(Boolean buttonDisabled) {
        this.buttonDisabled = buttonDisabled;
    }

    public Map<String, Boolean> getValidator() {
        return validator;
    }
}
