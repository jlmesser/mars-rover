package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plateau {
    int x;
    int y;
    List<Rover> rovers = new ArrayList<>();

    public Plateau(int x, int y, List<Rover> rovers) {
        this.x = x;
        this.y = y;
        this.rovers = rovers;
        validateRovers();
    }

    public void moveRovers(){
        rovers.forEach(Rover::takeCommands);
    }

    public void validateRovers(){
        for (Rover rover : rovers) {
            if (rover.x() < 0 || rover.x() > x || rover.y() < 0 || rover.y() > y) {
                throw new IllegalStateException("Invalid rover location, outside plateau");
            }
        }

        long uniqueCoords = rovers.stream().map(rv -> Map.entry(rv.x(), rv.y())).distinct().count();
        if (uniqueCoords != rovers.size()) {
            throw new IllegalStateException("Invalid rover location, at least 2 rovers share one location");
        }
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
