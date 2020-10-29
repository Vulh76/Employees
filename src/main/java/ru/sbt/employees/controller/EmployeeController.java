package ru.sbt.employees.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
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

import java.util.List;
import java.util.function.Function;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public List<Employee> findAll() {
        logger.debug("Handling find all");
        return employeeService.findAll();
    }

    @GetMapping
    public Page<Employee> findPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        logger.debug("Handling find page: page={}, size={}", page, size);

        Page<Employee> employees = employeeService.findPage(page, size);

        for(Employee employee : employees.getContent()) {
            employee.add(linkTo(methodOn(EmployeeController.class).findById(employee.getId())).withSelfRel());
            employee.add(linkTo(methodOn(DepartmentController.class).findById(employee.getId())).withRel("department"));
        }

        return employees;
    }

    @GetMapping("/{id}")
    public EntityModel<Employee> findById(@PathVariable("id") Long id) {
        logger.debug("Handling find by id: id={}", id);

        Employee employee = employeeService.findById(id);

        /*employee.add(linkTo(methodOn(EmployeeController.class)
                .findById(id)).withSelfRel());
        employee.add(linkTo(methodOn(DepartmentController.class)
                .findById(employee.getId())).withRel("department"));*/

        return EntityModel.of(employeeService.findById(id)).add(
                linkTo(methodOn(EmployeeController.class).findById(id)).withSelfRel(),
                linkTo(methodOn(DepartmentController.class).findById(employee.getId())).withRel("department"));
    }

    @GetMapping("/{id}/department")
    public Department findEmployeeDepartment(@PathVariable("id") Long id) {
        logger.debug("Handling find department by employee id: id={}", id);
        Employee employee = employeeService.findById(id);
        return employee.getDepartment();
    }

    @GetMapping("/department/{id}")
    public Page<Employee> findEmployeesByDepartmentId(
            @PathVariable("id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        logger.debug("Handling find employees by department id: id={}", id);
        Page<Employee> employees = employeeService.findEmployeesByDepartmentId(id, page, size);
        for(Employee employee : employees.getContent()) {
            employee.add(linkTo(methodOn(EmployeeController.class).findEmployeeDepartment(id)).withRel("department"));
        }
        return employees;
    }

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        logger.debug("Handling add: {}", employee);
        return employeeService.add(employee);
    }

    @PutMapping
    public Employee update(@RequestBody Employee employee) {
        logger.debug("Handling update: {}", employee);
        employeeService.update(employee);
        return employee;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        logger.debug("Handling delete: id={}", id);
        employeeService.delete(id);
    }

    @GetMapping("/count")
    public long count() {
        logger.debug("Handling count");
        return employeeService.count();
    }

}
