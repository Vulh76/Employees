package ru.sbt.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.sbt.employees.model.Department;
import ru.sbt.employees.model.Employee;
import ru.sbt.employees.service.DepartmentService;

import javax.persistence.PersistenceException;
import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments/count")
    public int getCount() {
        return departmentService.getDepartmentsCount();
    }

    @GetMapping("/departments")
    public List<Department> getAll() {
        List<Department> departments;
        try {
            departments = departmentService.getAllDepartments("id", false);
        } catch (PersistenceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ошибка выполнения SQL-запрса: " + e.getMessage());
        }
        return departments;
    }

    @GetMapping("/department/{id}")
    public Department getById(@PathVariable("id") int id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/department/employees/{id}")
    public List<Employee> getEmployeesByDepartmentId(@PathVariable("id") int id) {
        Department department = departmentService.getDepartmentById(id);
        return department.getEmployees();
    }

    @PostMapping("/department/add")
    public Department addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        return department;
    }

    @PostMapping("/department/edit")
    public Department editDepartment(@RequestBody Department department) {
        departmentService.updateDepartment(department);
        return department;
    }

    @GetMapping("/department/delete/{id}")
    public void editDepartment(@PathVariable("id") int id) {
        departmentService.deleteDepartment(id);
    }
}
