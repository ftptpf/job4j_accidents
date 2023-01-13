package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.Optional;

@Primary
@Repository
@AllArgsConstructor
public class RuleJdbcStore implements Store<Rule> {
    private final JdbcTemplate jdbc;
    public Optional<Rule> create(Rule rule) {
        return Optional.empty();
    }

    public Collection<Rule> findAll() {
        return null;
    }

    public Optional<Rule> findById(int id) {
        return Optional.empty();
    }

    public boolean update(Rule rule) {
        return false;
    }

    public boolean remove(int id) {
        return false;
    }

    public void removeAll() {

    }
}
