package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DizionariCategorieController {
    @Autowired
    DizionariCategorieService dizionariCategorieService;

    @GetMapping("/getDizionariCategorie")
    public void getDizionariCategorie() {
        var res = dizionariCategorieService.getDizionariCategorie();
        for (DizionariCategorie dc: res) {
            System.out.println(dc);
        }
    }
}
