package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AnagraficheController {
    @Autowired
    AnagraficheService anagraficheService;

    @GetMapping("/getAnagrafiche")
    public void getAnagrafiche() {
        var res = anagraficheService.getAnagrafiche();
        for (Anagrafiche a: res) {
            System.out.println(a);
        }
    }
}
