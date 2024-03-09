package ru.sap.spaceBattle.server.ioc;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class DependencyResolver implements IDependencyResolver {
    private final Supplier<Map<String, Function<Object[], Object>>> dependencies;

    public DependencyResolver(Supplier<Map<String, Function<Object[], Object>>> scope) {
        this.dependencies = scope;
    }

    @Override
    public Object resolve(String dependency, Object[] args) {
        var dependencies = this.dependencies.get();

        if (dependencies == null) {
            return null;
        }

        while (true) {
            Function<Object[], Object> dependencyResolverStrategy = null;
            if ((dependencyResolverStrategy = dependencies.get(dependency)) != null) {
                return dependencyResolverStrategy.apply(args);
            } else {
                dependencies = (Map<String, Function<Object[], Object>>)dependencies
                        .get("IoC.Scope.Parent").apply(args);
            }
        }
    }
}
