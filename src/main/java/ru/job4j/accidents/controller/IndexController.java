package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.CrudService;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class IndexController {
    private final CrudService<Accident, HttpServletRequest> serviceAccident;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", serviceAccident.findAll());
        return "index";
    }

}
