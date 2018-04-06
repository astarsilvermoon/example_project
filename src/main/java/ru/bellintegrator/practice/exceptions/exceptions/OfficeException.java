package ru.bellintegrator.practice.exceptions.exceptions;

public class OfficeException extends RuntimeException {

    public OfficeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfficeException(String message) {
        super(message);
    }
}
