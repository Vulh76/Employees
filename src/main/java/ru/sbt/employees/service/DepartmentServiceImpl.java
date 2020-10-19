package ru.sbt.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.employees.dao.EmployeeDAO;
import ru.sbt.employees.model.Department;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public DepartmentServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<Department> getAllDepartments(String sortColumn, boolean desc) {
        return employeeDAO.getAll(Department.class, sortColumn, desc);
    }

    @Override
    @Transactional
    public List<Department> getPageDepartments(int page, int itemPerPage, String sortColumn, boolean desc) {
        return employeeDAO.getPage(Department.class, page, itemPerPage, sortColumn, desc);
    }

    @Override
    @Transactional
    public Department getDepartmentById(long id) {
        return employeeDAO.getById(Department.class, id);
    }

    @Override
    @Transactional
    public long addDepartment(Department department) {
        return employeeDAO.add(department);
    }

    @Override
    @Transactional
    public void deleteDepartment(Department department) {
        employeeDAO.delete(department);
    }

    @Override
    @Transactional
    public void updateDepartment(Department department) {
        employeeDAO.update(department);
    }

    @Override
    @Transactional
    public int countDepartments() {
        return employeeDAO.count(Department.class);
    }
}
