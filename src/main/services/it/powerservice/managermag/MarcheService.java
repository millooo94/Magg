package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcheService {

    @Autowired
    MarcheRepository marcheRepository;
    public List<Marche> getMarche() {
        return marcheRepository.getMarche();
    }
    public void saveMarca(Marche marca) { marcheRepository.save(marca);}
    public  void deleteMarca(Long marcaId) {marcheRepository.deleteById(marcaId);}
}
