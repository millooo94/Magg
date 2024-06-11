package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnagraficheInfoTrasportatoriService {
    @Autowired
    AnagraficheInfoTrasportatoriRepository anagraficheInfoTrasportatoriRepository;

    public List<AnagraficheInfoTrasportatori> getAnagraficheInfoTrasportatori() {
        return anagraficheInfoTrasportatoriRepository.getAanagraficheInfoTrasportatori();
    }
}
