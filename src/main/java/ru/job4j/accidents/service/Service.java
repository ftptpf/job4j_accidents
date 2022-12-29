package ru.job4j.accidents.service;

import java.util.Collection;

public interface Service<T> {
    public T create(int id, T t);
    public Collection<T> findAll();
    public T findById(int id);
    public void update(int id, T t);
    public T remove(int id);
    public void removeAll();
}
