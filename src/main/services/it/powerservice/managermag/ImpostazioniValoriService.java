package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImpostazioniValoriService {
    @Autowired
    ImpostazioniValoriRepository impostazioniValoriRepository;

    public List<ImpostazioniValori> getImpostazioniValori() {
        return impostazioniValoriRepository.getImpostazioniValori();
    }

    public List<ImpostazioniValori> getImpostazioniValori(String codiceImpostazione) {
        return impostazioniValoriRepository.getImpostazioniValori(codiceImpostazione);
    }
}
