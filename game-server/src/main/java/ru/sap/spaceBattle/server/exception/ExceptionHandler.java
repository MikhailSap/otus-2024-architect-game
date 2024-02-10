package ru.sap.spaceBattle.server.exception;

import ru.sap.spaceBattle.server.command.ICommand;

public interface ExceptionHandler {
    void handle(ICommand command, Exception exception);
}
