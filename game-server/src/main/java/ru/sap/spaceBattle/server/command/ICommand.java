package ru.sap.spaceBattle.server.command;

public interface ICommand {
    void execute();

    default String getName() {
       return this.getClass().getName();
    }
}
