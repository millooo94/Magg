package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticoliAllocazioniConfigurazioniService {
    @Autowired
    ArticoliAllocazioniConfigurazioniRepository articoliAllocazioniConfigurazioniRepository;

    public List<ArticoliAllocazioniConfigurazioni> getArticoliAllocazioniConfigurazioni() {
        return articoliAllocazioniConfigurazioniRepository.getArticoliAllocazioniConfigurazioni();
    }
}
