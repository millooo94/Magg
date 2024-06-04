package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ListiniController {
    @Autowired
    ListiniService listiniService;

    @GetMapping("getListini")
    public void getAllListini() {
        var res = listiniService.getListini();
        for (Listini l: res) {
            System.out.println(l);
        }
    }
}
