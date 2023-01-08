package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.repository.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeService implements CrudService<Type, HttpServletRequest> {
    private final Store<Type> store;

    public Optional<Type> create(Type type, HttpServletRequest req) {
        return store.create(type);
    }

    public Collection<Type> findAll() {
        return store.findAll();
    }

    public Optional<Type> findById(int id) {
        return store.findById(id);
    }

    public boolean update(Type type, HttpServletRequest req) {
        return store.update(type);
    }

    public boolean remove(int id) {
        return store.remove(id);
    }

    public void removeAll() {
        store.removeAll();
    }
}
