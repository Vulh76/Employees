package ru.sbt.employees.service;

import ru.sbt.employees.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments(String sortColumn, boolean desc);
    List<Department> getPageDepartments(int page, int itemPerPage, String sortColumn, boolean desc);
    Department getDepartmentById(long id);
    long addDepartment(Department department);
    void deleteDepartment(Department department);
    void updateDepartment(Department department);
    int countDepartments();

}
