package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatiAziendaService {
    @Autowired
    DatiAziendaRepository datiAziendaRepository;

    public List<DatiAzienda> getDatiAzienda() {
        return datiAziendaRepository.getDatiAzienda();
    }
}
