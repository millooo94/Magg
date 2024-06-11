package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticoliWebMarketConfigurazioniService {
    @Autowired
    ArticoliWebMarketConfigurazioniRepository articoliWebMarketConfigurazioniRepository;

    public List<ArticoliWebMarketConfigurazioni> getArticoliWebMarketConfigurazioni() {
        return articoliWebMarketConfigurazioniRepository.getArticoliWebMarketConfigurazioni();
    }
}
