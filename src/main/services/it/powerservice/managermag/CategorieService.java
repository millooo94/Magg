package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    @Autowired
    CategorieRepository categorieRepository;

    public List<Categorie> getCategorie() {
        return categorieRepository.getCategorie();
    }
}
