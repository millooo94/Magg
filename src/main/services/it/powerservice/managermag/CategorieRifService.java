package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieRifService {
    @Autowired
    CategorieRifRepository categorieRifRepository;

    public List<CategorieRif> getCategorieRif() {
        return categorieRifRepository.getCategorieRif();
    }

}
