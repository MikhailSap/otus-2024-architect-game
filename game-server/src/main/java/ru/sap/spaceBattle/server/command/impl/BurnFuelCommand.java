package ru.sap.spaceBattle.server.command.impl;

import ru.sap.spaceBattle.server.Refuelable;
import ru.sap.spaceBattle.server.command.ICommand;

public class BurnFuelCommand implements ICommand {
    private final Refuelable refuelable;

    public BurnFuelCommand(Refuelable refuelable) {
        this.refuelable = refuelable;
    }

    @Override
    public void execute() {
        refuelable.burnFuel(refuelable.getConsumption());
    }
}
