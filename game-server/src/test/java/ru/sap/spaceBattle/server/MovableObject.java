package ru.sap.spaceBattle.server;

import ru.sap.spaceBattle.server.exception.ChangePositionException;
import ru.sap.spaceBattle.server.exception.UnavailableStateException;

public class MovableObject implements Movable {

    private Vector position;
    private Vector velocity;

    public MovableObject() {
    }

    public MovableObject(Vector position, Vector velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    @Override
    public Vector getPosition() throws UnavailableStateException {
        if (position == null) {
            throw new UnavailableStateException("Положение объекта не доступно");
        }
        return position;
    }

    @Override
    public Vector getVelocity() throws UnavailableStateException {
        if (velocity == null) {
            throw new UnavailableStateException("Скорость объекта не доступна");
        }
        return velocity;
    }

    @Override
    public void setPosition(Vector next) throws ChangePositionException {
        if (next == null) {
            throw new ChangePositionException("Новое положение объекта - null");
        }
        this.position = next;
    }
}
