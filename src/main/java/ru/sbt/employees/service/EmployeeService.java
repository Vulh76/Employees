package ru.sbt.employees.service;

import ru.sbt.employees.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll(String sortColumn, boolean desc);
    List<Employee> getPage(int page, int itemPerPage, String sortColumn, boolean desc);
    Employee getById(long id);
    long add(Employee employee);
    void delete(Employee employee);
    void update(Employee employee);
    int count();
}
