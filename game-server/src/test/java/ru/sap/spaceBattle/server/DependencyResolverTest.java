package ru.sap.spaceBattle.server;

import org.junit.Test;
import ru.sap.spaceBattle.server.ioc.DependencyResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.junit.Assert.*;

public class DependencyResolverTest {

    @Test
    public void resolveDependencyWithNoParentTest() {
        Map<String, Function<Object[], Object>> scope = new HashMap<>();
        scope.put("dependency", args -> "success");
        DependencyResolver resolver = new DependencyResolver(() -> scope);
        assertEquals("success", resolver.resolve("dependency", new Object[0]));
    }

    @Test
    public void resolveDependencyWithParentTest() {
        Map<String, Function<Object[], Object>> parentScope = new HashMap<>();
        parentScope.put("dependency", args -> "success");
        Map<String, Function<Object[], Object>> scope = new HashMap<>();
        scope.put("IoC.Scope.Parent", args -> parentScope);
        DependencyResolver resolver = new DependencyResolver(() -> scope);
        assertEquals("success", resolver.resolve("dependency", new Object[0]));
    }
}
