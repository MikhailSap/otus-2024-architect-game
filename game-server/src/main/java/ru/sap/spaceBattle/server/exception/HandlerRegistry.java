package ru.sap.spaceBattle.server.exception;

import ru.sap.spaceBattle.server.command.ICommand;

import java.util.HashMap;
import java.util.Map;

public class HandlerRegistry {
    private final Map<Class<? extends ICommand>,
            Map<Class<? extends Exception>, ExceptionHandler>> handlerRegistry = new HashMap<>();
    private final Map<Class<? extends ICommand>, ExceptionHandler> repeatHandlerRegistry = new HashMap<>();

    public void registerHandler(Class<? extends ICommand> commandType,
                                Class<? extends Exception> exceptionType, ExceptionHandler handler) {
        if (handlerRegistry.containsKey(commandType)) {
            handlerRegistry.get(commandType).put(exceptionType, handler);
        } else {
            handlerRegistry.put(commandType, new HashMap<>(){{
                put(exceptionType, handler);
            }});
        }
    }

    public void registerHandler(Class<? extends ICommand> commandType, ExceptionHandler handler) {
        repeatHandlerRegistry.put(commandType, handler);
    }

    public ExceptionHandler getHandler(Class<? extends ICommand> commandType,
                                       Class<? extends Exception> exceptionType) {
        if (handlerRegistry.containsKey(commandType)) {
            return handlerRegistry.get(commandType).get(exceptionType);
        } else {
            return repeatHandlerRegistry.get(commandType);
        }
    }

}
