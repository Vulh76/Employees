package ru.sbt.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.employees.dao.EmployeeDAO;
import ru.sbt.employees.model.Employee;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional
    public List<Employee> getAll(String sortColumn, boolean desc) {
        return employeeDAO.getAll(sortColumn, desc);
    }

    @Transactional
    public List<Employee> getPage(int page, int itemPerPage, String sortColumn, boolean desc) {
        return employeeDAO.getPage(page, itemPerPage, sortColumn, desc);
    }

    @Transactional
    public Employee getById(long id) {
        return employeeDAO.getById(id);
    }

    @Transactional
    public long add(Employee employee) {
        return employeeDAO.add(employee);
    }

    @Transactional
    public void delete(Employee employee) {
        employeeDAO.delete(employee);
    }

    @Transactional
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    @Transactional
    public int count() {
        return employeeDAO.count();
    }
}
