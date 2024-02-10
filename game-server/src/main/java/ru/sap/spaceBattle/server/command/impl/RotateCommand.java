package ru.sap.spaceBattle.server.command.impl;

import ru.sap.spaceBattle.server.Rotatable;
import ru.sap.spaceBattle.server.command.ICommand;

public class RotateCommand implements ICommand {
    private final Rotatable rotatable;

    public RotateCommand(Rotatable rotatable) {
        this.rotatable = rotatable;
    }

    @Override
    public void execute() {
        rotatable.getAngle()
                .plus(rotatable.getAngularVelocity());
    }
}
