package ru.sbt.employees.exception;

public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2349240020206399614L;

    public EntityNotFoundException(Class<?> clazz, long id) {
        super(String.format("Entity Not Found. Entity: %s, id: %d", clazz.getSimpleName(), id));
    }
}
