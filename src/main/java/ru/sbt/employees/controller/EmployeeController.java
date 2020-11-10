package ru.sbt.employees.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.employees.model.Department;
import ru.sbt.employees.model.Employee;
import ru.sbt.employees.payload.DepartmentModelAssembler;
import ru.sbt.employees.payload.EmployeeModelAssembler;
import ru.sbt.employees.service.EmployeeService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/employee", produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
public class EmployeeController {

    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_PAGE_SIZE = 10;

    private final EmployeeService employeeService;
    private final EmployeeModelAssembler employeeModelAssembler;
    private final DepartmentModelAssembler departmentModelAssembler;

    private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeModelAssembler employeeModelAssembler, DepartmentModelAssembler departmentModelAssembler) {
        this.employeeService = employeeService;
        this.employeeModelAssembler = employeeModelAssembler;
        this.departmentModelAssembler = departmentModelAssembler;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Employee>> findAll() {
        logger.debug("Handling find all");
        return employeeModelAssembler.toCollectionModel(employeeService.findAll());
    }

    @GetMapping
    public PagedModel<EntityModel<Employee>> findPage(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageRequest,
            PagedResourcesAssembler<Employee> pagedResourcesAssembler) {

        logger.debug("Handling find page: page={}, size={}", pageRequest.getPageNumber(), pageRequest.getPageSize());

        PagedModel<EntityModel<Employee>> pagedModel = pagedResourcesAssembler.toModel(employeeService.findPage(pageRequest));
        for(EntityModel<Employee> employee : pagedModel.getContent()) {
            employee.add(linkTo(methodOn(EmployeeController.class).findPage(pageRequest, pagedResourcesAssembler)).withSelfRel());
            employee.add(linkTo(methodOn(EmployeeController.class).findEmployeeDepartment(employee.getContent().getId())).withRel("department"));
        }
        return pagedModel;
    }

    @GetMapping("/{id}")
    public EntityModel<Employee> findById(@PathVariable("id") Long id) {
        logger.debug("Handling find by id: id={}", id);
        return employeeModelAssembler.toModel(employeeService.findById(id));
    }

    @GetMapping("/{id}/department")
    public EntityModel<Department> findEmployeeDepartment(@PathVariable("id") Long id) {
        logger.debug("Handling find department by employee id: id={}", id);
        Employee employee = employeeService.findById(id);
        return departmentModelAssembler.toModel(employee.getDepartment());
    }

    @GetMapping("/department/{id}")
    public PagedModel<EntityModel<Employee>> findEmployeesByDepartmentId(
            @PathVariable("id") Long id,
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageRequest,
            PagedResourcesAssembler<Employee> pagedResourcesAssembler) {

        logger.debug("Handling find employees by department id: id={}", id);

        PagedModel<EntityModel<Employee>> pagedModel = pagedResourcesAssembler.toModel(employeeService.findEmployeesByDepartmentId(id,  pageRequest));
        for(EntityModel<Employee> employee : pagedModel.getContent()) {
            employee.add(linkTo(methodOn(EmployeeController.class).findEmployeesByDepartmentId(id, pageRequest, pagedResourcesAssembler)).withSelfRel());
            employee.add(linkTo(methodOn(EmployeeController.class).findEmployeeDepartment(id)).withRel("department"));
        }
        return pagedModel;
    }

    @PostMapping
    public EntityModel<Employee> add(@RequestBody Employee employee) {
        logger.debug("Handling add: {}", employee);
        return employeeModelAssembler.toModel(employeeService.add(employee));
    }

    @PutMapping
    public EntityModel<Employee> update(@RequestBody Employee employee) {
        logger.debug("Handling update: {}", employee);
        employeeService.update(employee);
        return employeeModelAssembler.toModel(employee);
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
