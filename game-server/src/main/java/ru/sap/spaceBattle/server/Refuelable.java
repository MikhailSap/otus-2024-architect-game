package ru.sap.spaceBattle.server;

public interface Refuelable {
    int getFuelLevel();
    void refuel();
    int getConsumption();
    void burnFuel(int decrement);
}
