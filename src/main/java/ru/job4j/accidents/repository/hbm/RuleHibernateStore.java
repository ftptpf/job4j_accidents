package ru.job4j.accidents.repository.hbm;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.Optional;

@Primary
@Repository
@AllArgsConstructor
public class RuleHibernateStore implements Store<Rule> {
    private final SessionFactory sf;

    public Optional<Rule> create(Rule rule) {
        return Optional.empty();
    }

    public Collection<Rule> findAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Rule", Rule.class).list();
        }
    }

    public Optional<Rule> findById(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Rule r WHERE r.id = :fId", Rule.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
        }
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
