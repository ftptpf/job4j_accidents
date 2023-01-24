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
            int id = (int) session.save(accident);
            return id != 0 ? Optional.of(accident) : Optional.empty();
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
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Accident a JOIN FETCH a.rules WHERE a.id = :fId", Accident.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
        }
    }

    public boolean update(Accident accident) {
        try (Session session = sf.openSession()) {
            session.update(accident);
            return true;
        }
    }

    public boolean remove(int id) {
        try (Session session = sf.openSession()) {
            Accident accident = (Accident) session.createQuery("DELETE FROM Accident a WHERE a.id = :fId")
                    .setParameter("fId", id)
                    .getSingleResult();
            return accident.getId() == id;
        }
    }

    public void removeAll() {
        try (Session session = sf.openSession()) {
            session.createSQLQuery("TRUNCATE accidents_rules, accidents RESTART IDENTITY");
        }

    }
}
