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


    }
}
