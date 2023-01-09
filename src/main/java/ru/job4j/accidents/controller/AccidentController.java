package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.service.CrudService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final CrudService<Accident, String[]> serviceAccident;
    private final CrudService<Type, String[]> serviceType;
    private final CrudService<Rule, String[]> serviceRule;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", serviceType.findAll());
        model.addAttribute("rules", serviceRule.findAll());

        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, Model model, HttpServletRequest req) {
        String[] rulesId = req.getParameterValues("ruleId");
        Optional<Accident> accidentOptional = serviceAccident.create(accident, rulesId);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Ошибка при сохранении автомобильного инцидента в базе.");
            return "error";
        }
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String updateForm(@RequestParam("id") int id, Model model) {
        Optional<Accident> accidentOptional = serviceAccident.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Ошибка. Автомобильный инцидент в базе не найден.");
            return "error";
        }
        model.addAttribute("accident", accidentOptional.get());
        model.addAttribute("types", serviceType.findAll());
        model.addAttribute("rules", serviceRule.findAll());
        return "editAccident";
    }

    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident, Model model, HttpServletRequest req) {
        String[] rulesId = req.getParameterValues("ruleId");
        if (!serviceAccident.update(accident, rulesId)) {
            model.addAttribute("message", "Ошибка при обновлении информации об автомобильном инциденте.");
            return "error";
        }
        return "redirect:/index";
    }

}
