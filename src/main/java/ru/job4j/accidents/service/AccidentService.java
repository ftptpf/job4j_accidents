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
    private final Store<Accident> store;
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
        return store.create(accident);
    }

    public Collection<Accident> findAll() {
        return store.findAll();
    }

    public Optional<Accident> findById(int id) {
        return store.findById(id);
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
        return store.update(accident);
    }

    public boolean remove(int id) {
        return store.remove(id);
    }

    public void removeAll() {
        store.removeAll();
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
