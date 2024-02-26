package ru.sap.spaceBattle.server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.sap.spaceBattle.server.command.ICommand;
import ru.sap.spaceBattle.server.command.impl.MoveCommand;
import ru.sap.spaceBattle.server.command.impl.OneRepeatCommand;
import ru.sap.spaceBattle.server.command.impl.TwoRepeatCommand;
import ru.sap.spaceBattle.server.exception.ChangePositionException;
import ru.sap.spaceBattle.server.exception.ExceptionHandler;
import ru.sap.spaceBattle.server.exception.HandlerRegistry;
import ru.sap.spaceBattle.server.exception.UnavailableStateException;
import ru.sap.spaceBattle.server.exception.handlerImpl.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;

public class ExceptionHandlerTest {
    private BlockingQueue<ICommand> commandQueue;
    private CommandExecutor commandExecutor;

    @Before
    public void init() {
        commandQueue = Mockito.spy(new ArrayBlockingQueue<>(10));
        HandlerRegistry handlerRegistry = new HandlerRegistry();
        handlerRegistry.registerHandler(MoveCommand.class, UnavailableStateException.class, new UnavailableStateExceptionHandler(commandQueue));
        handlerRegistry.registerHandler(MoveCommand.class, ChangePositionException.class, new ChangePositionExceptionHandler(commandQueue));
        handlerRegistry.registerHandler(TwoRepeatCommand.class, new TwoRepeatCommandExceptionHandler(commandQueue));
        handlerRegistry.registerHandler(OneRepeatCommand.class, new OneRepeatCommandExceptionHandler(commandQueue));
        ExceptionHandler commandExceptionHandler = new CommandExceptionHandler(handlerRegistry);
        commandExecutor = new CommandExecutor(commandQueue, commandExceptionHandler);
        CompletableFuture.runAsync(() -> commandExecutor.process());
    }

    @After
    public void destroy() {
        commandExecutor.stopProcess();
    }

    @Test
    public void unavailableStateExceptionTest() throws InterruptedException { // двойной повтор команды
        MovableObject movableObject = new MovableObject(null, null); // бросит исключение UnavailableStateException
        MoveCommand moveCommand = Mockito.spy(new MoveCommand(movableObject));
        commandQueue.add(moveCommand);
        Thread.sleep(1000);
        Mockito.verify(commandQueue, Mockito.times(4))
                .add(Mockito.any(ICommand.class));
        Mockito.verify(commandQueue, Mockito.times(5))
                .take();
    }

    @Test
    public void changePositionExceptionTest() throws InterruptedException { // повтор команды
        Vector mockVector = Mockito.mock(Vector.class);
        Mockito.when(mockVector.plus(Mockito.any(Vector.class))).thenReturn(null);
        Movable movableObject = new MovableObject( // бросит исключение ChangePositionException
                mockVector,
                new Vector(-7, 3)
        );
        MoveCommand moveCommand = Mockito.spy(new MoveCommand(movableObject));
        commandQueue.add(moveCommand);
        Thread.sleep(1000);
        Mockito.verify(commandQueue, Mockito.times(3))
                .add(Mockito.any(ICommand.class));
        Mockito.verify(commandQueue, Mockito.times(4))
                .take();
    }
}
