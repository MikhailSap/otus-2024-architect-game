package ru.sap.spaceBattle.server;

import org.junit.Test;

import ru.sap.spaceBattle.server.command.RotateCommand;
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

    public static class RotatableObject implements Rotatable {
        private final Angle angle;
        private final int angularVelocity;

        public RotatableObject(Angle angle, int angularVelocity) {
            this.angle = angle;
            this.angularVelocity = angularVelocity;
        }

        @Override
        public Angle getAngle() throws UnavailableStateException {
            if (angle == null) {
                throw new UnavailableStateException("Угол объекта не доступен");
            }
            return angle;
        }

        @Override
        public int getAngularVelocity() throws UnavailableStateException {
            if (angularVelocity == 0) {
                throw new UnavailableStateException("Угловая скорость объекта равна нулю");
            }
            return angularVelocity;
        }
    }
}
