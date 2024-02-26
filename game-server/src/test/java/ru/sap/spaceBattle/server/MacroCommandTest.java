package ru.sap.spaceBattle.server;

import org.junit.Test;
import org.mockito.Mockito;
import ru.sap.spaceBattle.server.command.ChangePositionCommand;
import ru.sap.spaceBattle.server.command.impl.BurnFuelCommand;
import ru.sap.spaceBattle.server.command.impl.CheckFuelCommand;
import ru.sap.spaceBattle.server.command.impl.MoveCommand;
import ru.sap.spaceBattle.server.exception.CommandException;

import static org.junit.Assert.*;

public class MacroCommandTest {

    @Test
    public void executePositiveTest() {
        Refuelable refuelable = Mockito.spy(new RefuelableObject(70, 5));
        Movable movable = Mockito.spy(new MovableObject(
                new Vector(12, 5),
                new Vector(-7, 3))
        );
        CheckFuelCommand checkFuelCommand = Mockito.spy(new CheckFuelCommand(refuelable));
        MoveCommand moveCommand = Mockito.spy(new MoveCommand(movable));
        BurnFuelCommand burnFuelCommand = Mockito.spy(new BurnFuelCommand(refuelable));
        ChangePositionCommand changePositionCommand = new ChangePositionCommand(
                checkFuelCommand,
                moveCommand,
                burnFuelCommand
        );
        changePositionCommand.execute();
        Mockito.verify(refuelable, Mockito.times(1)).getFuelLevel();
        Mockito.verify(refuelable, Mockito.times(2)).getConsumption();
        Mockito.verify(refuelable, Mockito.times(1)).burnFuel(5);
        Mockito.verify(movable, Mockito.times(1)).setPosition(Mockito.any(Vector.class));
        Mockito.verify(movable, Mockito.times(1)).getPosition();
        Mockito.verify(movable, Mockito.times(1)).getVelocity();
        Mockito.verify(checkFuelCommand, Mockito.times(1)).execute();
        Mockito.verify(moveCommand, Mockito.times(1)).execute();
        Mockito.verify(burnFuelCommand, Mockito.times(1)).execute();
    }

    @Test
    public void executeNegativeTest() {
        Refuelable refuelable = Mockito.spy(new RefuelableObject(70, 5));
        Movable movable = Mockito.spy(new MovableObject(
                null,
                new Vector(-7, 3)
        ));
        CheckFuelCommand checkFuelCommand = Mockito.spy(new CheckFuelCommand(refuelable));
        MoveCommand moveCommand = Mockito.spy(new MoveCommand(movable));
        BurnFuelCommand burnFuelCommand = Mockito.spy(new BurnFuelCommand(refuelable));
        ChangePositionCommand changePositionCommand = new ChangePositionCommand(
                checkFuelCommand,
                moveCommand,
                burnFuelCommand
        );
        try {
            changePositionCommand.execute();
        } catch (CommandException e) {
            assertEquals("сообщение об ошибке должно совпадать",
                    "Положение объекта не доступно", e.getMessage());
        }
        Mockito.verify(refuelable, Mockito.times(1)).getFuelLevel();
        Mockito.verify(refuelable, Mockito.times(1)).getConsumption();
    }
}
