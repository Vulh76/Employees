package ru.sbt.employees.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.sbt.employees.dao.EmployeeDAO;
import ru.sbt.employees.dao.EmployeeDAOImpl;
import ru.sbt.employees.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

public class EmployeeServiceTest {

    private EmployeeDAO employeeDAO;
    private EmployeeServiceImpl employeeService;

    @Before
    public void setUp() throws Exception {
        employeeDAO = Mockito.mock(EmployeeDAOImpl.class);
        employeeService = new EmployeeServiceImpl(employeeDAO);

        HashMap<Long, Employee> longEmployeeHashMap = new HashMap<>();
        AtomicLong atomicLong = new AtomicLong();

        Mockito.when(employeeDAO.add(Mockito.any())).thenAnswer(i -> {
            long id = atomicLong.incrementAndGet();
            Employee argument = i.getArgument(0, Employee.class);
            argument.setId(id);
            longEmployeeHashMap.put(id, argument);
            return id;
        });

        Mockito.when(employeeDAO.getById(Mockito.anyLong())).thenAnswer(i ->
                longEmployeeHashMap.get(i.getArgument(0, Long.class))
        );
    }

    @Test
    public void addEmployeeTest1() {
        Employee employee = new Employee();
        employeeService.add(employee);

        Employee car1 = employeeService.getById(employee.getId());
        assertNotNull(car1);
    }

    @Test
    public void addEmployeeTest2() {
        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        Mockito.doReturn(1L).when(employeeDAO).add(captor.capture());
        //Mockito.when(carDAO.add(captor.capture())).thenReturn(1L);

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        employeeService.add(employee1);
        employeeService.add(employee2);

        List<Employee> allValues = captor.getAllValues();
        assertEquals(2, allValues.size());
        assertEquals(employee1, allValues.get(0));
        assertEquals(employee2, allValues.get(1));
    }


}