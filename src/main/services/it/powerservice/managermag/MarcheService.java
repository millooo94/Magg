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
}
