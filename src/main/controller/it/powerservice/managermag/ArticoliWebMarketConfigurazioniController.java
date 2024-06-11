package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticoliWebMarketConfigurazioniController {
    @Autowired
    ArticoliWebMarketConfigurazioniService articoliWebMarketConfigurazioniService;

    @GetMapping("/getArticoliWebMarketConfigurazioni")
    public void getArticoliWebMarketConfigurazioni() {
        var res = articoliWebMarketConfigurazioniService.getArticoliWebMarketConfigurazioni();
        for (ArticoliWebMarketConfigurazioni awmc: res) {
            System.out.println(awmc);
        }
    }
}
