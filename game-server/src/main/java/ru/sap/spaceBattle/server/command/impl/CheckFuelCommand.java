package ru.sap.spaceBattle.server.command.impl;

import ru.sap.spaceBattle.server.Refuelable;
import ru.sap.spaceBattle.server.command.ICommand;
import ru.sap.spaceBattle.server.exception.CommandException;

public class CheckFuelCommand implements ICommand {
    private final Refuelable refuelable;

    public CheckFuelCommand(Refuelable refuelable) {
        this.refuelable = refuelable;
    }

    @Override
    public void execute() {
        if (refuelable.getFuelLevel() < refuelable.getConsumption()) {
            throw new CommandException("Недостаточно топлива для движения");
        }
    }
}
