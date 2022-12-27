package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
@Service
public class AccidentService {
    private final Store store;

    public AccidentService(Store store) {
        this.store = store;
    }

    public Collection<Accident> findAll() {
        return store.findAll();
    }

}
