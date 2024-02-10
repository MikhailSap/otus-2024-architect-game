package ru.sap.spaceBattle.server.exception.handlerImpl;

import ru.sap.spaceBattle.server.command.ICommand;
import ru.sap.spaceBattle.server.exception.ExceptionHandler;
import ru.sap.spaceBattle.server.exception.HandlerRegistry;

public class CommandExceptionHandler implements ExceptionHandler {
    private final HandlerRegistry registry;

    public CommandExceptionHandler(HandlerRegistry registry) {
        this.registry = registry;
    }

    public void handle(ICommand command, Exception exception) {
        registry.getHandler(command.getClass(), exception.getClass())
                .handle(command, exception);
    }
}
