package ru.sap.spaceBattle.server.command.impl;

import ru.sap.spaceBattle.server.command.ICommand;

/**
 * Команда очистки скоупа
 */
public class ClearCurrentScopeCommand implements ICommand {
    @Override
    public void execute() {
        InitCommand.currentScopes.set(null);
    }
}
