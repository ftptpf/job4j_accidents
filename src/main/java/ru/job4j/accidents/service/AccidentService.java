package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
@Service
public class AccidentService implements CrudService<Accident> {
    private final Store<Accident> store;

    public AccidentService(Store<Accident> store) {
        this.store = store;
    }

    public Accident create(int id, Accident accident) {
        return store.create(id, accident);
    }

    public Collection<Accident> findAll() {
        return store.findAll();
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public Accident update(int id, Accident accident) {
        return store.update(id, accident);
    }

    public Accident remove(int id) {
        return store.remove(id);
    }

    public void removeAll() {
        store.removeAll();
    }

}
