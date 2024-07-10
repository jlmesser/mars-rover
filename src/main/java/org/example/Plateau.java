package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Plateau {

    final Logger logger = LoggerFactory.getLogger(Plateau.class);

    final int x;
    final int y;
    final List<Rover> rovers;

    public Plateau(int x, int y, List<Rover> rovers) {
        this.x = x;
        this.y = y;
        this.rovers = rovers;
        validateRovers();
    }

    public void moveRovers(){
        for (Rover rover : rovers) {
            rover.takeCommands();

            Set<State> uniqueStates = rovers.stream().filter(r -> !r.equals(rover)).map(rv -> rv.history.getLast()).collect(Collectors.toSet());

            while (uniqueStates.contains(rover.history.getLast()) || isRoverOutsidePlateau(rover)) {
                logger.warn("rover collision at {}, backtracking", rover.history.getLast());
                rover.revertOne();
            }

        }
        logger.info("finished moving rovers - {}", rovers);
    }

    public void validateRovers(){
        for (Rover rover : rovers) {
            if (isRoverOutsidePlateau(rover)) {
                throw new IllegalStateException("Invalid rover location, outside plateau");
            }
        }

        long uniqueCoords = rovers.stream().map(rv -> rv.history.getLast()).distinct().count();
        if (uniqueCoords != rovers.size()) {
            throw new IllegalStateException("Invalid rover location, at least 2 rovers share one location");
        }
        logger.info("plateau validated");
    }

    private boolean isRoverOutsidePlateau(Rover rover) {
        return rover.x() < 0 || rover.x() > x || rover.y() < 0 || rover.y() > y;
    }

    public List<Rover> getRovers() {
        return rovers;
    }
}
