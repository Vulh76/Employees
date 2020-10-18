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
    public <T> List<T> getAll(Class<T> clazz, String sortColumn, boolean desc) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from " + clazz.getSimpleName();
        if(sortColumn != null && !sortColumn.isEmpty()) {
            hql += " order by " + sortColumn;
            if(desc) {
                hql += " DESC";
            }
        }
        return session.createQuery(hql).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getPage(Class<T> clazz, int page, int itemPerPage, String sortColumn, boolean desc) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from " + clazz.getSimpleName();
        if(sortColumn != null && !sortColumn.isEmpty()) {
            hql += " order by " + sortColumn;
            if(desc) {
                hql += " DESC";
            }
        }
        return session.createQuery(hql)
                .setFirstResult(itemPerPage * (page - 1))
                .setMaxResults(itemPerPage)
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
