package ru.sap.spaceBattle.server;

import ru.sap.spaceBattle.server.exception.CommandException;

public class RefuelableObject implements Refuelable {
    private static final int FULL_TANK = 100;
    private final int consumption;
    private int fuelLevel;

    public RefuelableObject(int fuelLevel, int consumption) {
        this.fuelLevel = fuelLevel;
        this.consumption = consumption;
    }

    @Override
    public int getFuelLevel() {
        return fuelLevel;
    }

    @Override
    public void refuel() {
        fuelLevel = FULL_TANK;
    }

    @Override
    public int getConsumption() {
        return consumption;
    }

    @Override
    public void burnFuel(int decrement) {
        if (decrement == 0) {
            throw new CommandException("Расход топлива не может быть нулевым");
        }
        fuelLevel -= decrement;
    }
}
