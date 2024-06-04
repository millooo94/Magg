package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DizionariController {
    @Autowired
    DizionariService dizionariService;
    @GetMapping("/getDizionari")
    public void getDizionari() {
        var res = dizionariService.getDizionari();
        for (Dizionari d: res) {
            System.out.println(d);
        }
    }
}
