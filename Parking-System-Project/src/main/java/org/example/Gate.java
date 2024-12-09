package org.example;

public class Gate {
    private final int gateId;
    private int servedCarCount;

    public Gate(int gateId) {
        this.gateId = gateId;
        this.servedCarCount = 0;  // Initialize count to 0
    }

    public int getGateId() {
        return gateId;
    }

    // Method to increment the served car count
    public  void serveCar() {
        servedCarCount++;
    }

    public int getServedCarCount() {
        return servedCarCount;
    }
}
