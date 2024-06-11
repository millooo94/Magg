package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantiRifService {
    @Autowired
    VariantiRifRepository variantiRifRepository;
    public List<VariantiRif> getVariantiRif() {
       return variantiRifRepository.getVariantiRif();
    }
}
