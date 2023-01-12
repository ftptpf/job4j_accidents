package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Primary
@Repository
@AllArgsConstructor
public class AccidentJdbcStore implements Store<Accident> {
    private final JdbcTemplate jdbc;

    public Optional<Accident> create(Accident accident) {
        return Optional.empty();
    }

    public Collection<Accident> findAll() {
        return jdbc.query(
                """
                    SELECT * FROM accidents
                    INNER JOIN types
                    ON accidents.type_id = types.id
                    """,
                (resultSet, rowNum) -> {
                    Accident accident = new Accident();
                    Type type = new Type();
                    type.setId(resultSet.getInt("type.id"));
                    type.setName(resultSet.getString("type.name"));
                    accident.setId(resultSet.getInt("id"));
                    accident.setName(resultSet.getString("name"));
                    accident.setText(resultSet.getString("description"));
                    accident.setAddress(resultSet.getString("address"));
                    accident.setType(type);
                    accident.setRules(new HashSet<>());
                    return accident;
                });
    }

    public Optional<Accident> findById(int id) {
        return Optional.empty();
    }

    public boolean update(Accident accident) {
        return false;
    }

    public boolean remove(int id) {
        return false;
    }

    public void removeAll() {

    }
}
