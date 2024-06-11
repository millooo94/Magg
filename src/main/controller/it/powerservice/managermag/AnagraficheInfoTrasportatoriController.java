package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AnagraficheInfoTrasportatoriController {
    @Autowired
    AnagraficheInfoTrasportatoriService anagraficheInfoTrasportatoriService;

    @GetMapping("/getAnagraficheInfoTrasportatori")
    public void getAnagraficheInfoTrasportatori() {
        var res = anagraficheInfoTrasportatoriService.getAnagraficheInfoTrasportatori();
        for (AnagraficheInfoTrasportatori ait: res) {
            System.out.println(ait);
        }
    }
}
