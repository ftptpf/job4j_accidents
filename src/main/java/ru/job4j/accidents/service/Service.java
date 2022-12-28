package ru.job4j.accidents.service;

import java.util.Collection;

public interface Service<T> {
    public Collection<T> findAll();
}
