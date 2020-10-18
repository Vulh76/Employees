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

    @Override
    @Transactional
    public List<Employee> getAllEmployees(String sortColumn, boolean desc) {
        return employeeDAO.getAll(Employee.class, sortColumn, desc);
    }

    @Override
    @Transactional
    public List<Employee> getPageEmployees(int page, int itemPerPage, String sortColumn, boolean desc) {
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
    public void updateEmployee(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    @Transactional
    public int countEmployees() {
        return employeeDAO.count(Employee.class);
    }
}
