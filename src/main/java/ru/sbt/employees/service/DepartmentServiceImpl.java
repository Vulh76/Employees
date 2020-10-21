package ru.sbt.employees.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.employees.dao.EmployeeDAO;
import ru.sbt.employees.model.Department;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeDAO employeeDAO;
    private final static Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    public DepartmentServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<Department> getAll() {
        return employeeDAO.getAll(Department.class);
    }

    @Override
    @Transactional
    public List<Department> getPage(int page, int count) {
        return employeeDAO.getPage(Department.class, page, count);
    }

    @Override
    @Transactional
    public Department getById(long id) {
        return employeeDAO.getById(Department.class, id);
    }

    @Override
    @Transactional
    public int getCount() {
        return employeeDAO.count(Department.class);
    }

    @Override
    @Transactional
    public long add(Department department) {
        return employeeDAO.add(department);
    }

    @Override
    @Transactional
    public void delete(Department department) {
        employeeDAO.delete(department);
    }

    @Override
    @Transactional
    public void delete(long id) {
        employeeDAO.delete(id);
    }

    @Override
    @Transactional
    public void update(Department department) {
        employeeDAO.update(department);
    }
}
