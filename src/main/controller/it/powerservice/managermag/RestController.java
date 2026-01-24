package it.powerservice.managermag;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @GetMapping("/rest")
    public String home() {
        return "ma poi torn";
    }

}
