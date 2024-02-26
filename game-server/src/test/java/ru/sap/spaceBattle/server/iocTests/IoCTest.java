package ru.sap.spaceBattle.server.iocTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ru.sap.spaceBattle.server.command.impl.ClearCurrentScopeCommand;
import ru.sap.spaceBattle.server.command.impl.InitCommand;
import ru.sap.spaceBattle.server.command.impl.RegisterDependencyCommand;
import ru.sap.spaceBattle.server.command.impl.SetCurrentScopeCommand;
import ru.sap.spaceBattle.server.ioc.IoC;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static org.junit.Assert.*;

public class IoCTest {
    @BeforeClass
    public static void init() {
        InitCommand command = new InitCommand();
        command.execute();
    }

    @Test
    public void initCommandTestPositive() {
        Object setCurrentScopeCommand = IoC.resolve("IoC.Scope.Current.Set", Collections.emptyMap());
        assertTrue(setCurrentScopeCommand instanceof SetCurrentScopeCommand);
        Object clearCurrentScopeCommand = IoC.resolve("IoC.Scope.Current.Clear");
        assertTrue(clearCurrentScopeCommand instanceof ClearCurrentScopeCommand);
        Object currentScope = IoC.resolve("IoC.Scope.Current");
        assertNotNull(currentScope);
        Object parentScope = IoC.resolve("IoC.Scope.Parent");
        assertNull(parentScope);
        Object emptyScope = IoC.resolve("IoC.Scope.Create.Empty");
        assertNotNull(emptyScope);
        assertTrue(((Map<String, Function<Object[], Object>>)emptyScope).isEmpty());
        Object newScope = IoC.resolve("IoC.Scope.Create", currentScope);
        assertEquals(
                currentScope,
                ((Map<String, Function<Object[], Object>>)newScope)
                        .get("IoC.Scope.Parent").apply(new Object[0])
        );
        Object registerDependencyCommand = IoC.resolve(
                "IoC.Register",
                new Object[]{"new", Mockito.mock(Function.class)}
        );
        assertTrue(registerDependencyCommand instanceof RegisterDependencyCommand);
    }
    @Test
    public void resolveValidDependencyTest() {
        RegisterDependencyCommand command = new RegisterDependencyCommand("dependency", args ->  "dependency " + args[0]);
        command.execute();
        String result = IoC.resolve("dependency", "success");

        assertEquals("dependency success", result);
    }

    @Test
    public void multiThreadingScopeTest() throws InterruptedException {

        ConcurrentHashMap<String, String> resultMap = new ConcurrentHashMap<>();
        Runnable runnable = () -> {
            Function<Object[], Object> strategy = args -> Thread.currentThread().getName();
            RegisterDependencyCommand registerDependencyCommand = IoC.resolve(
                    "IoC.Register",
                    new Object[]{"dependency", strategy}
            );
            registerDependencyCommand.execute();
            resultMap.put(Thread.currentThread().getName(), IoC.resolve("dependency"));
        };

        Thread threadOne = new Thread(runnable, "One");
        Thread threadTwo = new Thread(runnable, "Two");

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        assertEquals(2, resultMap.size());
        resultMap.forEach(Assert::assertEquals);
    }

    @Test
    public void registerDependencyPositiveTest() {
        RegisterDependencyCommand command = new RegisterDependencyCommand("dependency", args -> "success");
        command.execute();
        assertEquals("success", IoC.resolve("dependency"));
    }

    @Test
    public void setAndCleanScopeTest() {
        Object currentScope = IoC.resolve("IoC.Scope.Current");
        assertNotNull(currentScope);
        ClearCurrentScopeCommand command = new ClearCurrentScopeCommand();
        command.execute();
        assertNull(IoC.resolve("IoC.Scope.Current"));
        SetCurrentScopeCommand setCommand = new SetCurrentScopeCommand(currentScope);
        setCommand.execute();
        assertEquals(currentScope, IoC.resolve("IoC.Scope.Current"));
    }
}
