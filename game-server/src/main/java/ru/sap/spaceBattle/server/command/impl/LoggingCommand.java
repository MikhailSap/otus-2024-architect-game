package ru.sap.spaceBattle.server.command.impl;

import lombok.extern.slf4j.Slf4j;
import ru.sap.spaceBattle.server.command.ICommand;

@Slf4j
public class LoggingCommand implements ICommand {
    private final ICommand command;
    private final Exception exception;

    public LoggingCommand(ICommand command, Exception exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        log.info("Ошибка при выполнении команды: {}", command.getName(), exception);
    }
}
