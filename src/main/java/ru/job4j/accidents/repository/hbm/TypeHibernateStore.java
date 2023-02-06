package ru.job4j.accidents.repository.hbm;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Type;
import ru.job4j.accidents.repository.Store;

import java.util.Collection;
import java.util.Optional;

/*@Primary*/
/*@Repository*/
@AllArgsConstructor
public class TypeHibernateStore implements Store<Type, Integer> {
    private final SessionFactory sf;

    public Optional<Type> create(Type type) {
        return Optional.empty();
    }

    public Collection<Type> findAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Type", Type.class).list();
        }
    }

    public Optional<Type> findById(Integer id) {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Type t WHERE t.id = :fId", Type.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
        }
    }

    public boolean update(Type type) {
        return false;
    }

    public boolean remove(Integer id) {
        return false;
    }

    public void removeAll() {

    }
}
