package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticoliCategorieService {
    @Autowired
    ArticoliCategorieRepository articoliCategorieRepository;

    public List<ArticoliCategorie> getArticoliCategorie() {
        return articoliCategorieRepository.getArticoliCategorie();
    }
}
