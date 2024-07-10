package org.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Rover {
    private int x;
    private int y;
    private String direction;
    char[] commands;
    public List<State> history = new ArrayList<>();

    public Rover(int x, int y, String direction, char[] commands) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.commands = commands;
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
        for (char command : commands) {
            switch (command) {
                case 'f', 'b':
                    move(command);
                    history.add(new State(x, y, direction));
                    break;
                case 'r', 'l':
                    turn(command);
                    history.add(new State(x, y, direction));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + command);
            }
        }


    }

    //todo make some enums
    private void turn(char command) {
        if (command == 'r') {
            switch (direction) {
                case "N":
                    direction = "E";
                    break;
                case "E":
                    direction = "S";
                    break;
                case "S":
                    direction = "W";
                    break;
                case "W":
                    direction = "N";
                    break;
            }
        } else {
            switch (direction) {
                case "N":
                    direction = "W";
                    break;
                case "W":
                    direction = "S";
                    break;
                case "S":
                    direction = "E";
                    break;
                case "E":
                    direction = "N";
                    break;
            }
        }
    }

    private void move(char firstChar) {
        if (firstChar == 'f' && direction.equals("N") || firstChar == 'b' && direction.equals("S")) {
            y++;
        } else if (firstChar == 'b' && direction.equals("N") || firstChar == 'f' && direction.equals("S")) {
            y--;
        } else if (firstChar == 'f' && direction.equals("E") || firstChar == 'b' && direction.equals("W")) {
            x++;
        } else if (firstChar == 'b' && direction.equals("E") || firstChar == 'f' && direction.equals("W")) {
            x--;
        }
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public String direction() {
        return direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Rover) obj;
        return this.x == that.x &&
                this.y == that.y &&
                this.commands == that.commands &&
                Objects.equals(this.direction, that.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, Arrays.hashCode(commands), direction);
    }

    @Override
    public String toString() {
        return "Rover[" +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "commands=" + Arrays.toString(commands) + ", " +
                "direction=" + direction + ']';
    }

}
