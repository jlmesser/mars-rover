package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @Test
    void createRover_validInput_success() {
        Rover roverN = new Rover(0,0,Direction.N, new char[]{Command.f.ch});
        Rover roverS = new Rover(0,0,Direction.S, new char[]{Command.f.ch});
        Rover roverE = new Rover(0,0,Direction.E, new char[]{Command.f.ch});
        Rover roverW = new Rover(0,0,Direction.W, new char[]{Command.f.ch});
    }

    @Test
    void provideCommands_validInput_success() {
        char[] nonEmptyArray = new char[]{Command.f.ch, Command.b.ch, Command.l.ch, Command.r.ch};
        Rover rover = new Rover(0,0,Direction.N, nonEmptyArray);
        rover.takeCommands();
    }

    @Test
    void provideCommands_invalidInput_success() {
        assertThrows(Exception.class, () -> new Rover(0,0,Direction.N, new char[]{}));
        assertThrows(Exception.class, () -> new Rover(0,0,Direction.N, new char[]{'k'}).takeCommands());
    }

    @ParameterizedTest(name = "[{index}] {0} {1} -> x: {2} y: {3}")
    @MethodSource
    void provideCommands_forwardBackwardDirection(Direction direction, char[] chars, int x, int y) {
        Rover rover = new Rover(0,0,direction, chars);

        rover.takeCommands();

        assertAll(() -> assertEquals(x, rover.x()), () -> assertEquals(y, rover.y()));
    }


    //todo add checks for final heading (e.g. started north, ended up west etc?)
    public static Stream<Arguments> provideCommands_forwardBackwardDirection() {
        char[] f = {Command.f.ch};
        char[] b = {Command.b.ch};
        char[] cancelOut = {Command.b.ch, Command.f.ch};
        char[] ff = {Command.f.ch, Command.f.ch};
        char[] bb = {Command.b.ch, Command.b.ch};
        return Stream.of(
                Arguments.of(Direction.N, f, 0, 1),
                Arguments.of(Direction.N, b, 0, -1),
                Arguments.of(Direction.S, f, 0, -1),
                Arguments.of(Direction.S, b, 0, 1),
                Arguments.of(Direction.E, f, 1, 0),
                Arguments.of(Direction.E, b, -1, 0),
                Arguments.of(Direction.W, f, -1, 0),
                Arguments.of(Direction.W, b, 1, 0),

                Arguments.of(Direction.N, cancelOut, 0, 0),
                Arguments.of(Direction.S, cancelOut, 0, 0),
                Arguments.of(Direction.E, cancelOut, 0, 0),
                Arguments.of(Direction.W, cancelOut, 0, 0),

                Arguments.of(Direction.N, ff, 0, 2),
                Arguments.of(Direction.S, ff, 0, -2),
                Arguments.of(Direction.E, ff, 2, 0),
                Arguments.of(Direction.W, ff, -2, 0),

                Arguments.of(Direction.N, bb, 0, -2),
                Arguments.of(Direction.S, bb, 0, 2),
                Arguments.of(Direction.E, bb, -2, 0),
                Arguments.of(Direction.W, bb, 2, 0),

                Arguments.of(Direction.N, new char[]{Command.f.ch, Command.r.ch, Command.f.ch}, 1, 1),
                Arguments.of(Direction.N, new char[]{Command.f.ch, Command.l.ch, Command.f.ch}, -1, 1),

                Arguments.of(Direction.E, new char[]{Command.f.ch, Command.r.ch, Command.f.ch}, 1, -1),
                Arguments.of(Direction.E, new char[]{Command.f.ch, Command.l.ch, Command.f.ch}, 1, 1),

                Arguments.of(Direction.S, new char[]{Command.b.ch, Command.r.ch, Command.b.ch}, 1, 1),
                Arguments.of(Direction.S, new char[]{Command.b.ch, Command.l.ch, Command.b.ch}, -1, 1),

                Arguments.of(Direction.W, new char[]{Command.b.ch, Command.r.ch, Command.b.ch}, 1, -1),
                Arguments.of(Direction.W, new char[]{Command.b.ch, Command.l.ch, Command.b.ch}, 1, 1),

                Arguments.of(Direction.N, new char[]{Command.b.ch, Command.r.ch, Command.b.ch}, -1, -1),
                Arguments.of(Direction.N, new char[]{Command.b.ch, Command.l.ch, Command.b.ch}, 1, -1),

                Arguments.of(Direction.E, new char[]{Command.b.ch, Command.r.ch, Command.b.ch}, -1, 1),
                Arguments.of(Direction.E, new char[]{Command.b.ch, Command.l.ch, Command.b.ch}, -1, -1),

                Arguments.of(Direction.S, new char[]{Command.f.ch, Command.r.ch, Command.f.ch}, -1, -1),
                Arguments.of(Direction.S, new char[]{Command.f.ch, Command.l.ch, Command.f.ch}, 1, -1),

                Arguments.of(Direction.W, new char[]{Command.f.ch, Command.r.ch, Command.f.ch}, -1, 1),
                Arguments.of(Direction.W, new char[]{Command.f.ch, Command.l.ch, Command.f.ch}, -1, -1)
        );
    }

    @Test
    void provideCommands_turnLeft_success() {
        char[] nonEmptyArray = new char[]{Command.l.ch};
        Rover rover = new Rover(0,0,Direction.N, nonEmptyArray);
        rover.takeCommands();
        assertEquals(Direction.W, rover.direction());
        rover.takeCommands();
        assertEquals(Direction.S, rover.direction());
        rover.takeCommands();
        assertEquals(Direction.E, rover.direction());
        rover.takeCommands();
        assertEquals(Direction.N, rover.direction());
    }

    @Test
    void provideCommands_turnRight_success() {
        char[] nonEmptyArray = new char[]{Command.r.ch};
        Rover rover = new Rover(0,0,Direction.N, nonEmptyArray);
        rover.takeCommands();
        assertEquals(Direction.E, rover.direction());
        rover.takeCommands();
        assertEquals(Direction.S, rover.direction());
        rover.takeCommands();
        assertEquals(Direction.W, rover.direction());
        rover.takeCommands();
        assertEquals(Direction.N, rover.direction());
    }

}