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
        char[] emptyArray = new char[]{};
        rover.takeCommands(emptyArray);

        char[] nonEmptyArray = new char[]{'f'};
        rover.takeCommands(nonEmptyArray);
    }

    //Implement commands that move the rover forward/backward (f,b).

    @Test
    void provideCommands_moveForward_success() {
        Rover rover = new Rover(0,0,"N");

        char[] chars = new char[]{'f'};
        rover.takeCommands(chars);
        assertEquals(1, rover.x());
        assertEquals(0, rover.y());
    }

    @Test
    void provideCommands_moveBackwards_success() {
        Rover rover = new Rover(0,0,"N");

        char[] chars = new char[]{'b'};
        rover.takeCommands(chars);
        assertEquals(-1, rover.x());
        assertEquals(0, rover.y());
    }

    @ParameterizedTest
    @MethodSource
    void provideCommands_forwardBackwardDirection(String direction, char command, int x, int y) {
        Rover rover = new Rover(0,0,direction);

        char[] chars = new char[]{command};
        rover.takeCommands(chars);
        assertEquals(x, rover.x());
        assertEquals(y, rover.y());
    }


    public static Stream<Arguments> provideCommands_forwardBackwardDirection() {
        return Stream.of(
                Arguments.of("N", 'b', -1, 0),
                Arguments.of("N", 'f', 1, 0)
        );
    }


}