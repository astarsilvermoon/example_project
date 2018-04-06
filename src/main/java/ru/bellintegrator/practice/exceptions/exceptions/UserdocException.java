package ru.bellintegrator.practice.exceptions.exceptions;

public class UserdocException extends RuntimeException {

    public UserdocException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserdocException(String message) {
        super(message);
    }
}
