package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BancheController {
    @Autowired
    BancheService bancheService;

    @GetMapping("getBanche")
    public void getBanche() {
        var res = bancheService.getBanche();
        for (Banche b: res) {
            System.out.println(b);
        }
    }
}
