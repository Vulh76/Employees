package ru.sbt.employees.service;

import ru.sbt.employees.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    List<Employee> getPage(int page, int itemPerPage);
    Employee getById(long id);
    long add(Employee employee);
    void delete(Employee employee);
    void update(Employee employee);
    int count();
}
