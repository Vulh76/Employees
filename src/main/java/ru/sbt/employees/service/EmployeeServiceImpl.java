package ru.sbt.employees.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.employees.dao.EmployeeDAO;
import ru.sbt.employees.model.Employee;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final Logger logger;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO, Logger logger) {
        this.employeeDAO = employeeDAO;
        this.logger = logger;
    }

    @Override
    @Transactional
    public int getEmployeesCount() {
        return employeeDAO.count(Employee.class);
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees(String sortColumn, boolean desc) {
        return employeeDAO.getAll(Employee.class, sortColumn, desc);
    }

    @Override
    @Transactional
    public List<Employee> getPageEmployees(int page, int itemPerPage, String sortColumn, boolean desc) {
        //logger.debug("Вызван метод getPageEmployees. Page: {}, Sort by: {}", page, sortColumn);
        return employeeDAO.getPage(Employee.class, page, itemPerPage, sortColumn, desc);
    }

    @Override
    @Transactional
    public Employee getEmployeeById(long id) {
        return employeeDAO.getById(Employee.class, id);
    }

    @Override
    @Transactional
    public long addEmployee(Employee employee) {
        return employeeDAO.add(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Employee employee) {
        employeeDAO.delete(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(long id) {
        employeeDAO.delete(id);
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        employeeDAO.update(employee);
    }
}
