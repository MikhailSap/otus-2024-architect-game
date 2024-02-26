package ru.sap.spaceBattle.server.command.impl;

import ru.sap.spaceBattle.server.command.ICommand;

import java.util.Map;
import java.util.function.Function;

/**
 * Команда установки текущего скоупа
 */
public class SetCurrentScopeCommand implements ICommand {
    private final Object scope;

    public SetCurrentScopeCommand(Object scope) {
        this.scope = scope;
    }

    @Override
    public void execute() {
        InitCommand.currentScopes.set((Map<String, Function<Object[], Object>>)scope);
    }
}
