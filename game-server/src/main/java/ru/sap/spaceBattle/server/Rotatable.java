package ru.sap.spaceBattle.server;

import ru.sap.spaceBattle.server.exception.UnavailableStateException;

public interface Rotatable {
    Angle getAngle() throws UnavailableStateException;
    int getAngularVelocity() throws UnavailableStateException;
}
