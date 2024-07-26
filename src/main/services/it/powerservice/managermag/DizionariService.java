package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DizionariService {
    @Autowired
    DizionariRepository dizionariRepository;
    public List<Dizionari> getDizionari() {
        return dizionariRepository.getDizionari();
    }

    public void updateDescrizioneDizionario(String codice, String newDescrizione) {
        dizionariRepository.updateDescrizioneDizionario(codice, newDescrizione);
    }
}
