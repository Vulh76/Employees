package ru.sbt.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.sbt.employees.exception.EntityNotFoundException;
import ru.sbt.employees.model.Department;
import ru.sbt.employees.repository.DepartmentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Department> findPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return departmentRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Department findById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.orElseThrow(() -> new EntityNotFoundException(Department.class, id));
    }

    @Override
    @Transactional
    public Department add(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public Department update(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void delete(Department department) {
        departmentRepository.delete(department);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public long count() {
        return departmentRepository.count();
    }
}
