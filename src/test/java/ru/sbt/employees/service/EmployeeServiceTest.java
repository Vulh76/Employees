package ru.sbt.employees.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.sbt.employees.model.Employee;
import ru.sbt.employees.repository.EmployeeRepository;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

public class EmployeeServiceTest {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @Before
    public void setUp() throws Exception {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);

        // Эмуляция базы
        HashMap<Long, Employee> longEmployeeHashMap = new HashMap<>();
        AtomicLong atomicLong = new AtomicLong();

        // save
        Mockito.when(employeeRepository.save(Mockito.any())).thenAnswer(i -> {
            Employee argument = i.getArgument(0, Employee.class);
            long id = atomicLong.incrementAndGet();
            argument.setId(id);
            longEmployeeHashMap.put(id, argument);
            return argument;
        });

        // findById
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenAnswer(i ->
                Optional.of(longEmployeeHashMap.get(i.getArgument(0, Long.class)))
        );
    }

    @Test
    public void addEmployeeTest1() {
        Employee employee = new Employee("Андрей", "Петров", 25, null);
        employeeService.add(employee);

        Employee actual = employeeService.findById(employee.getId());

        assertNotNull(actual);
    }

    @Test
    public void addEmployeeTest2() {
        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        Mockito.doReturn(null).when(employeeRepository).save(captor.capture());

        Employee employee1 = new Employee("Андрей", "Петров", 25, null);
        Employee employee2 = new Employee("Сергей", "Симёнов", 35, null);
        employeeService.add(employee1);
        employeeService.add(employee2);

        List<Employee> allValues = captor.getAllValues();
        assertEquals(2, allValues.size());
        assertEquals(employee1, allValues.get(0));
        assertEquals(employee2, allValues.get(1));
    }
}