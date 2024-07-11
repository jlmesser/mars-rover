package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please enter Mars Rover input data");

        try (Scanner scanner = new Scanner(System.in)) {
            int plateauX = scanner.nextInt();
            int plateauY = scanner.nextInt();
            System.out.println("x : " + plateauX + ", y : " + plateauY);
            ArrayList<Rover> rovers = new ArrayList<>();
            while (scanner.hasNextInt()) {
                newRover(scanner, rovers);
            }
            System.out.println("rovers " + rovers);
            Plateau plateau = new Plateau(plateauX, plateauY, rovers);
            plateau.moveRovers();

            rovers.forEach(rover -> System.out.println(rover.x() + " " + rover.y() + " " + rover.direction()));

        }

    }

    private static void newRover(Scanner scanner, ArrayList<Rover> rovers) {
        int roverX = scanner.nextInt();
        int roverY = scanner.nextInt();
        Direction roverDirection = Direction.valueOf(scanner.next());
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        String next = scanner.next(Pattern.compile("([NSEWLRFB])*"));
        char[] commands = next.toCharArray();

        Rover rover = new Rover(roverX, roverY, roverDirection, commands);
        System.out.println("rover : " + rover);
        rovers.add(rover);

    }

}

/*
Test Input:

5 5

1 2 N

LFLFLFLFF

3 3 E

FFRFFRFRRF

Expected Output:

1 3 N

5 1 E
 */