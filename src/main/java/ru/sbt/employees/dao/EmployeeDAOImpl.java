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
    public List<Employee> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Employee").getResultList();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getPage(int page, int itemPerPage) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Employee").setFirstResult(itemPerPage * (page - 1))
                .setMaxResults(itemPerPage).getResultList();
    }

    @Override
    public Employee getById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Override
    public long add(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        return (long) session.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(employee);
    }

    @Override
    public void update(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
    }

    @Override
    public int count() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(e.id) from Employee e", Number.class).getSingleResult().intValue();
    }
}
