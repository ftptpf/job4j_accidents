package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    private final RowMapper<Type> typeRowMapper = (resultSet, rowNum) -> {
        Type type = new Type();
        type.setId(resultSet.getInt("type_id"));
        type.setName(resultSet.getString("type_name"));
        return type;
    };
    public Optional<Type> create(Type type) {
        //TODO
        return Optional.empty();
    }

    public Collection<Type> findAll() {
        String sql = """
                SELECT
                types.id type_id,
                types.name type_name
                FROM types;
                """;
        return jdbc.query(sql, typeRowMapper);
    }

    public Optional<Type> findById(int id) {
        String sql = """
                SELECT
                types.id type_id,
                types.name type_name
                FROM types
                WHERE types.id = ?;
                """;
        Type type = jdbc.queryForObject(sql, typeRowMapper, id);
        return Optional.ofNullable(type);
    }

    public boolean update(Type type) {
        //TODO
        return false;
    }

    public boolean remove(int id) {
        //TODO
        return false;
    }

    public void removeAll() {
        //TODO
    }
}
