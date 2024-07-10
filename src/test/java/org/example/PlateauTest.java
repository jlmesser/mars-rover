package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    void validRover_invalidMove_exception() {
        char[] nonEmptyArray = new char[]{'f'};
        Rover rover = new Rover(10,10,"N", nonEmptyArray);

        Plateau plateau = new Plateau(10, 10, List.of(rover));

        plateau.moveRovers();

        assertEquals(11, rover.y());
        assertEquals(10, rover.x());
        assertThrows(Exception.class, plateau::validateRovers);
    }

    @Test
    void invalidRover_initPlateau_exception() {
        Rover rover = new Rover(10,11,"N", new char[]{});
        assertThrows(Exception.class, () -> new Plateau(10, 10, List.of(rover)));
    }

    @Test
    void validRover_validMove_success() {
        char[] nonEmptyArray = new char[]{'f'};
        Rover rover = new Rover(0,0,"N", nonEmptyArray);

        Plateau plateau = new Plateau(10, 10, List.of(rover));

        plateau.moveRovers();

        assertEquals(1, rover.y());
        assertEquals(0, rover.x());
        assertDoesNotThrow(plateau::validateRovers);
    }
}