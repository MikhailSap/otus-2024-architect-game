package ru.sap.spaceBattle.server;

import org.junit.Test;

import ru.sap.spaceBattle.server.command.impl.RotateCommand;
import ru.sap.spaceBattle.server.exception.UnavailableStateException;

import static org.junit.Assert.assertEquals;

public class RotateCommandTest {

    @Test
    public void rotatePositiveTest() {
        RotatableObject rotatable = new RotatableObject(
                new Angle(10, 60),
                100
        );

        RotateCommand rotate = new RotateCommand(rotatable);
        rotate.execute();

        assertEquals(50, rotatable.getAngle().getDirection());
    }

    @Test(expected = UnavailableStateException.class)
    public void getAngleNegativeTest() {
        RotatableObject rotatable = new RotatableObject(null, 100);

        RotateCommand rotate = new RotateCommand(rotatable);
        rotate.execute();
    }

    @Test(expected = UnavailableStateException.class)
    public void getAngularVelocityNegativeTest() {
        RotatableObject rotatable = new RotatableObject(
                new Angle(10, 60),
                0
        );

        RotateCommand rotate = new RotateCommand(rotatable);
        rotate.execute();
    }
}
