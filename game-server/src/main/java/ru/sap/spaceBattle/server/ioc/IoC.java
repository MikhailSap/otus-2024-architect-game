package ru.sap.spaceBattle.server.ioc;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Контейнер с зависимостями
 */
public class IoC {
    static BiFunction<String, Object[], Object> strategy =
            (dependency, args) -> {
                if ("Update Ioc Resolve Dependency Strategy".equals(dependency)) {
                    return new UpdateIocResolveDependencyStrategyCommand(
                            (Function<
                                    BiFunction<String, Object[], Object>,
                                    BiFunction<String, Object[], Object>>) args[0]
                    );
                } else {
                    throw new IllegalArgumentException(
                            String.format("Dependency %s not found", dependency)
                    );
                }
            };

    public static <T> T resolve(String key, Object... args) {
        return (T)strategy.apply(key, args);
    }
}
