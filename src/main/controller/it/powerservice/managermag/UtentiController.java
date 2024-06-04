package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UtentiController {
    @Autowired
    UtentiService utentiService;

    @GetMapping("/getUtenti")
    public void getUtenti() {
        var res = utentiService.getUtenti();
        for (Utenti u: res) {
            System.out.println(u);
        }
    }
}
