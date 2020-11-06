package ru.sbt.employees.payload;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ru.sbt.employees.controller.DepartmentController;
import ru.sbt.employees.model.Department;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DepartmentModelAssembler implements RepresentationModelAssembler<Department, EntityModel<Department>> {
    @Override
    public EntityModel<Department> toModel(Department entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(DepartmentController.class).findById(entity.getId())).withSelfRel(),
                linkTo(methodOn(DepartmentController.class).findDepartmentEmployees(entity.getId())).withRel("employees"));

    }

    @Override
    public CollectionModel<EntityModel<Department>> toCollectionModel(Iterable<? extends Department> entities) {
        List<EntityModel<Department>> departments = StreamSupport.stream(entities.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(departments, linkTo(methodOn(DepartmentController.class).findAll()).withSelfRel());
    }
}
