package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.repository.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentService implements CrudService<Accident, HttpServletRequest> {
    private final Store<Accident> store;
    private final Store<Type> storeType;
    private final Store<Rule> storeRule;

    public Optional<Accident> create(Accident accident, HttpServletRequest req) {
        Optional<Type> typeOptional = storeType.findById(accident.getType().getId());
        if (typeOptional.isEmpty()) {
            return Optional.empty();
        }
        accident.setType(typeOptional.get());
        String[] rules = req.getParameterValues("ruleId");
        Set<Rule> ruleSet = getRulesSet(rules);
        if (ruleSet.isEmpty()) {
            return Optional.empty();
        }
        accident.setRules(ruleSet);
        return store.create(accident);
    }

    public Collection<Accident> findAll() {
        return store.findAll();
    }

    public Optional<Accident> findById(int id) {
        return store.findById(id);
    }

    public boolean update(Accident accident, HttpServletRequest req) {
        Optional<Type> typeOptional = storeType.findById(accident.getType().getId());
        if (typeOptional.isEmpty()) {
            return false;
        }
        accident.setType(typeOptional.get());
        String[] rules = req.getParameterValues("ruleId");
        Set<Rule> ruleSet = getRulesSet(rules);
        if (ruleSet.isEmpty()) {
            return false;
        }
        accident.setRules(ruleSet);
        return store.update(accident);
    }

    public boolean remove(int id) {
        return store.remove(id);
    }

    public void removeAll() {
        store.removeAll();
    }

    public Set<Rule> getRulesSet(String[] rules) {
        Set<Rule> rsl = new HashSet<>();
        for (String id : rules) {
            int idRule = Integer.parseInt(id);
            Optional<Rule> ruleOptional = storeRule.findById(idRule);
            ruleOptional.ifPresent(rsl::add);
        }
        return rsl;
    }

}
