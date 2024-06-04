package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DizionariIvaService {
    @Autowired
    DizionariIvaRepository dizionariIvaRepository;

    List<DizionariIva> getDizionariIva() {
        return dizionariIvaRepository.getDizionariIva();
    }
}
