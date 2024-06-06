package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TipologiePagamentiDettagliController {
    @Autowired
    TipologiePagamentiDettagliService tipologiePagamentiDettagliService;

    @GetMapping("getTipologiePagamentiDettagli")
    public void getTipologiePagamentiDettagli() {
        var res = tipologiePagamentiDettagliService.getTipologiePagamentiDettagli();
        for (TipologiePagamentiDettagli tpd: res) {
            System.out.println(tpd);
        }
    }
}
