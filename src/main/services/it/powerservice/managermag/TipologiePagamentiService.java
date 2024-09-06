package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipologiePagamentiService {
    @Autowired
    TipologiePagamentiRepository tipologiePagamentiRepository;

    public List<TipologiePagamenti> getTipologiePagamenti() {
        return tipologiePagamentiRepository.getTipologiePagamenti();
    }
    public void onSaveTipologiaPagamento(TipologiePagamenti tipologiePagamento) {
        tipologiePagamentiRepository.save(tipologiePagamento);
    }

}
