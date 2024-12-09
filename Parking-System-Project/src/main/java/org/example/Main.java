package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        List<Thread> carThreads = new ArrayList<>();
        List<Gate> gates = new ArrayList<>(); // List to track Gate objects
        Gate gate1 = new Gate(1);
        Gate gate2 = new Gate(2);
        Gate gate3 = new Gate(3);
        gates.add(gate1);
        gates.add(gate2);
        gates.add(gate3);
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\hassa\\Downloads\\Parking\\Parking\\src\\main\\resources\\cars_input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(", ");

                if (parts.length != 4) {
                    System.err.println("Invalid input format: " + line);
                    continue;
                }

                int gateId = Integer.parseInt(parts[0].split(" ")[1]);
                int carId = Integer.parseInt(parts[1].split(" ")[1]);
                int parkingDuration = Integer.parseInt(parts[3].split(" ")[1]);

                // Reuse Gate objects from the list for the same gate ID
                Gate gate = gates.get(gateId - 1);
                gate.serveCar();

                carThreads.add(new Thread(new Car(carId, parkingDuration, gate, parkingLot)));
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return;
        }

        // Start all car threads
        for (Thread carThread : carThreads) {
            carThread.start();
        }

        // Wait for all threads to finish
        for (Thread carThread : carThreads) {
            try {
                carThread.join();
            } catch (InterruptedException e) {
                System.err.println("Main thread interrupted.");
            }
        }

        // Print the count of cars served by each gate
        System.out.println("Summary of cars served by each gate:");
        int totalCars = 0;
        for (Gate gate : gates) {
            totalCars += gate.getServedCarCount();
            System.out.println("Gate " + gate.getGateId() + " served " + gate.getServedCarCount() + " cars.");
        }
        System.out.println("Total cars served " + totalCars);
        System.out.println("All cars have finished parking.");
    }
}
