package ru.sbt.employees.dao;

import ru.sbt.employees.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAll();
    List<Employee> getPage(int page, int itemPerPage, String sortColumn, boolean desc);
    Employee getById(long id);
    long add(Employee employee);
    void delete(Employee employee);
    void update(Employee employee);
    int count();
}
