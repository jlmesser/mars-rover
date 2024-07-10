package org.example;

import java.util.ArrayList;
import java.util.List;

public enum Command {
    f('f'),
    b('b'),
    r('r'),
    l('l');

    public final char ch;

    public static Command fromChar(char ch) {
        return switch (ch) {
            case 'f' -> f;
            case 'b' -> b;
            case 'r' -> r;
            case 'l' -> l;
            default -> null;
        };
    }

    public static List<Command> getCommands(char[] commands) {
        ArrayList<Command> commandList = new ArrayList<>();

        for (char command : commands) {
            commandList.add(Command.fromChar(command));
        }

        return commandList;
    }

    Command(char ch) {
        this.ch = ch;
    }
}
