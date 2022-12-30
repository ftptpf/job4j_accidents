package ru.job4j.accidents.repository;

import java.util.Collection;

public interface Store<T> {
    public T create(int id, T t);
    public Collection<T> findAll();
    public T findById(int id);
    public T update(int id, T t);
    public T remove(int id);
    public void removeAll();
}
