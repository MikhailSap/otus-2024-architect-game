package ru.sap.spaceBattle.server.ioc;

import ru.sap.spaceBattle.server.command.ICommand;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Команда обновления стратегии разрешения зависимостей
 */
public class UpdateIocResolveDependencyStrategyCommand implements ICommand {
    private final Function<
            BiFunction<String, Object[], Object>,
            BiFunction<String, Object[], Object>> updateIoCStrategy;

    public UpdateIocResolveDependencyStrategyCommand(Function<
            BiFunction<String, Object[], Object>,
            BiFunction<String, Object[], Object>> updateIoCStrategy) {
        this.updateIoCStrategy = updateIoCStrategy;
    }

    @Override
    public void execute() {
        IoC.strategy = updateIoCStrategy.apply(IoC.strategy);
    }
}
