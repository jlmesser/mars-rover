package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    void invalidRoverCoords() {
        Rover rover = new Rover(10,10,"N");
        char[] nonEmptyArray = new char[]{'f'};

        Plateau plateau = new Plateau(10, 10, List.of(rover));

        rover.takeCommands(nonEmptyArray);

        assertThrows(Exception.class, plateau::validateRovers);
    }

    @Test
    void invalidRoverCoordsStart() {
        Rover rover = new Rover(10,11,"N");
        assertThrows(Exception.class, () -> new Plateau(10, 10, List.of(rover)));
    }

    @Test
    void validRoverCoords() {
        Rover rover = new Rover(0,0,"N");
        char[] nonEmptyArray = new char[]{'f'};

        Plateau plateau = new Plateau(10, 10, List.of(rover));

        rover.takeCommands(nonEmptyArray);

        assertDoesNotThrow(plateau::validateRovers);
    }
}