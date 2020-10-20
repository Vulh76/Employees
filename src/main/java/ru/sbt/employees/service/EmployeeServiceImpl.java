package ru.sbt.employees.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.employees.dao.EmployeeDAO;
import ru.sbt.employees.model.Employee;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> getAll() {
        return employeeDAO.getAll(Employee.class);
    }

    @Override
    @Transactional
    public List<Employee> getPage(int page, int count) {
        logger.debug("Вызван метод getPageEmployees. Page: {}, Sort by: {}", page, count);
        return employeeDAO.getPage(Employee.class, page, count);
    }

    @Override
    @Transactional
    public Employee getById(long id) {
        return employeeDAO.getById(Employee.class, id);
    }

    @Override
    @Transactional
    public int getCount() {
        return employeeDAO.count(Employee.class);
    }

    @Override
    @Transactional
    public long add(Employee employee) {
        return employeeDAO.add(employee);
    }

    @Override
    @Transactional
    public void delete(Employee employee) {
        employeeDAO.delete(employee);
    }

    @Override
    @Transactional
    public void delete(long id) {
        employeeDAO.delete(id);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }
}
