package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.Optional;

@org.springframework.stereotype.Service
public class AccidentService implements Service<Accident> {
    private final Store<Accident> store;

    public AccidentService(Store<Accident> store) {
        this.store = store;
    }

    public Optional<Accident> create(Accident accident) {
        return store.create(accident);
    }

    public Collection<Accident> findAll() {
        return store.findAll();
    }

    public Optional<Accident> findById(int id) {
        return store.findById(id);
    }

    public boolean update(Accident accident) {
        return store.update(accident);
    }

    public boolean remove(int id) {
        return store.remove(id);
    }

    public void removeAll() {
        store.removeAll();
    }

}
