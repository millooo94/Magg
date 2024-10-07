package it.powerservice.managermag;

import it.powerservice.managermag.geography.Nazioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NazioniService {
    @Autowired
    NazioniRepository nazioniRepository;
    public List<Nazioni> getNazioni() {
        return  nazioniRepository.findAll();
    }
}
