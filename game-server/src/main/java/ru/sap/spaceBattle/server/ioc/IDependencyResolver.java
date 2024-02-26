package ru.sap.spaceBattle.server.ioc;

/**
 * Механизм разрешения зависимостей
 */
public interface IDependencyResolver {
    Object resolve(String dependency, Object[] args);
}
