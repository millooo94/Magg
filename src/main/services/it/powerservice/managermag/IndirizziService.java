package it.powerservice.managermag;

import com.powerservice.managermag.anagrafiche.events.IndirizzoSavedEvent;
import it.powerservice.managermag.geography.Nazioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizziService {
    @Autowired
    IndirizziRepository indirizziRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public List<Indirizzi> getIndirizzi() {
        return indirizziRepository.getIndirizzi();
    }

    public Indirizzi getIndirizziFromId(long id) {
        return indirizziRepository.getIndirizziFromId(id);
    }

    public List<Indirizzi> getIndirizziFromIdAnagrafica(long idAnagrafica) {
        return indirizziRepository.getIndirizziFromIdAnagrafica(idAnagrafica);
    }

    public Indirizzi getIndirizzoSedePrincipaleFromIdAnagrafica(long idAnagrafica) {
        return indirizziRepository.getIndirizzoSedePrincipaleFromIdAnagrafica(idAnagrafica);
    }

    public void saveIndirizzo(Indirizzi indirizzo) {
        indirizziRepository.save(indirizzo);
        applicationEventPublisher.publishEvent(new IndirizzoSavedEvent(indirizzo));
    }
}
