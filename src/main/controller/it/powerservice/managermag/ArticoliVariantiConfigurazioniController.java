package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticoliVariantiConfigurazioniController {
    @Autowired
    ArticoliVariantiConfigurazioniService articoliVariantiConfigurazioniService;

    @GetMapping("/getArticoliVariantiConfigurazioni")
    public void getArticoliVariantiConfigurazioni() {
        var res = articoliVariantiConfigurazioniService.getArticoliVariantiConfigurazioni();
        for (ArticoliVariantiConfigurazioni avc: res) {
            System.out.println(res);
        }
    }

}
