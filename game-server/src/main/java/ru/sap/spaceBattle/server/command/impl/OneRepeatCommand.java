package ru.sap.spaceBattle.server.command.impl;

import ru.sap.spaceBattle.server.command.ICommand;

public class OneRepeatCommand implements ICommand {
    private final ICommand command;

    public OneRepeatCommand(ICommand command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}
