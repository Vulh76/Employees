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
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }*/

    @GetMapping("/employee")
    public List<Employee> getPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                  @RequestParam(name = "count", defaultValue = "10") int count) {
        return employeeService.getPage(page, count);
    }

    @GetMapping("/employee/{id}")
    public Employee getById(@PathVariable("id") int id) {
        return employeeService.getById(id);
    }

    @GetMapping("/employee/{id}/department")
    public Department getDepartmentByEmployeeId(@PathVariable("id") int id) {
        Employee employee = employeeService.getById(id);
        return employee.getDepartment();
    }

    @GetMapping("/employee/count")
    public int getCount() {
        return employeeService.getCount();
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        employeeService.add(employee);
        return employee;
    }

    @PutMapping("/employee")
    public Employee editEmployee(@RequestBody Employee employee) {
        employeeService.update(employee);
        return employee;
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") int id) {
        employeeService.delete(id);
    }
}
