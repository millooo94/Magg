package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DizionariCategorieService {
    @Autowired
    DizionariCategorieRepository dizionariCategorieRepository;

    public List<DizionariCategorie> getDizionariCategorie() {
        return  dizionariCategorieRepository.getDizionariCategorie();
    }
}
