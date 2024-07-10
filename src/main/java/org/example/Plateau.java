package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Plateau {
    int x;
    int y;
    List<Rover> rovers = new ArrayList<>();

    //todo add some helpful error messages and logging

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
                rover.revertOne();
            }

        }
    }

    public void validateRovers(){
        for (Rover rover : rovers) {

            if ( isRoverOutsidePlateau(rover)) {
                throw new IllegalStateException("Invalid rover location, outside plateau");
            }
        }

        long uniqueCoords = rovers.stream().map(rv -> Map.entry(rv.x(), rv.y())).distinct().count();
        if (uniqueCoords != rovers.size()) {
            throw new IllegalStateException("Invalid rover location, at least 2 rovers share one location");
        }
    }

    private boolean isRoverOutsidePlateau(Rover rover) {
        return rover.x() < 0 || rover.x() > x || rover.y() < 0 || rover.y() > y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Rover> getRovers() {
        return rovers;
    }

    public void setRovers(List<Rover> rovers) {
        this.rovers = rovers;
    }
}
