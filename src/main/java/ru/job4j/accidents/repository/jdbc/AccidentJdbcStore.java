package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
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

    public Optional<Accident> create(Accident accident) {
        return Optional.empty();
    }

    public Collection<Accident> findAll() {
        /*return jdbc.query(
                """
                    SELECT
                    accidents.id accidents_id,
                    accidents.name accidents_name,
                    accidents.description accidents_description,
                    accidents.address accidents_address,
                    types.id types_id,
                    types.name types_name,
                    rules.name rules_name
                    FROM accidents
                    INNER JOIN types
                    ON accidents.type_id = types.id
                    INNER JOIN accidents_rules
                    ON accidents.id = accidents_rules.accident_id
                    INNER JOIN rules
                    ON accidents_rules.rule_id = rules.id;
                    """,
                (resultSet, rowNum) -> {
                    Accident accident = new Accident();
                    Type type = new Type();
                    type.setId(resultSet.getInt("types_id"));
                    type.setName(resultSet.getString("types_name"));
                    accident.setId(resultSet.getInt("accidents_id"));
                    accident.setName(resultSet.getString("accidents_name"));
                    accident.setText(resultSet.getString("accidents_description"));
                    accident.setAddress(resultSet.getString("accidents_address"));
                    accident.setType(type);
                    accident.setRules(new HashSet<>());
                    return accident;
                });*/
        String sql = """
                SELECT
                accidents.id accidents_id,
                accidents.name accidents_name,
                accidents.description accidents_description,
                accidents.address accidents_address,
                types.id types_id,
                types.name types_name,
                rules.name rules_name
                FROM accidents
                INNER JOIN types
                ON accidents.type_id = types.id
                INNER JOIN accidents_rules
                ON accidents.id = accidents_rules.accident_id
                INNER JOIN rules
                ON accidents_rules.rule_id = rules.id;
                """;
        return jdbc.query(sql, accidentRowMapper);
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

/*    private final ResultSetExtractor<Set<Rule>> setResultSetExtractor = (rs -> {
       rs.
    });*/

    public Set<Rule> getRules(int id) {
        String sql = """
                    SELECT
                    rules.id rule_id,
                    rules.name rules_name
                    FROM accidents_rules
                    INNER JOIN rules
                    ON accidents_rules.rule_id = rules.id
                    WHERE accidents_rules.accident_id = ?;
                    """;
/*        Set<Rule> rules = new HashSet<>();*/
/*        Rule ruleFromResultSet = jdbc.queryFo(sql, (resultSet, rowNum) -> {
            Rule rule = new Rule();
            rule.setId(resultSet.getInt("rule_id"));
            rule.setName(resultSet.getString("rules_name"));
            return rule;
        }, id);*/
        List<Rule> ruleList = jdbc.query(sql, ruleRowMapper, id);
/*        rules.add(ruleFromResultSet);*/
        return new HashSet<>(ruleList);
    }
}
