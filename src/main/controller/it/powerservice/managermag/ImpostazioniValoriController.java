package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ImpostazioniValoriController {

    @Autowired
    ImpostazioniValoriService impostazioniValoriService;

    @GetMapping("getImpostazioniValori")
    public void getImpostazioniValori() {
        var res = this.impostazioniValoriService.getImpostazioniValori();
        for (ImpostazioniValori iv: res) {
            System.out.println(iv);
        }
    }
}
