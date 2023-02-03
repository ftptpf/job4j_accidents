package ru.job4j.accidents.repository.mem;

import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class RuleMem implements Store<Rule, Integer> {
    private final AtomicInteger mapKey = new AtomicInteger(4);
    private final Map<Integer, Rule> store = new ConcurrentHashMap<>();

    private RuleMem() {
        store.put(1, new Rule(1, "Статья 1"));
        store.put(2, new Rule(2, "Статья 2"));
        store.put(3, new Rule(3, "Статья 3"));
    }

    public Optional<Rule> create(Rule rule) {
        rule.setId(mapKey.getAndIncrement());
        Rule createRule = store.putIfAbsent(rule.getId(), rule);
        return createRule == null ? Optional.of(rule) : Optional.empty();
    }

    public Collection<Rule> findAll() {
        return store.values();
    }

    public Optional<Rule> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    public boolean update(Rule rule) {
        return store.replace(rule.getId(), rule) != null;
    }

    public boolean remove(Integer id) {
        return store.remove(id) != null;
    }

    public void removeAll() {
        store.clear();
    }
}
