package ru.sbt.employees.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.sbt.employees.model.Employee;
import ru.sbt.employees.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public List<Employee> findAll()
    {
        logger.debug("getAll");
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Employee> findPage(int page, int size) {
        logger.debug("getPage. page: {}, count: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Employee findById(long id) {
        logger.debug("getById. id: {}", id);
        return employeeRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Employee add(Employee employee) {
        logger.debug("add");
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee update(Employee employee) {
        logger.debug("update");
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void delete(Employee employee) {
        logger.debug("delete");
        employeeRepository.delete(employee);
    }

    @Override
    @Transactional
    public void delete(long id) {
        logger.debug("delete");
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public long count() {
        logger.debug("getCount");
        return employeeRepository.count();
    }
}
