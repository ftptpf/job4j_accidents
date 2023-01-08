package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RuleService implements CrudService<Rule, HttpServletRequest> {
    private Store<Rule> store;

    public Optional<Rule> create(Rule rule, HttpServletRequest req) {
        return store.create(rule);
    }

    public Collection<Rule> findAll() {
        return store.findAll();
    }

    public Optional<Rule> findById(int id) {
        return store.findById(id);
    }

    public boolean update(Rule rule, HttpServletRequest req) {
        return store.update(rule);
    }

    public boolean remove(int id) {
        return store.remove(id);
    }

    public void removeAll() {
        store.removeAll();
    }
}
