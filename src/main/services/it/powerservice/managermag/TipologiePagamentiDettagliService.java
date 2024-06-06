package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipologiePagamentiDettagliService {
    @Autowired
    TipologiePagamentiDettagliRepository tipologiePagamentiDettagliRepository;

    public List<TipologiePagamentiDettagli> getTipologiePagamentiDettagli() {
        return tipologiePagamentiDettagliRepository.getTipologiePagamentiDettagli();
    }
}
