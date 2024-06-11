package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticoliWebMarketTagConfigurazioniService {
    @Autowired
    ArticoliWebMarketTagConfigurazioniRepository articoliWebMarketTagConfigurazioniRepository;

    public List<ArticoliWebMarketTagConfigurazioni> getArticoliWebMarketTagConfigurazioni() {
        return articoliWebMarketTagConfigurazioniRepository.getArticoliWebMarketTagConfigurazioni();
    }
}
