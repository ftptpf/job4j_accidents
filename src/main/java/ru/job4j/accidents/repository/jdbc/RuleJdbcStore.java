package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RuleJdbcStore implements Store<Rule> {
    private final JdbcTemplate jdbc;

    private final RowMapper<Rule> ruleRowMapper = (resultSet, rowNum) -> {
        Rule rule = new Rule();
        rule.setId(resultSet.getInt("rule_id"));
        rule.setName(resultSet.getString("rules_name"));
        return rule;
    };

    public Optional<Rule> create(Rule rule) {
        return Optional.empty();
    }

    public Collection<Rule> findAll() {
        String sql = """
                SELECT
                rules.id rule_id,
                rules.name rules_name
                FROM rules;
                """;
        return jdbc.query(sql, ruleRowMapper);
    }

    public Optional<Rule> findById(int id) {
        String sql = """
                SELECT
                rules.id rule_id,
                rules.name rules_name
                FROM rules
                WHERE rules.id = ?;
                """;
        Rule rule = jdbc.queryForObject(sql, ruleRowMapper, id);
        return Optional.ofNullable(rule);
    }

    public boolean update(Rule rule) {
        return false;
    }

    public boolean remove(int id) {
        return false;
    }

    public void removeAll() {
    }
}
