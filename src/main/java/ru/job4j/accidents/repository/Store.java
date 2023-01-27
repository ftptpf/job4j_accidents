package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface Store<T> extends CrudRepository {
    public Optional<T> create(T t);
    public Collection<T> findAll();
    public Optional<T> findById(int id);
    public boolean update(T t);
    public boolean remove(int id);
    public void removeAll();
}
