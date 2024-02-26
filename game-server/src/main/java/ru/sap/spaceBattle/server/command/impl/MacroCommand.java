package ru.sap.spaceBattle.server.command.impl;

import lombok.extern.slf4j.Slf4j;
import ru.sap.spaceBattle.server.command.ICommand;
import ru.sap.spaceBattle.server.exception.CommandException;

import java.util.List;

@Slf4j
public class MacroCommand implements ICommand {
    private final List<ICommand> commandList;

    public MacroCommand(ICommand... commands) {
        this.commandList = List.of(commands);
    }

    @Override
    public void execute() {
        ICommand command = null;
        try {
            for (ICommand currentCommand : commandList) {
                command = currentCommand;
                command.execute();
            }
        } catch (CommandException e) {
            log.trace("Команда {} не может быть выполнена", command.getName(), e);
            throw new CommandException(e.getMessage());
        }
    }
}
