package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticoliConfigurazioniController {
    @Autowired
    ArticoliConfigurazioniService articoliConfigurazioniService;
    @GetMapping("/getArticoliConfigurazioni")
    public void getArticoliConfigurazioni() {
        var res = articoliConfigurazioniService.getArticoliConfigurazioni();
        for (ArticoliConfigurazioni ac: res) {
            System.out.println(ac);
        }
    }
}
