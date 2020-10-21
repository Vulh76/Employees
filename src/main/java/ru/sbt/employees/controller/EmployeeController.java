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
import ru.sbt.employees.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*@GetMapping("/employee")
    public List<Employee> findAll() {
        return employeeService.getAllEmployees();
    }*/

    @GetMapping("/employee")
    public Page<Employee> findPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "10") int size) {
        return employeeService.findPage(page, size);
    }

    @GetMapping("/employee/{id}")
    public Employee findById(@PathVariable("id") int id) {
        return employeeService.findById(id);
    }

    @GetMapping("/employee/{id}/department")
    public Department findDepartmentByEmployeeId(@PathVariable("id") int id) {
        Employee employee = employeeService.findById(id);
        return employee.getDepartment();
    }

    @PostMapping("/employee")
    public Employee add(@RequestBody Employee employee) {
        employeeService.add(employee);
        return employee;
    }

    @PutMapping("/employee")
    public Employee update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return employee;
    }

    @DeleteMapping("/employee/{id}")
    public void delete(@PathVariable("id") int id) {
        employeeService.delete(id);
    }

    @GetMapping("/employee/count")
    public long count() {
        return employeeService.count();
    }

}
