package ru.sap.spaceBattle.server.exception;

public class CommandException extends RuntimeException {
    public CommandException(String message) {
        super(message);
    }
}
