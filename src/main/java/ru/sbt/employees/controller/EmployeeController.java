package ru.sbt.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.employees.model.Department;
import ru.sbt.employees.model.Employee;
import ru.sbt.employees.service.EmployeeService;

import javax.persistence.PersistenceException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    private int itemPerPage = 10;
    private final Map<String, Boolean> sortingDirectionMap = new HashMap<>();

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/count")
    public int getCount() {
        return employeeService.getEmployeesCount();
    }

    @GetMapping("/employees")
    public List<Employee> getAll() {
        return employeeService.getAllEmployees("id", false);
    }

    @GetMapping("/employees/{page}/{sort}")
    public List<Employee> getPage(@PathVariable(name = "page") int page,
                                  @PathVariable(name = "sort") String sortColumn) {
        List<Employee> employees;
        boolean sortingDirection = sortingDirectionMap.merge(sortColumn, false, (oldVal, newVal) -> !oldVal);
        employees = employeeService.getPageEmployees(page, itemPerPage, sortColumn, sortingDirection);
        return employees;
    }

    @GetMapping("/employee/{id}")
    public Employee getById(@PathVariable("id") int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employee/department/{id}")
    public Department getDepartmentByEmployeeId(@PathVariable("id") int id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee.getDepartment();
    }

    @PostMapping("/employee/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return employee;
    }

    @PostMapping("/employee/edit")
    public Employee editEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return employee;
    }

    @GetMapping("/employee/delete/{id}")
    public void deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
    }
}
