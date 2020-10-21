package ru.sbt.employees.service;

import org.springframework.data.domain.Page;
import ru.sbt.employees.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    Page<Department> findPage(int page, int size);
    Department findById(Long id);
    Department add(Department department);
    Department update(Department department);
    void delete(Department department);
    void delete(Long id);
    long count();
}
