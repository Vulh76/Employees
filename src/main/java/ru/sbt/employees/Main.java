package ru.sbt.employees;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.employees.config.HibernateConfig;
import ru.sbt.employees.model.Employee;
import ru.sbt.employees.service.EmployeeServiceImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(HibernateConfig.class);
        EmployeeServiceImpl employeeService = applicationContext.getBean(EmployeeServiceImpl.class);

        employeeService.addEmployee(new Employee("Андрей", "Иванов", 27, "Отдел резработки"));
        employeeService.addEmployee(new Employee("Илья", "Думаченко", 34, "Отдел резработки"));
        employeeService.addEmployee(new Employee("Наталья", "Синичкина", 23, "Отдел резработки"));
        employeeService.addEmployee(new Employee("Владимир", "Орлов", 43, "Отдел тестирования"));
        employeeService.addEmployee(new Employee("Елизавета", "Котова", 39, "Отдел тестирования"));
        employeeService.addEmployee(new Employee("Оксана", "Саратова", 22, "Отдел тестирования"));
        employeeService.addEmployee(new Employee("Александр", "Вертушкин", 29, "Отдел внедрения"));
        employeeService.addEmployee(new Employee("Виктория", "Соколова", 31, "Отдел внедрения"));

        System.out.println(employeeService.getAllEmployees("id", false));
    }
}
