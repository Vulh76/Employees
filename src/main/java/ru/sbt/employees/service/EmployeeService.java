package ru.sbt.employees.service;

import org.springframework.data.domain.Page;
import ru.sbt.employees.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    Page<Employee> findPage(int page, int size);
    Employee findById(long id);
    Employee add(Employee employee);
    Employee update(Employee employee);
    void delete(Employee employee);
    void delete(long id);
    long count();
}
