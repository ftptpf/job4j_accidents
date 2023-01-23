package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.repository.Store;

import java.util.*;

@Repository
@AllArgsConstructor
public class AccidentJdbcStore implements Store<Accident> {
    private final JdbcTemplate jdbc;

    /**
     * SQL for Accident
     */

    private static final String SQL_CREATE_ACCIDENT_AND_RETURNING_ID = """
            INSERT INTO accidents (name, description, address, type_id)
            VALUES (?, ?, ?, ?)
            RETURNING id;
            """;

    private static final String SQL_UPDATE_ACCIDENT_BY_ID = """
            UPDATE accidents
            SET name = ?, description = ?, address = ?, type_id =?
            WHERE id = ?;
            """;

    private static final String SQL_GET_ALL_ACCIDENTS = """
            SELECT
            accidents.id accidents_id,
            accidents.name accidents_name,
            accidents.description accidents_description,
            accidents.address accidents_address,
            types.id types_id,
            types.name types_name,
            rules.id rule_id,
            rules.name rule_name
            FROM accidents
            INNER JOIN types
            ON accidents.type_id = types.id
            INNER JOIN accidents_rules
            ON accidents_rules.accident_id = accidents.id
            INNER JOIN rules
            ON rules.id = accidents_rules.rule_id
            """;
    private static final String SQL_GET_ACCIDENT_BY_ID = """
            SELECT
            accidents.id accidents_id,
            accidents.name accidents_name,
            accidents.description accidents_description,
            accidents.address accidents_address,
            types.id types_id,
            types.name types_name,
            rules.id rule_id,
            rules.name rule_name
            FROM accidents
            INNER JOIN types
            ON accidents.type_id = types.id
            INNER JOIN accidents_rules
            ON accidents_rules.accident_id = accidents.id
            INNER JOIN rules
            ON rules.id = accidents_rules.rule_id
            WHERE accidents.id = ?;
            """;

    private static final String SQL_REMOVE_ALL_ACCIDENTS = """
            TRUNCATE accidents_rules, accidents
            RESTART IDENTITY;
            """;

    private static final String SQL_REMOVE_ACCIDENT_BY_ID = """
            DELETE FROM accidents
            WHERE accidents.id = ?;
            """;

    /**
     * SQL for Rule
     */

    private static final String SQL_REMOVE_ACCIDENT_RULE_CONNECTION_BY_ID = """
            DELETE FROM accidents_rules
            WHERE accident_id = ?;
            """;

    private static final String SQL_SET_ACCIDENT_RULE_CONNECTION_BY_ID = """
            INSERT INTO accidents_rules (accident_id, rule_id)
            VALUES (?, ?);
            """;

    private final ResultSetExtractor<List<Accident>> accidentListResultSetExtractor = (resultSet) -> {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        while (resultSet.next()) {
            Accident accident = new Accident();
            int id = resultSet.getInt("accidents_id");
            Rule rule = new Rule();
            rule.setId(resultSet.getInt("rule_id"));
            rule.setName(resultSet.getString("rule_name"));
            if (accidentMap.containsKey(id)) {
                accidentMap.get(id).getRules().add(rule);
            } else {
                accident.setId(resultSet.getInt("accidents_id"));
                accident.setName(resultSet.getString("accidents_name"));
                accident.setText(resultSet.getString("accidents_description"));
                accident.setAddress(resultSet.getString("accidents_address"));
                Type type = new Type();
                type.setId(resultSet.getInt("types_id"));
                type.setName(resultSet.getString("types_name"));
                accident.setType(type);
                Set<Rule> rules = new HashSet<>();
                rules.add(rule);
                accident.setRules(rules);
                accidentMap.put(id, accident);
            }
        }
        return new ArrayList<>(accidentMap.values());
    };

    private final ResultSetExtractor<Accident> accidentResultSetExtractor = (resultSet) -> {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("accidents_id");
            Rule rule = new Rule();
            rule.setId(resultSet.getInt("rule_id"));
            rule.setName(resultSet.getString("rule_name"));
            if (accidentMap.containsKey(id)) {
                accidentMap.get(id).getRules().add(rule);
            } else {
                Accident accident = new Accident();
                accident.setId(resultSet.getInt("accidents_id"));
                accident.setName(resultSet.getString("accidents_name"));
                accident.setText(resultSet.getString("accidents_description"));
                accident.setAddress(resultSet.getString("accidents_address"));
                Type type = new Type();
                type.setId(resultSet.getInt("types_id"));
                type.setName(resultSet.getString("types_name"));
                accident.setType(type);
                Set<Rule> rules = new HashSet<>();
                rules.add(rule);
                accident.setRules(rules);
                accidentMap.put(id, accident);
            }
        }
        return accidentMap.get(id);
    };

    public Optional<Accident> create(Accident accident) {
        int id = jdbc.queryForObject(SQL_CREATE_ACCIDENT_AND_RETURNING_ID, Integer.class,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId());
        setRules(id, accident.getRules());
        return findById(id);
    }

    public boolean update(Accident accident) {
        int numberUpdatedRows = jdbc.update(SQL_UPDATE_ACCIDENT_BY_ID,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        setRules(accident.getId(), accident.getRules());
        return numberUpdatedRows > 0;
    }

    public Collection<Accident> findAll() {
        return jdbc.query(SQL_GET_ALL_ACCIDENTS, accidentListResultSetExtractor);
    }

    public Optional<Accident> findById(int id) {
        Accident accident = jdbc.query(SQL_GET_ACCIDENT_BY_ID, accidentResultSetExtractor, id);
        return Optional.ofNullable(accident);
    }

    public boolean remove(int id) {
        return jdbc.update(SQL_REMOVE_ACCIDENT_BY_ID, id) > 0;
    }

    public void removeAll() {
        jdbc.update(SQL_REMOVE_ALL_ACCIDENTS);
    }

    private void setRules(int idAccident, Set<Rule> ruleSet) {
        jdbc.update(SQL_REMOVE_ACCIDENT_RULE_CONNECTION_BY_ID, idAccident);
        for (Rule rule : ruleSet) {
            jdbc.update(SQL_SET_ACCIDENT_RULE_CONNECTION_BY_ID, idAccident, rule.getId());
        }
    }

}
