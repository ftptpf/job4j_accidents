package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Store<Accident, AccidentType> {
    private final AtomicInteger mapKey = new AtomicInteger(4);
    private final Map<Integer, Accident> store = new ConcurrentHashMap<>();
    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();

    private AccidentMem() {
        accidentTypes.put(1, new AccidentType(1, "Две машины"));
        accidentTypes.put(2, new AccidentType(2, "Машина и человек"));
        accidentTypes.put(3, new AccidentType(3, "Машина и велосипед"));
        store.put(1,
                new Accident(1,
                        "Авария велосипедиста",
                        "Столкнулись автомобиль и велосипедист",
                        "г.Москва, пр. Ленина 1", accidentTypes.get(3)));
        store.put(2,
                new Accident(2,
                        "Авария на пешеходном переходе",
                        "Автомобиль при движении задел пешехода",
                        "г.Москва, ул. Сиреневая 45", accidentTypes.get(2)));
        store.put(3,
                new Accident(3,
                        "Авария на парковке",
                        "При маневре Toyota задела Газель",
                        "г.Москва, ул. Тихая 18", accidentTypes.get(1)));

    }

    /**
     * Создание автонарушения.
     * @param accident автонарушение
     * @return если ключа в map еще нет вернет Optional со значением, в ином случае пустой Optional
     */
    public Optional<Accident> create(Accident accident) {
        accident.setId(mapKey.getAndIncrement());
        AccidentType type = accidentTypes.get(accident.getType().getId());
        accident.setType(type);
        Accident createAccident = store.putIfAbsent(accident.getId(), accident);
        return createAccident == null ? Optional.of(accident) : Optional.empty();
    }

    /**
     * Поиск всех автонарушений.
     * @return коллекция найденных значений
     */
    public Collection<Accident> findAll() {
        return store.values();
    }

    /**
     * Поиск конкретного автонарушения по ключу.
     * @param id ключ map
     * @return Optional с найденным значением, либо пустой Optional
     */
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * Обновление информации автонарушения по ключу.
     * Замена записи для указанного ключа происходит только в том случае,
     * если в данный момент она сопоставлена с некоторым значением.
     * @param accident автонарушение
     * @return true - если запись значения обновилась, false - если обновление не произошло
     */
    public boolean update(Accident accident) {
        AccidentType type = accidentTypes.get(accident.getType().getId());
        accident.setType(type);
        return store.replace(accident.getId(), accident) != null;
    }

    /**
     * Удаление конкретного автонарушения по ключу.
     * @param id ключ map
     * @return true - если удаление прошло успешно, в ином случае false
     */
    public boolean remove(int id) {
        return store.remove(id) != null;
    }

    /**
     * Удаление всех автонарушений.
     */
    public void removeAll() {
        store.clear();
    }

    /**
     * Поиск всех типов автонарушений.
     * @return коллекция найденных типов автонарушений
     */
    public Collection<AccidentType> findAllTypes() {
        return accidentTypes.values();
    }

}
