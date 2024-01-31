package ru.sap.spaceBattle.server.exception;

public class UnavailableStateException extends RuntimeException {
    public UnavailableStateException(String message) {
        super(message);
    }
}
