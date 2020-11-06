package ru.sbt.employees.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sbt.employees.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Page<Employee> findPage(Pageable pageable);
    Page<Employee> findEmployeesByDepartmentId(Long id, Pageable pageable);
    Employee findById(Long id);
    Employee add(Employee employee);
    Employee update(Employee employee);
    void delete(Employee employee);
    void delete(Long id);
    long count();
}
