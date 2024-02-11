package ru.sap.spaceBattle.server;

import org.junit.Test;
import org.mockito.Mockito;
import ru.sap.spaceBattle.server.command.impl.BurnFuelCommand;
import ru.sap.spaceBattle.server.exception.CommandException;

import static org.junit.Assert.*;

public class BurnFuelCommandTest {
    private final Refuelable refuelable = new RefuelableObject(70, 5);

    @Test
    public void burnFuelTest() {
        BurnFuelCommand command = new BurnFuelCommand(refuelable);
        command.execute();
        assertEquals(65, refuelable.getFuelLevel());
    }

    @Test(expected = CommandException.class)
    public void burnFuelNegativeTest() {
        Refuelable refuelable = Mockito.spy(new RefuelableObject(10, 0));
        BurnFuelCommand command = new BurnFuelCommand(refuelable);
        command.execute();
    }
}
