package ru.sbt.employees.service;

import ru.sbt.employees.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();
    List<Department> getPage(int page, int count);
    Department getById(long id);
    int getCount();
    long add(Department department);
    void delete(Department department);
    void delete(long id);
    void update(Department department);
}
