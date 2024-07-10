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
        Rover roverN = new Rover(0,0,"N");
        Rover roverS = new Rover(0,0,"S");
        Rover roverE = new Rover(0,0,"E");
        Rover roverW = new Rover(0,0,"W");
    }

    @Test
    void provideCommands_validInput_success() {
        Rover rover = new Rover(0,0,"N");
        char[] nonEmptyArray = new char[]{'f', 'b', 'l', 'r'};
        rover.takeCommands(nonEmptyArray);
    }

    @Test
    void provideCommands_invalidInput_success() {
        Rover rover = new Rover(0,0,"N");

        assertThrows(Exception.class, () -> rover.takeCommands(new char[]{}));
        assertThrows(Exception.class, () -> rover.takeCommands(new char[]{'k'}));
    }

    @ParameterizedTest(name = "[{index}] {0} {1} -> x: {2} y: {3}")
    @MethodSource
    void provideCommands_forwardBackwardDirection(String direction, char[] chars, int x, int y) {
        Rover rover = new Rover(0,0,direction);

        rover.takeCommands(chars);

        assertAll(() -> assertEquals(x, rover.x()), () -> assertEquals(y, rover.y()));
    }


    public static Stream<Arguments> provideCommands_forwardBackwardDirection() {
        char[] f = {'f'};
        char[] b = {'b'};
        char[] cancelOut = {'b', 'f'};
        char[] ff = {'f', 'f'};
        char[] bb = {'b', 'b'};
        return Stream.of(
                Arguments.of("N", f, 0, 1),
                Arguments.of("N", b, 0, -1),
                Arguments.of("S", f, 0, -1),
                Arguments.of("S", b, 0, 1),
                Arguments.of("E", f, 1, 0),
                Arguments.of("E", b, -1, 0),
                Arguments.of("W", f, -1, 0),
                Arguments.of("W", b, 1, 0),

                Arguments.of("N", cancelOut, 0, 0),
                Arguments.of("S", cancelOut, 0, 0),
                Arguments.of("E", cancelOut, 0, 0),
                Arguments.of("W", cancelOut, 0, 0),

                Arguments.of("N", ff, 0, 2),
                Arguments.of("S", ff, 0, -2),
                Arguments.of("E", ff, 2, 0),
                Arguments.of("W", ff, -2, 0),

                Arguments.of("N", bb, 0, -2),
                Arguments.of("S", bb, 0, 2),
                Arguments.of("E", bb, -2, 0),
                Arguments.of("W", bb, 2, 0),

                Arguments.of("N", new char[]{'f', 'r', 'f'}, 1, 1)
        );
    }

    @Test
    void provideCommands_turnLeft_success() {
        Rover rover = new Rover(0,0,"N");
        char[] nonEmptyArray = new char[]{'l'};
        rover.takeCommands(nonEmptyArray);
        assertEquals("W", rover.direction());
        rover.takeCommands(nonEmptyArray);
        assertEquals("S", rover.direction());
        rover.takeCommands(nonEmptyArray);
        assertEquals("E", rover.direction());
        rover.takeCommands(nonEmptyArray);
        assertEquals("N", rover.direction());
    }

    @Test
    void provideCommands_turnRight_success() {
        Rover rover = new Rover(0,0,"N");
        char[] nonEmptyArray = new char[]{'r'};
        rover.takeCommands(nonEmptyArray);
        assertEquals("E", rover.direction());
        rover.takeCommands(nonEmptyArray);
        assertEquals("S", rover.direction());
        rover.takeCommands(nonEmptyArray);
        assertEquals("W", rover.direction());
        rover.takeCommands(nonEmptyArray);
        assertEquals("N", rover.direction());
    }

}