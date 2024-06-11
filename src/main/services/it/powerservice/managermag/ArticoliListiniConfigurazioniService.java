package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticoliListiniConfigurazioniService {
    @Autowired
    ArticoliListiniConfigurazioniRepository articoliListiniConfigurazioniRepository;
    public List<ArticoliListiniConfigurazioni> getArticoliListiniConfigurazioni() {
        return articoliListiniConfigurazioniRepository.getAllArticoliListiniConfigurazioni();
    }
}
