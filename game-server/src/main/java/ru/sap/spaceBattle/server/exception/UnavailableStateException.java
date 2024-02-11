package ru.sap.spaceBattle.server.exception;

public class UnavailableStateException extends CommandException {
    public UnavailableStateException(String message) {
        super(message);
    }
}
