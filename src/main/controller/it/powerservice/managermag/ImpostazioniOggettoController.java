package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ImpostazioniOggettoController {

    @Autowired
    ImpostazioniOggettoService impostazioniOggettoService;

    @GetMapping("getImpostazioniOggetto")
    public void getImpostazioniOggetto() {
        var res = this.impostazioniOggettoService.getImpostazioniOggetto();
        for (ImpostazioniOggetto io: res) {
            System.out.println(io);
        }
    }
}
