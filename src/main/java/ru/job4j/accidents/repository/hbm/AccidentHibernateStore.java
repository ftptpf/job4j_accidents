package ru.job4j.accidents.repository.hbm;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/*@Primary*/
@Repository
@AllArgsConstructor
public class AccidentHibernateStore implements Store<Accident> {
    private final SessionFactory sf;

    public Optional<Accident> create(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            int id = (int) session.save(accident);
            session.getTransaction().commit();
            return id != 0 ? Optional.of(accident) : Optional.empty();
        }
    }

    public Collection<Accident> findAll() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List<Accident> accidentList = session.createQuery(
                    "FROM Accident a JOIN FETCH a.rules ORDER BY a.id", Accident.class)
                    .getResultStream()
                    .distinct()
                    .toList();
            session.getTransaction().commit();
            return accidentList;
        }
    }

    public Optional<Accident> findById(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Optional<Accident> accidentOptional = session.createQuery(
                    "FROM Accident a JOIN FETCH a.rules WHERE a.id = :fId", Accident.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
            session.getTransaction().commit();
            return accidentOptional;
        }
    }

    public boolean update(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(accident);
            session.getTransaction().commit();
            return true;
        }
    }

    public boolean remove(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Accident accident = (Accident) session.createQuery("DELETE FROM Accident a WHERE a.id = :fId")
                    .setParameter("fId", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return accident.getId() == id;
        }
    }

    public void removeAll() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE accidents_rules, accidents RESTART IDENTITY");
            session.getTransaction().commit();
        }
    }
}
