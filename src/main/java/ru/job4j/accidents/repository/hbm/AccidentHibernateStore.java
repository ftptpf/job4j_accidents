package ru.job4j.accidents.repository.hbm;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.Optional;

@Primary
@Repository
@AllArgsConstructor
public class AccidentHibernateStore implements Store<Accident> {
    private final SessionFactory sf;

    public Optional<Accident> create(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return Optional.ofNullable(accident);
        }
    }

    public Collection<Accident> findAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Accident a JOIN FETCH a.rules", Accident.class)
                    .getResultStream()
                    .distinct()
                    .toList();
        }
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
}
