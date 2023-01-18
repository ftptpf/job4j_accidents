package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.repository.Store;

import java.util.*;

@Primary
@Repository
@AllArgsConstructor
public class AccidentJdbcStore implements Store<Accident> {
    private final JdbcTemplate jdbc;

    private final RowMapper<Accident> accidentRowMapper = (resultSet, rowNum) -> {
        Accident accident = new Accident();
        accident.setId(resultSet.getInt("accidents_id"));
        accident.setName(resultSet.getString("accidents_name"));
        accident.setText(resultSet.getString("accidents_description"));
        accident.setAddress(resultSet.getString("accidents_address"));

        Type type = new Type();
        type.setId(resultSet.getInt("types_id"));
        type.setName(resultSet.getString("types_name"));
        accident.setType(type);
        Set<Rule> rules = getRules(resultSet.getInt("accidents_id"));
        accident.setRules(rules);
        return accident;
    };

    private final RowMapper<Rule> ruleRowMapper = (resultSet, rowNum) -> {
        Rule rule = new Rule();
        rule.setId(resultSet.getInt("rule_id"));
        rule.setName(resultSet.getString("rules_name"));
        return rule;
    };

    public Optional<Accident> create(Accident accident) {
        String sql = """
                INSERT INTO 
                accidents (name, description, address, type_id) 
                VALUES (?, ?, ?, ?);
                INSERT INTO accidents_rules(accident_id, rule_id) 
                VALUES (?, ?);
                """;
        Accident accidentFromBase = jdbc.queryForObject(sql, accidentRowMapper,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId()
                );
        return Optional.ofNullable(accidentFromBase);
    }

    public Collection<Accident> findAll() {
        String sql = """
                SELECT
                accidents.id accidents_id,
                accidents.name accidents_name,
                accidents.description accidents_description,
                accidents.address accidents_address,
                types.id types_id,
                types.name types_name
                FROM accidents
                INNER JOIN types
                ON accidents.type_id = types.id;
                """;
        return jdbc.query(sql, accidentRowMapper);
    }

    public Optional<Accident> findById(int id) {
        String sql = """
                SELECT
                accidents.id accidents_id,
                accidents.name accidents_name,
                accidents.description accidents_description,
                accidents.address accidents_address,
                types.id types_id,
                types.name types_name
                FROM accidents
                INNER JOIN types
                ON accidents.type_id = types.id
                WHERE accidents.id = ?;
                """;
        Accident accident = jdbc.queryForObject(sql, accidentRowMapper, id);
        return Optional.ofNullable(accident);
    }

    public boolean update(Accident accident) {
        String sql = """
                INSERT INTO
                accidents ()
                """;
        return false;
    }

    public boolean remove(int id) {
        String sql = """
                DELETE FROM
                accidents
                WHERE accidents.id = ?;
                """;
        return jdbc.update(sql, id) > 0;
    }

    public void removeAll() {
        String sql = """
                TRUNCATE accidents_rules, accidents RESTART IDENTITY;
                """;
        jdbc.update(sql);

    }

    private Set<Rule> getRules(int id) {
        String sql = """
                SELECT
                rules.id rule_id,
                rules.name rules_name
                FROM accidents_rules
                INNER JOIN rules
                ON accidents_rules.rule_id = rules.id
                WHERE accidents_rules.accident_id = ?;
                """;
        List<Rule> ruleList = jdbc.query(sql, ruleRowMapper, id);
        return new HashSet<>(ruleList);
    }
}
