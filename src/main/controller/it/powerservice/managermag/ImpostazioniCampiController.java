package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RestController
@RequestMapping("/api")
public class ImpostazioniCampiController {

    @Autowired
    ImpostazioniGridService impostazioniCampiService;

    /*@GetMapping(value = "/getImpostazioniCampi")
    public void getImpostazioniCampi() throws SQLException {
        var res = this.impostazioniCampiService.getImpostazioniCampi();
        for (ImpostazioniCampi ic: res) {
            System.out.println(ic);
            System.out.println("Vaffanculooooooooooooooo");
        }
    }*/
}
