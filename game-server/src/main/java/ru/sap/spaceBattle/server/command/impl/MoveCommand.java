package ru.sap.spaceBattle.server.command.impl;

import ru.sap.spaceBattle.server.Movable;
import ru.sap.spaceBattle.server.command.ICommand;

public class MoveCommand implements ICommand {
    private final Movable movable;

    public MoveCommand(Movable movable) {
        this.movable = movable;
    }

    public void execute() {
        movable.setPosition(movable.getPosition().plus(movable.getVelocity()));
    }
}
