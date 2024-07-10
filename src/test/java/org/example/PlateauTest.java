package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    void validRover_validMove_success() {
        char[] nonEmptyArray = new char[]{'f'};
        Rover rover = new Rover(0, 0, Direction.N, nonEmptyArray);

        Plateau plateau = new Plateau(10, 10, List.of(rover));

        plateau.moveRovers();

        assertEquals(1, rover.y());
        assertEquals(0, rover.x());
        assertEquals(Direction.N, rover.direction());
        assertDoesNotThrow(plateau::validateRovers);
    }

    @ParameterizedTest
    @MethodSource
    void invalidMoveReverted(List<Rover> startState, List<Rover> endState) {

        Plateau plateau = new Plateau(10, 10, startState);

        plateau.moveRovers();

        assertEquals(plateau.getRovers().size(), endState.size());
        assertEquals(plateau.getRovers().getFirst(), endState.getFirst());
        assertEquals(plateau.getRovers().getLast(), endState.getLast());

        assertDoesNotThrow(plateau::validateRovers);
    }


    public static Stream<Arguments> invalidMoveReverted() {
        return Stream.of(
                Arguments.of(
                        List.of(new Rover(10, 9, Direction.N, new char[]{'f', 'f'})),
                        List.of(new Rover(10, 10, Direction.N, new char[]{'f', 'f'}))),
                Arguments.of(
                        List.of(new Rover(0, 0, Direction.N, new char[]{'b'})),
                        List.of(new Rover(0, 0, Direction.N, new char[]{'b'}))),
                Arguments.of(
                        List.of(new Rover(0, 0, Direction.N, new char[]{'f'}), new Rover(0, 1, Direction.N, new char[]{'f', 'b'})),
                        List.of(new Rover(0, 0, Direction.N, new char[]{'f'}), new Rover(0, 1, Direction.N, new char[]{'f', 'b'})))
        );
    }


    @ParameterizedTest
    @MethodSource
    void init_throws(List<Rover> rovers) {
        assertThrows(Exception.class, () -> new Plateau(10, 10, rovers));
    }


    public static Stream<Arguments> init_throws() {
        return Stream.of(
                Arguments.of(List.of(new Rover(11, 11, Direction.N, new char[]{'f'}))),
                Arguments.of(List.of(new Rover(-1, -1, Direction.N, new char[]{'f'}))),
                Arguments.of(List.of(
                        new Rover(0, 0, Direction.N, new char[]{'f'}),
                        new Rover(0, 0, Direction.N, new char[]{'f'})
                ))

        );
    }
}