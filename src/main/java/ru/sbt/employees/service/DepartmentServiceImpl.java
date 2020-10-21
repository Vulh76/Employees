package ru.sbt.employees.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.sbt.employees.model.Department;
import ru.sbt.employees.repository.DepartmentRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    private final static Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public List<Department> findAll() {
        logger.debug("getAll");
        return departmentRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Department> findPage(int page, int size) {
        logger.debug("getPage. page: {}, count: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return departmentRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Department findById(long id) {
        logger.debug("getById");
        return departmentRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Department add(Department department) {
        logger.debug("add");
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public Department update(Department department) {
        logger.debug("update");
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void delete(Department department) {
        logger.debug("delete");
        departmentRepository.delete(department);
    }

    @Override
    @Transactional
    public void delete(long id) {
        logger.debug("delete");
        departmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public long count() {
        logger.debug("count");
        return departmentRepository.count();
    }

}
