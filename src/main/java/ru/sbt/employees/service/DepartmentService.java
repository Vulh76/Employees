package ru.sbt.employees.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sbt.employees.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    Page<Department> findPage(Pageable pageable);
    Department findById(Long id);
    Department add(Department department);
    Department update(Department department);
    void delete(Department department);
    void delete(Long id);
    long count();
}
