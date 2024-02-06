package ru.sap.spaceBattle.server;

import lombok.Getter;

@Getter
public class Vector2 implements Vector {
    private final int x;
    private final int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector plus(Vector next) {
        if (!(next instanceof Vector2)) {
            throw new IllegalArgumentException("Передан вертор не верной размерности");
        }
        return new Vector2(x + ((Vector2)next).getX(), y + ((Vector2)next).getY());
    }
}
