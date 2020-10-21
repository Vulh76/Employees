package ru.sbt.employees.exception;

public class EntityNotFoundException extends RuntimeException {
    private final Class<?>  clazz;
    private final long id;

    public EntityNotFoundException(Class<?> clazz, long id) {
        this.clazz = clazz;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("Entity Not Found. Class: %s, id: %s", clazz.getSimpleName(), id);
    }
}
