package it.powerservice.managermag;

import it.powerservice.managermag.geography.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {
    @Autowired
    NazioniService nazioniService;
    @Autowired
    ComuniService comuniService;
    @Autowired
    IndirizziService indirizziService;
    @GetMapping("/nazioni")
    public List<Nazioni> getNazioni() {
        return nazioniService.getNazioni();
    }
    @GetMapping("/regioni")
    public List<Regioni> getRegioni() {
        return comuniService.getRegioni();
    }
    @GetMapping("/province/{regione}")
    public List<Province> getProvinceFromRegione(@PathVariable String regione) {
        return comuniService.getProvinceFromRegione(regione);
    }
    @GetMapping("/comuni/{provincia}")
    public List<Comuni> getComuniFromProvincia(@PathVariable String provincia) {
        return comuniService.getComuniFromProvincia(provincia);
    }
    @GetMapping("/cap/{comune}")
    public List<Cap> getCapFromComune(@PathVariable String comune) {
        return comuniService.getCapFromComune(comune);
    }
    @GetMapping("/indirizzo/{idAnagrafica}")
    public Indirizzi getIndirizzoSedePrincipaleFromIdAnagrafica(@PathVariable Long idAnagrafica) {
        return indirizziService.getIndirizzoSedePrincipaleFromIdAnagrafica(idAnagrafica);
    }
    @PostMapping("/indirizzo")
    public void saveIndirizzo(@RequestBody Indirizzi indirizzo) {
        try {
            indirizziService.saveIndirizzo(indirizzo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/indirizzo/{id}")
    public void updateIndirizzo(@PathVariable Long id, @RequestBody Indirizzi indirizzoDetails) {

        System.out.println(indirizzoDetails);

        Indirizzi indirizzo = indirizziService.getIndirizziFromId(id);

        indirizzo.setNazione(indirizzoDetails.getNazione());
        indirizzo.setIndirizzo(indirizzoDetails.getIndirizzo());
        indirizzo.setProvincia(indirizzoDetails.getProvincia());
        indirizzo.setComune(indirizzoDetails.getComune());
        indirizzo.setRegione(indirizzoDetails.getRegione());
        indirizzo.setCap(indirizzoDetails.getCap());

        indirizziService.saveIndirizzo(indirizzo);
    }
    @GetMapping("/idAnagrafica/{id}")
    public ResponseEntity<Long> getElementId(@PathVariable Long id) {
        return ResponseEntity.ok(id);
    }

    @GetMapping("/selectedIndirizzo/{id}")
    public ResponseEntity<Indirizzi> getIndirizzoFromId(@PathVariable Long id) {
        return ResponseEntity.ok(indirizziService.getIndirizziFromId(id));
    }





}
