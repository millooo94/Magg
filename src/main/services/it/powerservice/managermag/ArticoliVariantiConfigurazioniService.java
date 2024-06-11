package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticoliVariantiConfigurazioniService {
    @Autowired
    ArticoliVariantiConfigurazioniRepository articoliVariantiConfigurazioniRepository;
    public List<ArticoliVariantiConfigurazioni> getArticoliVariantiConfigurazioni() {
        return articoliVariantiConfigurazioniRepository.getArticoliVariantiConfigurazioni();
    }
}
