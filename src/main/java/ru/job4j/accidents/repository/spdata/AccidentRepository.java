package ru.job4j.accidents.repository.spdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.Store;

public interface AccidentRepository extends Store<Accident>, CrudRepository<Accident, Integer> {
}
