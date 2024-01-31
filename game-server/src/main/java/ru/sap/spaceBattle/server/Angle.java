package ru.sap.spaceBattle.server;

import lombok.Getter;

@Getter
public class Angle {
    private int direction;
    private final int directionNumbers;

    public Angle(int direction, int directionNumbers) {
        this.direction = direction;
        this.directionNumbers = directionNumbers;
    }

    public void plus(int angularVelocity) {
        this.direction = (direction + angularVelocity) % directionNumbers;
    }
}
