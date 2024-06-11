package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticoliAllocazioniConfigurazioniController {
    @Autowired
    ArticoliAllocazioniConfigurazioniService articoliAllocazioniConfigurazioniService;
    @GetMapping("/getArticoliAllocazioniConfigurazioni")
    public void getArticoliAllocazioniConfigurazioni() {
        var res = articoliAllocazioniConfigurazioniService.getArticoliAllocazioniConfigurazioni();
        for (ArticoliAllocazioniConfigurazioni aac: res) {
            System.out.println(aac);
        }
    }
}
