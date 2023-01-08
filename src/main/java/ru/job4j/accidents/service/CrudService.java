package ru.job4j.accidents.service;

import java.util.Collection;
import java.util.Optional;

public interface CrudService<T, E> {
    public Optional<T> create(T t, E e);
    public Collection<T> findAll();
    public Optional<T> findById(int id);
    public boolean update(T t, E e);
    public boolean remove(int id);
    public void removeAll();
}
