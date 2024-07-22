package ru.egartech.Controllers.Rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LentaController {
    @GetMapping("/lenta")
    public String lenta(){
        return "main/lenta";
    }
}
