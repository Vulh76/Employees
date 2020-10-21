package ru.sbt.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public List<Department> findAll() {
        return departmentService.findAll();
    }*/

    @GetMapping("/department")
    public Page<Department> findPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        return departmentService.findPage(page, size);
    }

    @GetMapping("/department/{id}")
    public Department findById(@PathVariable("id") int id) {
        return departmentService.findById(id);
    }

    @GetMapping("/department/{id}/employee")
    public List<Employee> findEmployeesByDepartmentId(@PathVariable("id") int id) {
        Department department = departmentService.findById(id);
        return department.getEmployees();
    }

    @PostMapping("/department")
    public Department add(@RequestBody Department department) {
        departmentService.add(department);
        return department;
    }

    @PutMapping("/department")
    public Department update(@RequestBody Department department) {
        departmentService.update(department);
        return department;
    }

    @DeleteMapping("/department/{id}")
    public void delete(@PathVariable("id") int id) {
        departmentService.delete(id);
    }

    @GetMapping("/department/count")
    public long count() {
        return departmentService.count();
    }
}
