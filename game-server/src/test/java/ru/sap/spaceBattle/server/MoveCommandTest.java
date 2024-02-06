package ru.sap.spaceBattle.server;

import org.junit.Test;
import ru.sap.spaceBattle.server.command.MoveCommand;
import ru.sap.spaceBattle.server.exception.ChangePositionException;
import ru.sap.spaceBattle.server.exception.UnavailableStateException;

import static org.junit.Assert.*;

public class MoveCommandTest {

    @Test
    public void movePositiveTest() {
        Movable movable = new MovableObject(
                new Vector2(12, 5),
                new Vector2(-7, 3)
        );

        MoveCommand move = new MoveCommand(movable);
        move.execute();

        assertEquals(5, ((Vector2)movable.getPosition()).getX());
        assertEquals(8, ((Vector2)movable.getPosition()).getY());
    }

    @Test(expected = UnavailableStateException.class)
    public void getPositionNegativeTest() {
        Movable movable = new MovableObject(
                null,
                new Vector2(-7, 3)
        );

        MoveCommand move = new MoveCommand(movable);
        move.execute();
    }

    @Test(expected = UnavailableStateException.class)
    public void getVelocityNegativeTest() {
        Movable movable = new MovableObject(
                new Vector2(12, 5),
                null
        );

        MoveCommand move = new MoveCommand(movable);
        move.execute();
    }

    @Test(expected = ChangePositionException.class)
    public void changePositionNegativeTest() {
        Movable movable = new MovableObject();
        movable.setPosition(null);
    }

    public static class MovableObject implements Movable {
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
}
