package ru.sbt.employees.service;

import ru.sbt.employees.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    List<Employee> getPage(int page, int count);
    Employee getById(long id);
    int getCount();
    long add(Employee employee);
    void delete(Employee employee);
    void delete(long id);
    void update(Employee employee);
}
