package ru.sbt.employees.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sbt.employees.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findEmployeesByDepartmentId(Long id, Pageable pageable);

    @Query("from Employee e where e.age > ?1")
    Page<Employee> queryByHigherAge(int age, Pageable pageable);

    @Query("SELECT name, EXTRACT(year FROM age(current_date, date_of_birth))::int as age FROM public.employees")
    Page<Employee> queryByHigherAge2(int age1, Pageable pageable);
    //@Query("SELECT name, date_part('year',age(date_of_birth)),* FROM public.employees")
    //Page<Employee> queryByHigherAge2(int age1, Pageable pageable);
}

