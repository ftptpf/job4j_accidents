package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService implements CrudService<Accident> {
    private final Store<Accident> store;
    private final Store<Type> storeType;

    public Optional<Accident> create(Accident accident) {
        Optional<Type> typeOptional = storeType.findById(accident.getType().getId());
        if (typeOptional.isEmpty()) {
            return Optional.empty();
        }
        accident.setType(typeOptional.get());
        return store.create(accident);
    }

    public Collection<Accident> findAll() {
        return store.findAll();
    }

    public Optional<Accident> findById(int id) {
        return store.findById(id);
    }

    public boolean update(Accident accident) {
        Optional<Type> typeOptional = storeType.findById(accident.getType().getId());
        if (typeOptional.isEmpty()) {
            return false;
        }
        accident.setType(typeOptional.get());
        return store.update(accident);
    }

    public boolean remove(int id) {
        return store.remove(id);
    }

    public void removeAll() {
        store.removeAll();
    }

}
