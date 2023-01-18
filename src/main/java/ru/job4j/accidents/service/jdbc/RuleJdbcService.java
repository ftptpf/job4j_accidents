package ru.job4j.accidents.service.jdbc;

import lombok.AllArgsConstructor;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.Store;
import ru.job4j.accidents.service.CrudService;

import java.util.Collection;
import java.util.Optional;

/*@Primary
@Service*/
@AllArgsConstructor
public class RuleJdbcService implements CrudService<Rule, String[]> {
    private final Store<Rule> store;
    public Optional<Rule> create(Rule rule, String[] strings) {
        return Optional.empty();
    }

    public Collection<Rule> findAll() {
        return null;
    }

    public Optional<Rule> findById(int id) {
        return Optional.empty();
    }

    public boolean update(Rule rule, String[] strings) {
        return false;
    }

    public boolean remove(int id) {
        return false;
    }

    public void removeAll() {

    }
}
