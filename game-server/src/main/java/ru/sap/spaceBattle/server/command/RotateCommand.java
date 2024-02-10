package ru.sap.spaceBattle.server.command;

import ru.sap.spaceBattle.server.Rotatable;

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
