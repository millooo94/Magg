package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticoliCategorieController {
    @Autowired
    ArticoliCategorieService articoliCategorieService;
    @GetMapping("/getArticoliCategorie")
    public void getArticoliCategorie() {
        var res = articoliCategorieService.getArticoliCategorie();
        for (ArticoliCategorie ac: res) {
            System.out.println(ac);
        }
    }
}
