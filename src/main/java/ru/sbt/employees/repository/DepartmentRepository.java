package ru.sbt.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbt.employees.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
