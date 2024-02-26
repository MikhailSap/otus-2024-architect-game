package ru.sap.spaceBattle.server.command.impl;

import ru.sap.spaceBattle.server.ioc.DependencyResolver;
import ru.sap.spaceBattle.server.ioc.IoC;
import ru.sap.spaceBattle.server.command.ICommand;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Команда инициализации скоупа
 */
public class InitCommand implements ICommand {
    static ThreadLocal<Map<String, Function<Object[], Object>>> currentScopes = ThreadLocal.withInitial(
            () -> {
                Map<String, Function<Object[], Object>> initScope = new HashMap<>();
                initScope.put(
                        "IoC.Scope.Current.Set",
                        args -> new SetCurrentScopeCommand(args[0])
                );

                initScope.put(
                        "IoC.Scope.Current.Clear",
                        args -> new ClearCurrentScopeCommand()
                );

                initScope.put(
                        "IoC.Scope.Current",
                        args -> initScope
                );

                initScope.put(
                        "IoC.Scope.Parent",
                        args -> null
                );

                initScope.put(
                        "IoC.Scope.Create.Empty",
                        args -> new HashMap<String, Function<Object[], Object>>()
                );

                initScope.put(
                        "IoC.Scope.Create",
                        args -> {
                            Map<String, Function<Object[], Object>> newScope = IoC.resolve("IoC.Scope.Create.Empty");

                            if (args.length > 0) {
                                var parentScope = args[0];
                                newScope.put("IoC.Scope.Parent", parentArgs -> parentScope);
                            } else {
                                var parentScope = IoC.resolve("IoC.Scope.Current");
                                newScope.put("IoC.Scope.Parent", parentArgs -> parentScope);
                            }
                            return newScope;
                        }
                );

                initScope.put(
                        "IoC.Register",
                        args -> new RegisterDependencyCommand(
                                (String) args[0],
                                (Function<Object[], Object>) args[1]
                        )
                );
                return initScope;
            }
    );

    private final AtomicBoolean isInit = new AtomicBoolean(false);

    @Override
    public void execute() {
        if (isInit.get()) {
            return;
        }

        synchronized (this) {
            var dependencyResolver = new DependencyResolver(currentScopes::get);
            Function<
                    BiFunction<String, Object[], Object>,
                    BiFunction<String, Object[], Object>> updateResolverDependencyStrategy =
                    (oldStrategy) -> dependencyResolver::resolve;
            ((ICommand) IoC.resolve(
                    "Update Ioc Resolve Dependency Strategy",
                    updateResolverDependencyStrategy)
            ).execute();
            isInit.set(true);
        }
    }
}
