package it.powerservice.managermag;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    String redirectWithUsingForwardPrefix() {
        return "forward:/index.zul";
    }
}