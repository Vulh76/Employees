package ru.sbt.employees.exception;

public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2349240020206399614L;

    private final Class<?>  clazz;
    private final long id;

    public EntityNotFoundException(Class<?> clazz, long id) {
        this.clazz = clazz;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("Entity Not Found. Entity: %s, id: %s", clazz.getSimpleName(), id);
    }
}
