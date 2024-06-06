package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantiService {
    @Autowired
    VariantiRepository variantiRepository;
    public List<Varianti> getVarianti() {
        return variantiRepository.getVarianti();
    }
}
