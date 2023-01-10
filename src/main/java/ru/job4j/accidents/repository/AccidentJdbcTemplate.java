package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements Store<Accident> {
    private final JdbcTemplate jdbc;

    public Optional<Accident> create(Accident accident) {
        return Optional.empty();
    }

    public Collection<Accident> findAll() {
        return null;
    }

    public Optional<Accident> findById(int id) {
        return Optional.empty();
    }

    public boolean update(Accident accident) {
        return false;
    }

    public boolean remove(int id) {
        return false;
    }

    public void removeAll() {

    }
}
