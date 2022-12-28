package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.ServiceInt;

@Controller
public class IndexController {
    private final ServiceInt<Accident> service;

    public IndexController(ServiceInt<Accident> service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", service.findAll());
        return "index";
    }

}
