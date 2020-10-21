package ru.sbt.employees.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.employees.model.Department;
import ru.sbt.employees.model.Employee;
import ru.sbt.employees.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*@GetMapping("/all")
    public List<Employee> findAll() {
        logger.debug("Handling find all");
        return employeeService.getAllEmployees();
    }*/

    @GetMapping("/")
    public Page<Employee> findPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "10") int size) {
        logger.debug("Handling find page: page={}, size={}", page, size);
        return employeeService.findPage(page, size);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") long id) {
        logger.debug("Handling find by id: id={}", id);
        return employeeService.findById(id);
    }

    @GetMapping("/{id}/department")
    public Department findDepartmentByEmployeeId(@PathVariable("id") long id) {
        logger.debug("Handling find department by employee id: id={}", id);
        Employee employee = employeeService.findById(id);
        return employee.getDepartment();
    }

    @PostMapping("/")
    public Employee add(@RequestBody Employee employee) {
        logger.debug("Handling add: {}", employee);
        employeeService.add(employee);
        return employee;
    }

    @PutMapping("/")
    public Employee update(@RequestBody Employee employee) {
        logger.debug("Handling update: {}", employee);
        employeeService.update(employee);
        return employee;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        logger.debug("Handling delete: id={}", id);
        employeeService.delete(id);
    }

    @GetMapping("/count")
    public long count() {
        logger.debug("Handling count");
        return employeeService.count();
    }

}
