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

        assertEquals(11, rover.y()); //todo implement stopping path at valid coord
        assertEquals(10, rover.x());
        assertThrows(Exception.class, plateau::validateRovers);
    }

    @Test
    void validRover_invalidMoveCollision_endCoordFinalValidCoordOfPath() {
        Rover rover1 = new Rover(0,0,"N", new char[]{'f'});
        Rover rover2 = new Rover(0,1,"N", new char[]{'f','b'});

        Plateau plateau = new Plateau(10, 10, List.of(rover1, rover2)); //todo validate start rovers not on top of each other

        plateau.moveRovers();

        assertThrows(Exception.class, plateau::validateRovers);
    }

    @Test
    void invalidRover_initPlateau_exception() {
        Rover rover = new Rover(10,11,"N", new char[]{'f'});
        assertThrows(Exception.class, () -> new Plateau(10, 10, List.of(rover)));

        Rover rover2 = new Rover(0,0,"N", new char[]{'f'});
        Rover rover3 = new Rover(0,0,"N", new char[]{'f'});
        assertThrows(Exception.class, () -> new Plateau(10, 10, List.of(rover2, rover3)));
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