package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.CrudService;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final CrudService<Accident, AccidentType> service;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", service.findAllTypes());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, Model model) {
        Optional<Accident> accidentOptional = service.create(accident);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Ошибка при сохранении автомобильного инцидента в базе.");
            return "error";
        }
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String updateForm(@RequestParam("id") int id, Model model) {
        Optional<Accident> accidentOptional = service.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Ошибка. Автомобильный инцидент в базе не найден.");
            return "error";
        }
        model.addAttribute("accident", accidentOptional.get());
        model.addAttribute("types", service.findAllTypes());
        return "editAccident";
    }

    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident, Model model) {
        if (!service.update(accident)) {
            model.addAttribute("message", "Ошибка при обновлении информации о автомобильном инциденте.");
            return "error";
        }
        return "redirect:/index";
    }


}
