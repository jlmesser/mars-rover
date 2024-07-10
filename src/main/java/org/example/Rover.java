package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.Command.*;
import static org.example.Direction.*;

public final class Rover {
    private int x;
    private int y;
    private Direction direction;
    final List<Command> commands;
    public final List<State> history = new ArrayList<>();

    public Rover(int x, int y, Direction direction, char[] commands) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.commands = getCommands(commands);
        if (commands.length == 0) {
            throw new IllegalStateException("Unexpected value: commands empty");
        }
        State initialState = new State(x, y, direction);
        history.add(initialState);
    }

    public void revertOne() {
        history.removeLast();
        State prevState = history.getLast();
        x = prevState.x();
        y = prevState.y();
        direction = prevState.direction();
    }

    public void takeCommands() {
        for (Command command : commands) {
            switch (command) {
                case f, b -> {
                    move(command);
                    history.add(new State(x, y, direction));
                }
                case r, l -> {
                    turn(command);
                    history.add(new State(x, y, direction));
                }
                default -> throw new IllegalStateException("Unexpected value: " + command);
            }
        }


    }

    private void turn(Command command) {
        if (command == r) {
            switch (direction) {
                case N -> direction = E;
                case E -> direction = S;
                case S -> direction = W;
                case W -> direction = N;
            }
        } else {
            switch (direction) {
                case N -> direction = W;
                case W -> direction = S;
                case S -> direction = E;
                case E -> direction = N;
            }
        }
    }

    private void move(Command firstChar) {
        if (moveNorth(firstChar)) {
            y++;
        } else if (moveSouth(firstChar)) {
            y--;
        } else if (moveEast(firstChar)) {
            x++;
        } else if (moveWest(firstChar)) {
            x--;
        }
    }

    private boolean moveWest(Command firstChar) {
        return firstChar == b && direction.equals(E) || firstChar == f && direction.equals(W);
    }

    private boolean moveEast(Command firstChar) {
        return firstChar == f && direction.equals(E) || firstChar == b && direction.equals(W);
    }

    private boolean moveSouth(Command firstChar) {
        return firstChar == b && direction.equals(N) || firstChar == f && direction.equals(S);
    }

    private boolean moveNorth(Command firstChar) {
        return firstChar == f && direction.equals(N) || firstChar == b && direction.equals(S);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Direction direction() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rover rover = (Rover) o;
        return x == rover.x && y == rover.y && direction == rover.direction && Objects.equals(commands, rover.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, commands, direction);
    }

    @Override
    public String toString() {
        return "Rover[" +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "commands=" + commands + ", " +
                "direction=" + direction + ']';
    }

}
