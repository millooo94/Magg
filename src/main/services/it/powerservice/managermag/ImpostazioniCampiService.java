package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpostazioniCampiService {
    @Autowired
    ImpostazioniCampiRepository impostazioniCampiRepository;

    public List<ImpostazioniCampi> getImpostazioniCampi() {
        return this.impostazioniCampiRepository.getImpostazioniCampi();
    }
}
