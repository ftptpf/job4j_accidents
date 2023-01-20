package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentService implements CrudService<Accident, String[]> {
    private final Store<Accident> storeAccident;
    private final Store<Type> storeType;
    private final Store<Rule> storeRule;

    public Optional<Accident> create(Accident accident, String[] rulesId) {
        Optional<Type> typeOptional = storeType.findById(accident.getType().getId());
        if (typeOptional.isEmpty()) {
            return Optional.empty();
        }
        accident.setType(typeOptional.get());

        Set<Rule> ruleSet = getRulesSet(rulesId);
        if (ruleSet.isEmpty()) {
            return Optional.empty();
        }
        accident.setRules(ruleSet);
        return storeAccident.create(accident);
    }

    public Collection<Accident> findAll() {
        return storeAccident.findAll();
    }

    public Optional<Accident> findById(int id) {
        return storeAccident.findById(id);
    }

    public boolean update(Accident accident, String[] rulesId) {
        Optional<Type> typeOptional = storeType.findById(accident.getType().getId());
        if (typeOptional.isEmpty()) {
            return false;
        }
        accident.setType(typeOptional.get());

        Set<Rule> ruleSet = getRulesSet(rulesId);
        if (ruleSet.isEmpty()) {
            return false;
        }
        accident.setRules(ruleSet);
        return storeAccident.update(accident);
    }

    public boolean remove(int id) {
        return storeAccident.remove(id);
    }

    public void removeAll() {
        storeAccident.removeAll();
    }

    /**
     * По значению id находим в базе rule и возвращаем сформированный set.
     * Если даже один из массива id не находим в базе - возвращаем пустой set.
     * @param rulesId массив id rules
     * @return set rules
     */
    public Set<Rule> getRulesSet(String[] rulesId) {
        Set<Rule> rsl = new HashSet<>();
        for (String idStr : rulesId) {
            int id = Integer.parseInt(idStr);
            Optional<Rule> ruleOptional = storeRule.findById(id);
            if (ruleOptional.isEmpty()) {
                return new HashSet<>();
            }
            rsl.add(ruleOptional.get());
        }
        return rsl;
    }

}
