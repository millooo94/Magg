package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizziService {
    @Autowired
    IndirizziRepository indirizziRepository;

    public List<Indirizzi> getIndirizzi() {
        return indirizziRepository.getIndirizzi();
    }

    public List<Indirizzi> getIndirizziFromIdAnagrafica(long idAnagrafica) {
        return indirizziRepository.getIndirizziFromIdAnagrafica(idAnagrafica);
    }
}
