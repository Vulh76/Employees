package ru.sbt.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.sbt.employees.exception.EntityNotFoundException;
import ru.sbt.employees.model.Employee;
import ru.sbt.employees.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Employee> findPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Page<Employee> findEmployeesByDepartmentId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findEmployeesByDepartmentId(id, pageable);
    }

    @Override
    @Transactional
    public Employee findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent())
            throw new EntityNotFoundException(Employee.class, id);
        return employee.get();
    }

    @Override
    @Transactional
    public Employee add(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public long count() {
        return employeeRepository.count();
    }
}
