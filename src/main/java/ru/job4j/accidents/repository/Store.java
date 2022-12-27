package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;

public interface Store {
    public Collection<Accident> findAll();
}
