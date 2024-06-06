package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticoliService {

    @Autowired
    ArticoliRepository articoliRepository;

    public List<Articoli> getArticoli() {
        return articoliRepository.getArticoli();
    }
}
