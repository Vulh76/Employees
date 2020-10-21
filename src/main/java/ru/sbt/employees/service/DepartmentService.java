package ru.sbt.employees.service;

import org.springframework.data.domain.Page;
import ru.sbt.employees.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAll();

    Page<Department> findPage(int page, int size);

    Department findById(long id);

    Department add(Department department);

    Department update(Department department);

    void delete(Department department);

    void delete(long id);

    long count();
}
