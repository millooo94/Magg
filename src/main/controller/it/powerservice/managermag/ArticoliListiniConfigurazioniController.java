package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticoliListiniConfigurazioniController {
    @Autowired
    ArticoliListiniConfigurazioniService articoliListiniConfigurazioniService;

    @GetMapping("/getArticoliListiniConfigurazioni")
    public void getArticoliListiniConfigurazioni() {
        var res = articoliListiniConfigurazioniService.getArticoliListiniConfigurazioni();
        for (ArticoliListiniConfigurazioni alc: res) {
            System.out.println(alc);
        }
    }
}
