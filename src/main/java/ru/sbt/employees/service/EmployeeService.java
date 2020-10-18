package ru.sbt.employees.service;

import ru.sbt.employees.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees(String sortColumn, boolean desc);
    List<Employee> getPageEmployees(int page, int itemPerPage, String sortColumn, boolean desc);
    Employee getEmployeeById(long id);
    long addEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    void updateEmployee(Employee employee);
    int countEmployees();
}
