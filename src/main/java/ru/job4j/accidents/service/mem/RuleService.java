package ru.job4j.accidents.service.mem;

import lombok.AllArgsConstructor;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.Store;
import ru.job4j.accidents.service.CrudService;

import java.util.Collection;
import java.util.Optional;


@AllArgsConstructor
public class RuleService implements CrudService<Rule, String[]> {
    private final Store<Rule> store;

    public Optional<Rule> create(Rule rule, String[] str) {
        return store.create(rule);
    }

    public Collection<Rule> findAll() {
        return store.findAll();
    }

    public Optional<Rule> findById(int id) {
        return store.findById(id);
    }

    public boolean update(Rule rule, String[] str) {
        return store.update(rule);
    }

    public boolean remove(int id) {
        return store.remove(id);
    }

    public void removeAll() {
        store.removeAll();
    }
}
