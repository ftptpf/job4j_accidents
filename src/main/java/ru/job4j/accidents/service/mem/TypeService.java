package ru.job4j.accidents.service.mem;

import lombok.AllArgsConstructor;
import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.repository.Store;
import ru.job4j.accidents.service.CrudService;

import java.util.Collection;
import java.util.Optional;


@AllArgsConstructor
public class TypeService implements CrudService<Type, String[]> {
    private final Store<Type> store;

    public Optional<Type> create(Type type, String[] str) {
        return store.create(type);
    }

    public Collection<Type> findAll() {
        return store.findAll();
    }

    public Optional<Type> findById(int id) {
        return store.findById(id);
    }

    public boolean update(Type type, String[] str) {
        return store.update(type);
    }

    public boolean remove(int id) {
        return store.remove(id);
    }

    public void removeAll() {
        store.removeAll();
    }
}
