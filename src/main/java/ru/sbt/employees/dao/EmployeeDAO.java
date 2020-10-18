package ru.sbt.employees.dao;

import ru.sbt.employees.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    <T> List<T> getAll(Class<T> clazz, String sortColumn, boolean desc);
    <T> List<T> getPage(Class<T> clazz, int page, int itemPerPage, String sortColumn, boolean desc);
    <T> T getById(Class<T> clazz, long id);
    <T> long add(T entity);
    <T> void delete(T entity);
    <T> void update(T entity);
    <T> int count(Class<T> clazz);
}
