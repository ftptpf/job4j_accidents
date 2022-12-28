package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
@org.springframework.stereotype.Service
public class AccidentService implements Service<Accident> {
    private final Store<Accident> store;

    public AccidentService(Store<Accident> store) {
        this.store = store;
    }

    public Collection<Accident> findAll() {
        return store.findAll();
    }

}
