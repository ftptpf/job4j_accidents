package ru.job4j.accidents.repository;

import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public interface Store<T, ID> extends Repository<T, ID> {
    public Optional<T> create(T t);
    public Collection<T> findAll();
    public Optional<T> findById(ID id);
    public boolean update(T t);
    public boolean remove(ID id);
    public void removeAll();
}
