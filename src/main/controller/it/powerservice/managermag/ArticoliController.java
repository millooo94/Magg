package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticoliController {
    @Autowired
    ArticoliService articoliService;

    @GetMapping("/getArticoli")
    public void getArticoli() {
        var res = articoliService.getArticoli();
        for (Articoli a: res) {
            System.out.println(a);
        }
    }
}
