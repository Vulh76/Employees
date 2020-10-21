package ru.sbt.employees.dao;

import ru.sbt.employees.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    <T> List<T> getAll(Class<T> clazz);
    <T> List<T> getPage(Class<T> clazz, int page, int count);
    <T> T getById(Class<T> clazz, long id);
    <T> long add(T entity);
    <T> void delete(Class<T> clazz, long id);
    <T> void delete(T entity);
    <T> void update(T entity);
    <T> int count(Class<T> clazz);
}
