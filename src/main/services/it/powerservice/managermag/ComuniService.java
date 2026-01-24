package it.powerservice.managermag;

import it.powerservice.managermag.geography.Cap;
import it.powerservice.managermag.geography.Comuni;
import it.powerservice.managermag.geography.Province;
import it.powerservice.managermag.geography.Regioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComuniService {
    @Autowired
    ComuniRepository comuniRepository;
    public List<Comuni> getComuni() {
        return  comuniRepository.getComuni();
    }
    public List<Regioni> getRegioni() {
        return comuniRepository.getRegioni();
    }
    public List<Province> getProvinceFromRegione(String regione) {
        return comuniRepository.getProvinceFromRegione(regione);
    }
    public List<Comuni> getComuniFromProvincia(String provincia) {
        return comuniRepository.getComuniFromProvincia(provincia);
    }
    public List<Cap> getCapFromComune(String comune) {
        return comuniRepository.getCapFromComune(comune);
    }

}
