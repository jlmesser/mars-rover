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
}