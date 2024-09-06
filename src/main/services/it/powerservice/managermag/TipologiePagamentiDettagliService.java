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
    public List<TipologiePagamentiDettagli> findTipologiePagamentiDettagliFromTipologiePagamenti(Long idTipologiePagamento) {
      return tipologiePagamentiDettagliRepository.findTipologiePagamentiDettagliFromIdTipologiePagamenti(idTipologiePagamento);
    }
    public void onSaveTipologiaPagamentoDettagli(TipologiePagamentiDettagli tipologiePagamentiDettagli) {
        tipologiePagamentiDettagliRepository.save(tipologiePagamentiDettagli);
    }
    public void deleteTipologiaPagamentoDettaglio(Long tipologiaPagamentoDettaglioId) {
        tipologiePagamentiDettagliRepository.deleteById(tipologiaPagamentoDettaglioId);
    }
}
