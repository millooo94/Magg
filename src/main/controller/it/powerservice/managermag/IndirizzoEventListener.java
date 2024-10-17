package it.powerservice.managermag;

import com.powerservice.managermag.anagrafiche.AnagraficheMonoViewModel;
import com.powerservice.managermag.anagrafiche.events.IndirizzoSavedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.event.EventQueues;

@Component
public class IndirizzoEventListener {

    @EventListener
    public void handleIndirizzoSavedEvent(IndirizzoSavedEvent event) {
        System.out.println("Evento IndirizzoSavedEvent ricevuto.");

        EventQueues.lookup("indirizzoQueue", EventQueues.APPLICATION, true)
                .publish(new org.zkoss.zk.ui.event.Event("indirizzoSaved"));
    }
}
