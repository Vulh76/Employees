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

        employeeService.add(new Employee("Андрей", "Иванов", 27, "Отдел резработки"));
        employeeService.add(new Employee("Илья", "Думаченко", 34, "Отдел резработки"));
        employeeService.add(new Employee("Наталья", "Синичкина", 23, "Отдел резработки"));
        employeeService.add(new Employee("Владимир", "Орлов", 43, "Отдел тестирования"));
        employeeService.add(new Employee("Елизавета", "Котова", 39, "Отдел тестирования"));
        employeeService.add(new Employee("Оксана", "Саратова", 22, "Отдел тестирования"));
        employeeService.add(new Employee("Александр", "Вертушкин", 29, "Отдел внедрения"));
        employeeService.add(new Employee("Виктория", "Соколова", 31, "Отдел внедрения"));

        System.out.println(employeeService.getAll("id", false));
    }
}
