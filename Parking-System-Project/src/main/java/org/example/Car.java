package org.example;

public class Car implements Runnable {
    private final int id;
    private final int parkingDuration;
    private final Gate gate;
    private final ParkingLot parkingLot;

    public Car(int id, int parkingDuration, Gate gate, ParkingLot parkingLot) {
        this.id = id;
        this.parkingDuration = parkingDuration;
        this.gate = gate;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        try {
            System.out.println("Car " + id + " from Gate " + gate.getGateId() + " attempting to park.");
            if (parkingLot.TryToPark()) {
                System.out.println("Car " + id + " from Gate " + gate.getGateId() + " parked. (Parking Status: " +
                        (4 - parkingLot.GetParkingLots()) + " spots occupied)");
                System.out.println("Car " + id + " from Gate " + gate.getGateId() + " left. (Parking Status: " +
                        (4 - parkingLot.GetParkingLots()) + " spots occupied)");
            } else {
                System.out.println("Car " + id + " from Gate " + gate.getGateId() + " waiting for a spot.");
                parkingLot.wait_to_park();
                System.out.println("Car " + id + " from Gate " + gate.getGateId() + " parked after waiting. (Parking Status: " +
                        (4 - parkingLot.GetParkingLots()) + " spots occupied)");
                System.out.println("Car " + id + " from Gate " + gate.getGateId() + " left after waiting. (Parking Status: " +
                        (4 - parkingLot.GetParkingLots()) + " spots occupied)");
            }
            Thread.sleep(parkingDuration * 1000L); // Simulate parking duration
            parkingLot.Unpark();


        } catch (InterruptedException e) {
            System.err.println("Car " + id + " from Gate " + gate.getGateId() + " was interrupted.");
        }
    }
}
