package ru.sap.spaceBattle.server.exception.handlerImpl;

import ru.sap.spaceBattle.server.command.ICommand;
import ru.sap.spaceBattle.server.command.impl.LoggingCommand;
import ru.sap.spaceBattle.server.exception.ExceptionHandler;

import java.util.concurrent.BlockingQueue;

public class OneRepeatCommandExceptionHandler implements ExceptionHandler {
    private final BlockingQueue<ICommand> commandQueue;

    public OneRepeatCommandExceptionHandler(BlockingQueue<ICommand> commandQueue) {
        this.commandQueue = commandQueue;
    }

    @Override
    public void handle(ICommand command, Exception exception) {
        LoggingCommand loggingCommand = new LoggingCommand(command, exception);
        commandQueue.add(loggingCommand);
    }
}
