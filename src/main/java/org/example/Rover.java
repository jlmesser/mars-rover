package org.example;

import java.util.Objects;

public final class Rover {
    private int x;
    private int y;
    private String direction;

    public Rover(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void takeCommands(char[] commands) {

        validate(commands);

        char firstChar = commands[0];

        if (firstChar == 'f' && direction.equals("N") || firstChar == 'b' && direction.equals("S")) {
            y++;
        }

        if (firstChar == 'b' && direction.equals("N") || firstChar == 'f' && direction.equals("S")) {
            y--;
        }

        if (firstChar == 'f' && direction.equals("E") || firstChar == 'b' && direction.equals("W")) {
            x++;
        }

        if (firstChar == 'b' && direction.equals("E") || firstChar == 'f' && direction.equals("W")) {
            x--;
        }


    }

    /*
        f north = increase y
        b north = decrease y

        south = opposite of north (f decrease y, b increase y)

        f east = increase x
        b east = decrease x

         */
    private static void validate(char[] commands) {
        if (commands.length == 0) {
            throw new IllegalStateException("Unexpected value: commands empty");
        }

        for (int i = 0; i < commands.length; i++) {
            switch (commands[i]) {
                case 'f', 'b':
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + commands[i]);
            }
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
                Objects.equals(this.direction, that.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction);
    }

    @Override
    public String toString() {
        return "Rover[" +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "direction=" + direction + ']';
    }

}
