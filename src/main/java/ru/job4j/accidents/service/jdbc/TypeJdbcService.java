package ru.job4j.accidents.service.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.service.CrudService;

import java.util.Collection;
import java.util.Optional;

/*@Primary
@Service*/
@AllArgsConstructor
public class TypeJdbcService implements CrudService<Type, String[]> {
    private final JdbcTemplate jdbc;
    public Optional<Type> create(Type type, String[] strings) {
        return Optional.empty();
    }

    public Collection<Type> findAll() {
        return null;
    }

    public Optional<Type> findById(int id) {
        return Optional.empty();
    }

    public boolean update(Type type, String[] strings) {
        return false;
    }

    public boolean remove(int id) {
        return false;
    }

    public void removeAll() {

    }
}
