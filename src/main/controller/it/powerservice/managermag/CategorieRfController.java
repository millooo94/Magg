package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategorieRfController {
    @Autowired
    CategorieRifService categorieRifService;

    @GetMapping("/getCategorieRif")
    public void getCategorieRif() {
        var res = categorieRifService.getCategorieRif();
        for (CategorieRif cr: res) {
            System.out.println(cr);
        }
    }
}
