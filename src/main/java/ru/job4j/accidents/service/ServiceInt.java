package ru.job4j.accidents.service;

import java.util.Collection;

public interface ServiceInt<T> {
    public Collection<T> findAll();
}
