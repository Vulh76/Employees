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
import ru.sbt.employees.exception.EntityNotFoundException;
import ru.sbt.employees.model.Department;
import ru.sbt.employees.model.Employee;
import ru.sbt.employees.service.DepartmentService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /*@GetMapping("/all")
    public List<Department> findAll() {
        logger.debug("Handling find all");
        return departmentService.findAll();
    }*/

    @GetMapping
    public Page<Department> findPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        logger.debug("Handling find page: page={}, size={}", page, size);

        Page<Department> departments = departmentService.findPage(page, size);

        for (Department department : departments.getContent()) {
            department.add(linkTo(methodOn(DepartmentController.class)
                    .findById(department.getId())).withSelfRel());
            department.add(linkTo(methodOn(DepartmentController.class)
                    .findDepartmentEmployees(department.getId())).withRel("employees"));
        }

        return departments;
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable("id") Long id) throws EntityNotFoundException {
        logger.debug("Handling find by id: id={}", id);

        Department department = departmentService.findById(id);

        department.add(linkTo(methodOn(DepartmentController.class)
                .findById(id)).withSelfRel());
        department.add(linkTo(methodOn(DepartmentController.class)
                .findDepartmentEmployees(department.getId())).withRel("employees"));

        return department;
    }

    @GetMapping("/{id}/employee")
    public List<Employee> findDepartmentEmployees(@PathVariable("id") Long id) throws EntityNotFoundException {
        logger.debug("Handling find employees by department id: id={}", id);
        Department department = departmentService.findById(id);
        return department.getEmployees();
    }

    @PostMapping
    public Department add(@RequestBody Department department) {
        logger.debug("Handling add: {}", department);
        departmentService.add(department);
        return department;
    }

    @PutMapping
    public Department update(@RequestBody Department department) {
        logger.debug("Handling update: {}", department);
        return departmentService.update(department);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        logger.debug("Handling delete: id={}", id);
        departmentService.delete(id);
    }

    @GetMapping("/count")
    public long count() {
        logger.debug("Handling count");
        return departmentService.count();
    }
}
