package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    void validRover_invalidMoveOffPlateau_endCoordFinalValidCoordOfPath() {
        char[] nonEmptyArray = new char[]{'f', 'f'};
        Rover rover = new Rover(10,9,"N", nonEmptyArray);

        Plateau plateau = new Plateau(10, 10, List.of(rover));

        plateau.moveRovers();

        assertEquals(10, rover.y());
        assertEquals(10, rover.x());
        assertDoesNotThrow(plateau::validateRovers);
    }

    @Test
    void validRover_invalidMoveOffPlateau_endCoordFinalValidCoordOfPathnegative() {
        char[] nonEmptyArray = new char[]{'b'};
        Rover rover = new Rover(0,0,"N", nonEmptyArray);

        Plateau plateau = new Plateau(10, 10, List.of(rover));

        plateau.moveRovers();

        assertEquals(0, rover.y());
        assertEquals(0, rover.x());
        assertDoesNotThrow(plateau::validateRovers);
    }

    @Test
    void validRover_invalidMoveCollision_endCoordFinalValidCoordOfPath() {
        Rover rover1 = new Rover(0,0,"N", new char[]{'f'});
        Rover rover2 = new Rover(0,1,"N", new char[]{'f','b'});

        Plateau plateau = new Plateau(10, 10, List.of(rover1, rover2));

        plateau.moveRovers();

        assertEquals(0, rover1.y());
        assertEquals(0, rover1.x());

        assertEquals(1, rover2.y());
        assertEquals(0, rover2.x());

        assertDoesNotThrow(plateau::validateRovers);
    }

    @Test
    void invalidRover_initPlateau_exception() {
        Rover rover = new Rover(11,11,"N", new char[]{'f'});
        assertThrows(Exception.class, () -> new Plateau(10, 10, List.of(rover)));

        Rover rover2 = new Rover(0,0,"N", new char[]{'f'});
        Rover rover3 = new Rover(0,0,"N", new char[]{'f'});
        assertThrows(Exception.class, () -> new Plateau(10, 10, List.of(rover2, rover3)));

        Rover rover4 = new Rover(-1,-1,"N", new char[]{'f'});
        assertThrows(Exception.class, () -> new Plateau(10, 10, List.of(rover4)));
    }

    @Test
    void validRover_validMove_success() {
        char[] nonEmptyArray = new char[]{'f'};
        Rover rover = new Rover(0,0,"N", nonEmptyArray);

        Plateau plateau = new Plateau(10, 10, List.of(rover));

        plateau.moveRovers();

        assertEquals(1, rover.y());
        assertEquals(0, rover.x());
        assertEquals("N", rover.direction());
        assertDoesNotThrow(plateau::validateRovers);
    }
}