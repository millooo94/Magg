package it.powerservice.managermag;

import it.powerservice.managermag.customClass.AnagraficheEstese;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarcheController {

    @Autowired
    MarcheService marcheService;

    @GetMapping("/marche")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<Marche> getMarche() {
        return marcheService.getMarche();
    }
}
