package ru.bellintegrator.practice.exceptions.exceptions;

public class DictionaryException extends RuntimeException {

    public DictionaryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DictionaryException(String message) {
        super(message);
    }
}
