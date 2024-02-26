package ru.sap.spaceBattle.server;

import ru.sap.spaceBattle.server.command.ICommand;
import ru.sap.spaceBattle.server.exception.ExceptionHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandExecutor {
    private final AtomicBoolean isActive = new AtomicBoolean(true);
    private final BlockingQueue<ICommand> commandQueue;
    private final ExceptionHandler exceptionHandler;

    public CommandExecutor(BlockingQueue<ICommand> commandQueue, ExceptionHandler exceptionHandler) {
        this.commandQueue = commandQueue;
        this.exceptionHandler = exceptionHandler;
    }

    public void process() {
        while (isActive.get()) {
            ICommand currentCommand = null;
            try {
                currentCommand = commandQueue.take();
                currentCommand.execute();
            } catch (Exception e) {
                exceptionHandler.handle(currentCommand, e);
            }
        }
    }

    public void stopProcess() {
        isActive.set(false);
    }
}
