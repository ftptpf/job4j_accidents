package ru.job4j.accidents.repository;

import java.util.Collection;

public interface Store<T> {
    public Collection<T> findAll();
}
