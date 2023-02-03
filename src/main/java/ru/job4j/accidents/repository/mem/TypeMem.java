package ru.job4j.accidents.repository.mem;

import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class TypeMem implements Store<Type, Integer> {
    private final AtomicInteger mapKey = new AtomicInteger(4);
    private final Map<Integer, Type> store = new ConcurrentHashMap<>();

    private TypeMem() {
        store.put(1, new Type(1, "Две машины"));
        store.put(2, new Type(2, "Машина и человек"));
        store.put(3, new Type(3, "Машина и велосипед"));
    }

    public Optional<Type> create(Type type) {
        type.setId(mapKey.getAndIncrement());
        Type createType = store.putIfAbsent(type.getId(), type);
        return createType == null ? Optional.of(type) : Optional.empty();
    }

    public Collection<Type> findAll() {
        return store.values();
    }

    public Optional<Type> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    public boolean update(Type type) {
        return store.replace(type.getId(), type) != null;
    }

    public boolean remove(Integer id) {
        return store.remove(id) != null;
    }

    public void removeAll() {
        store.clear();
    }

}
