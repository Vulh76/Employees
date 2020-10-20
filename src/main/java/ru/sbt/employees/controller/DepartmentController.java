package ru.sbt.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.employees.model.Department;
import ru.sbt.employees.model.Employee;
import ru.sbt.employees.service.DepartmentService;

import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /*@GetMapping("/department")
    public List<Department> getAll() {
        return departmentService.getAllDepartments();
    }*/

    @GetMapping("/department")
    public List<Department> getPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "count", defaultValue = "10") int count) {
        return departmentService.getPage(page, count);
    }


    @GetMapping("/department/{id}")
    public Department getById(@PathVariable("id") int id) {
        return departmentService.getById(id);
    }

    @GetMapping("/department/{id}/employee")
    public List<Employee> getEmployeesByDepartmentId(@PathVariable("id") int id) {
        Department department = departmentService.getById(id);
        return department.getEmployees();
    }

    @GetMapping("/department/count")
    public int getCount() {
        return departmentService.getCount();
    }

    @PostMapping("/department")
    public Department addDepartment(@RequestBody Department department) {
        departmentService.add(department);
        return department;
    }

    @PutMapping("/department")
    public Department editDepartment(@RequestBody Department department) {
        departmentService.update(department);
        return department;
    }

    @DeleteMapping("/department/{id}")
    public void editDepartment(@PathVariable("id") int id) {
        departmentService.delete(id);
    }
}
