package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnagraficheService {
    @Autowired
    AnagraficheRepository anagraficheRepository;

    public List<Anagrafiche> getAnagrafiche() {
        return anagraficheRepository.getAnagrafiche();
    }
}
