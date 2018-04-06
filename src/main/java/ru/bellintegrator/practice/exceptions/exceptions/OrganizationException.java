package ru.bellintegrator.practice.exceptions.exceptions;

public class OrganizationException extends RuntimeException {

    public OrganizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrganizationException(String message) {
        super(message);
    }
}
