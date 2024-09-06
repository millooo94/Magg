package it.powerservice.managermag;

import it.powerservice.managermag.customClass.CodDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnagraficheService {
    @Autowired
    AnagraficheRepository anagraficheRepository;

    public List<Anagrafiche> getAnagrafiche(CodDesc codDesc) {
        return anagraficheRepository.getAnagrafiche();
    }

    public List<Anagrafiche> getAnagraficheFromTipo(String tipo, String tipoCheck) {
        return anagraficheRepository.getAnagraficheFromTipo(tipo, tipoCheck);
    }
    public void saveAnagrafica(Anagrafiche anagrafica) {
        anagraficheRepository.save(anagrafica);
    }
    public void deleteAnagraficaById(Long id) {
        anagraficheRepository.deleteById(id);
    }
}
