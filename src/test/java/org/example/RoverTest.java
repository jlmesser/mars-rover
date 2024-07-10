package org.example;

import org.junit.jupiter.api.Test;

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

        char[] nonEmptyArray = new char[]{'a'};
        rover.takeCommands(nonEmptyArray);
    }
}