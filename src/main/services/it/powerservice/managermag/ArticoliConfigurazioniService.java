package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticoliConfigurazioniService {
    @Autowired
    ArticoliConfigurazioniRepository articoliConfigurazioniRepository;
    public List<ArticoliConfigurazioni> getArticoliConfigurazioni() {
        return articoliConfigurazioniRepository.getArticoliConfigurazioni();
    }
}
