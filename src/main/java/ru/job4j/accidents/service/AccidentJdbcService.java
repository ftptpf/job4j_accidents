package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentJdbcService implements CrudService<Accident, String[]> {
    private final AccidentJdbcTemplate store;

    public Optional<Accident> create(Accident accident, String[] strings) {
        return Optional.empty();
    }

    public Collection<Accident> findAll() {
        return null;
    }

    public Optional<Accident> findById(int id) {
        return Optional.empty();
    }

    public boolean update(Accident accident, String[] strings) {
        return false;
    }

    public boolean remove(int id) {
        return false;
    }

    public void removeAll() {

    }
}
