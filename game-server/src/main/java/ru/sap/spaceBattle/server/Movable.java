package ru.sap.spaceBattle.server;

import ru.sap.spaceBattle.server.exception.ChangePositionException;
import ru.sap.spaceBattle.server.exception.UnavailableStateException;

public interface Movable {
    Vector getPosition() throws UnavailableStateException;
    Vector getVelocity() throws UnavailableStateException;
    void setPosition(Vector newPosition) throws ChangePositionException;
}
