package ru.sap.spaceBattle.server.exception.handlerImpl;

import lombok.extern.slf4j.Slf4j;
import ru.sap.spaceBattle.server.command.ICommand;
import ru.sap.spaceBattle.server.command.impl.OneRepeatCommand;
import ru.sap.spaceBattle.server.exception.ExceptionHandler;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class TwoRepeatCommandExceptionHandler implements ExceptionHandler {
    private final BlockingQueue<ICommand> commandQueue;

    public TwoRepeatCommandExceptionHandler(BlockingQueue<ICommand> commandQueue) {
        this.commandQueue = commandQueue;
    }

    @Override
    public void handle(ICommand command, Exception exception) {
        log.trace("Ошибка при выполнении команды: {}, пробуем повторить еще раз", command.getName(), exception);
        OneRepeatCommand oneRepeatCommand = new OneRepeatCommand(command);
        commandQueue.add(oneRepeatCommand);
    }
}
