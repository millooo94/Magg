package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticoliWebMarketTagConfigurazioniController {
    @Autowired
    ArticoliWebMarketTagConfigurazioniService articoliWebMarketTagConfigurazioniService;

    @GetMapping("/getArticoloWebMarketTagConfigurazioni")
    public void getArticoliWebMarketTagConfigurazioni() {
        var res = articoliWebMarketTagConfigurazioniService.getArticoliWebMarketTagConfigurazioni();
        for (ArticoliWebMarketTagConfigurazioni awmtc: res) {
            System.out.println(awmtc);
        }
    }
}
