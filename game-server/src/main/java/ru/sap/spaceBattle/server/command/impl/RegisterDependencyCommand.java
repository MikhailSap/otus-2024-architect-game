package ru.sap.spaceBattle.server.command.impl;

import ru.sap.spaceBattle.server.ioc.IoC;
import ru.sap.spaceBattle.server.command.ICommand;

import java.util.Map;
import java.util.function.Function;

/**
 * Команда регистрации новой зависимоси
 */
public class RegisterDependencyCommand implements ICommand {
    private final String dependency;
    private final Function<Object[], Object> dependencyResolverStrategy;

    public RegisterDependencyCommand(String dependency,
                                     Function<Object[], Object> dependencyResolverStrategy) {
        this.dependency = dependency;
        this.dependencyResolverStrategy = dependencyResolverStrategy;
    }

    @Override
    public void execute() {
        Map<String, Function<Object[], Object>> currentScope = IoC.resolve("IoC.Scope.Current");
        currentScope.put(dependency, dependencyResolverStrategy);
    }
}
