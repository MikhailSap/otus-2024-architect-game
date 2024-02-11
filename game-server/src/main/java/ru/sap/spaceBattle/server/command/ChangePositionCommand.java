package ru.sap.spaceBattle.server.command;

import ru.sap.spaceBattle.server.command.impl.BurnFuelCommand;
import ru.sap.spaceBattle.server.command.impl.CheckFuelCommand;
import ru.sap.spaceBattle.server.command.impl.MacroCommand;
import ru.sap.spaceBattle.server.command.impl.MoveCommand;


public class ChangePositionCommand extends MacroCommand {
    public ChangePositionCommand(CheckFuelCommand checkFuelCommand,
                                 MoveCommand moveCommand,
                                 BurnFuelCommand burnFuelCommand) {
        super(checkFuelCommand, moveCommand, burnFuelCommand);
    }
}
