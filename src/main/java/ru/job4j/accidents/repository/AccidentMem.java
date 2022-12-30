package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem implements Store<Accident> {
    private final Map<Integer, Accident> store = new ConcurrentHashMap<>();

    private AccidentMem() {
        store.put(1,
                new Accident(1,
                        "Авария велосипедистов",
                        "Столкнулись два велосипедиста",
                        "г.Москва, пр. Ленина 1"));
        store.put(2,
                new Accident(2,
                        "Повреждение ограждения",
                        "Автомобиль при движении задел разделительное ограждение",
                        "г.Москва, ул. Сиреневая 45"));
        store.put(3,
                new Accident(3,
                        "Авария на парковке",
                        "При маневре Toyota задела Газель",
                        "г.Москва, ул. Тихая 18"));

    }

    /**
     * Создание автонарушения.
     * Если под данным ключом в map уже хранилось какое-то значение - оно перезапишется
     * @param id ключ map
     * @param accident автонарушение
     * @return значение которое, соответствовало ключу, или null если не найден ключ в map
     */
    public Accident create(int id, Accident accident) {
        return store.put(id, accident);
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
     * @return значение которое, соответствует ключу, или null если не найден ключ в map
     */
    public Accident findById(int id) {
        return store.get(id);
    }

    /**
     * Обновление информации автонарушения по ключу.
     * Замена записи для указанного ключа происходит только в том случае,
     * если в данный момент она сопоставлена с некоторым значением.
     * @param id ключ map
     * @param accident автонарушение
     * @return значение которое, соответствовало ключу, или null если не найден ключ в map
     */
    public Accident update(int id, Accident accident) {
        return store.replace(id, accident);
    }

    /**
     * Удаление конкретного автонарушения по ключу.
     * @param id ключ map
     * @return значение которое, соответствовало ключу, или null если не найден ключ в map
     */
    public Accident remove(int id) {
        return store.remove(id);
    }

    /**
     * Удаление всех автонарушений.
     */
    public void removeAll() {
        store.clear();
    }

}
