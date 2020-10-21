package ru.sbt.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sbt.employees.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("from Employee e where e.age > ?1")
    Employee queryByHigherAge(int age);

    List<Employee> findByNameLike(String template);
}
