package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtentiService {
    @Autowired
    UtentiRepository utentiRepository;

    public List<Utenti> getUtenti() {
        return utentiRepository.getUtenti();
    }
}
