package ru.sbt.employees.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
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
import ru.sbt.employees.payload.DepartmentModelAssembler;
import ru.sbt.employees.payload.EmployeeModelAssembler;
import ru.sbt.employees.service.DepartmentService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/department", produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
public class DepartmentController {

    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_PAGE_SIZE = 5;


    private final DepartmentService departmentService;
    private final DepartmentModelAssembler departmentModelAssembler;
    private final EmployeeModelAssembler employeeModelAssembler;

    private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    public DepartmentController(DepartmentService departmentService, DepartmentModelAssembler departmentModelAssembler, EmployeeModelAssembler employeeModelAssembler) {
        this.departmentService = departmentService;
        this.departmentModelAssembler = departmentModelAssembler;
        this.employeeModelAssembler = employeeModelAssembler;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Department>> findAll() {
        logger.debug("Handling find all");
        return departmentModelAssembler.toCollectionModel(departmentService.findAll());
    }

    @GetMapping
    public PagedModel<EntityModel<Department>> findPage(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageRequest,
            PagedResourcesAssembler<Department> pagedResourcesAssembler) {

        logger.debug("Handling find page: page={}, size={}",pageRequest.getPageNumber(), pageRequest.getPageSize());

        Page<Department> departments = departmentService.findPage(pageRequest);
        for(Department department : departments.getContent()) {
            department.add(linkTo(methodOn(DepartmentController.class).findPage(pageRequest, pagedResourcesAssembler)).withSelfRel());
            department.add(linkTo(methodOn(DepartmentController.class).findDepartmentEmployees(department.getId())).withRel("employees"));
        }
        PagedModel.of(departments, linkTo(methodOn(DepartmentController.class).findPage(pageRequest, pagedResourcesAssembler)).withSelfRel());
        return pagedResourcesAssembler.toModel(departments);
    }

    @GetMapping("/{id}")
    public EntityModel<Department> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
        logger.debug("Handling find by id: id={}", id);
        return departmentModelAssembler.toModel(departmentService.findById(id));
    }

    @GetMapping("/{id}/employee")
    public CollectionModel<EntityModel<Employee>> findDepartmentEmployees(@PathVariable("id") Long id) throws EntityNotFoundException {
        logger.debug("Handling find employees by department id: id={}", id);
        Department department = departmentService.findById(id);
        return employeeModelAssembler.toCollectionModel(department.getEmployees());
    }

    @PostMapping
    public EntityModel<Department> add(@RequestBody Department department) {
        logger.debug("Handling add: {}", department);
        departmentService.add(department);
        return departmentModelAssembler.toModel(department);
    }

    @PutMapping
    public EntityModel<Department> update(@RequestBody Department department) {
        logger.debug("Handling update: {}", department);
        return departmentModelAssembler.toModel(departmentService.update(department));
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
