package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.Optional;

@Primary
@Repository
@AllArgsConstructor
public class TypeJdbcStore implements Store<Type> {
    private final JdbcTemplate jdbc;
    public Optional<Type> create(Type type) {
        return Optional.empty();
    }

    public Collection<Type> findAll() {
        return null;
    }

    public Optional<Type> findById(int id) {
        return Optional.empty();
    }

    public boolean update(Type type) {
        return false;
    }

    public boolean remove(int id) {
        return false;
    }

    public void removeAll() {

    }
}
