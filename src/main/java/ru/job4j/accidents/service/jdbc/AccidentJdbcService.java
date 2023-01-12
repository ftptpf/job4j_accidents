package ru.job4j.accidents.service.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.Store;
import ru.job4j.accidents.service.CrudService;

import java.util.Collection;
import java.util.Optional;

@Primary
@Service
@AllArgsConstructor
public class AccidentJdbcService implements CrudService<Accident, String[]> {
    private final Store<Accident> store;

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


