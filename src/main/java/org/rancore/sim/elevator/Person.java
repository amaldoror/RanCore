package org.rancore.sim.elevator;

public class Person {
    private final int id;
    private final int startFloor;
    private final int destinationFloor;
    private final int requestTime;
    private int arrivalTime;

    public Person(int id, int startFloor, int destinationFloor, int requestTime) {
        this.id = id;
        this.startFloor = startFloor;
        this.destinationFloor = destinationFloor;
        this.requestTime = requestTime;
        this.arrivalTime = -1; // -1 indicates the person has not yet arrived at the destination
    }

    public int getId() {
        return id;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public int getRequestTime() {
        return requestTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean hasArrived() {
        return arrivalTime == -1;
    }
}
