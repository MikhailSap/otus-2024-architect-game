package ru.sap.spaceBattle.server;

import ru.sap.spaceBattle.server.exception.UnavailableStateException;

public class RotatableObject implements Rotatable {
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
