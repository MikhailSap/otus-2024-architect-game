package ru.sap.spaceBattle.server;

import lombok.Getter;

@Getter
public class Vector {
    private final int x;
    private final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector plus(Vector next) {
        return new Vector(x + next.getX(), y + next.getY());
    }
}
