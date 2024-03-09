package ru.sap.spaceBattle.server;

import org.junit.Test;
import org.mockito.Mockito;
import ru.sap.spaceBattle.server.command.impl.CheckFuelCommand;
import ru.sap.spaceBattle.server.exception.CommandException;

public class CheckFuelCommandTest {

    @Test
    public void checkFuelPositiveTest() {
        Refuelable refuelable = Mockito.spy(new RefuelableObject(70, 5));
        CheckFuelCommand command = new CheckFuelCommand(refuelable);
        command.execute();
        Mockito.verify(refuelable, Mockito.times(1)).getFuelLevel();
        Mockito.verify(refuelable, Mockito.times(1)).getConsumption();
    }

    @Test(expected = CommandException.class)
    public void checkFuelNegativeTest() {
        Refuelable refuelable = Mockito.spy(new RefuelableObject(10, 15));
        CheckFuelCommand command = new CheckFuelCommand(refuelable);
        command.execute();
    }
}
