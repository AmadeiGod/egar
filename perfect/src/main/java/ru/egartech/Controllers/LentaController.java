package ru.egartech.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LentaController {
    @Operation(summary = "Главная страница")
    @GetMapping("/lenta")
    public String lenta(){
        return "main/lenta";
    }
}
