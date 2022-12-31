package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.CrudService;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final CrudService<Accident> service;

    @GetMapping("/createAccident")
    public String viewCreateAccident() {
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        service.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String updateForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", service.findById(id).get());
        return "editAccident";
    }

    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident) {
        service.update(accident);
        return "redirect:/index";
    }


}
