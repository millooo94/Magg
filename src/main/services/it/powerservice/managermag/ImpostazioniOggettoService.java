package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpostazioniOggettoService {
    @Autowired
    ImpostazioniOggettoRepository impostazioniOggettoRepository;

    public List<ImpostazioniOggetto> getImpostazioniOggetto() {
        return this.impostazioniOggettoRepository.getImpostazioniOggetto();
    }
}
