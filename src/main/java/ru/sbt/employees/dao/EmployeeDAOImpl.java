package ru.sbt.employees.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.employees.model.Employee;

import java.util.List;

@Component
public class EmployeeDAOImpl implements EmployeeDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> clazz) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from " + clazz.getSimpleName();
        return session.createQuery(hql).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getPage(Class<T> clazz, int page, int count) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from " + clazz.getSimpleName();
        return session.createQuery(hql)
                .setFirstResult(10 * (page - 1))
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public <T> T getById(Class<T> clazz, long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(clazz, id);
    }

    @Override
    public <T> long add(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
        //return entity.getId();
        return (long) session.save(entity);
    }

    @Override
    public <T> void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
    }

    @Override
    public <T> void delete(Class<T> clazz, long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(clazz, id));
    }

    @Override
    public <T> void update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
    }

    @Override
    public <T> int count(Class<T> clazz) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(e.id) from Employee e", Number.class).getSingleResult().intValue();
    }
}
