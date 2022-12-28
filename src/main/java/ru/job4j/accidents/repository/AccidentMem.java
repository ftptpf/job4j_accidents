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

    public Collection<Accident> findAll() {
        return store.values();
    }

}
