package ru.job4j.accidents.service;

import java.util.Collection;
import java.util.Optional;

public interface CrudService<T> {
    public Optional<T> create(T t);
    public Collection<T> findAll();
    public Optional<T> findById(int id);
    public boolean update(T t);
    public boolean remove(int id);
    public void removeAll();
}
