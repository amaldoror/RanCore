package org.rancore.sim.elevator;

import java.util.TreeSet;

public class Elevator {
    private final int id;
    private int currentFloor;
    private final int numberOfFloors;
    private final TreeSet<Integer> requestedFloors;
    private String direction;

    public Elevator(int id, int numberOfFloors) {
        this.id = id;
        this.currentFloor = 1; // Assuming ground floor is 1
        this.numberOfFloors = numberOfFloors;
        this.requestedFloors = new TreeSet<>();
        this.direction = "IDLE";
    }

    public int getId() {
        return id;
    }

    public void requestFloor(int floor) {
        if (floor < 1 || floor > numberOfFloors) {
            System.out.println("Invalid floor requested.");
            return;
        }
        requestedFloors.add(floor);
        System.out.println("Elevator " + id + ": Floor " + floor + " requested.");
    }

    public void moveUp() {
        if (currentFloor < numberOfFloors) {
            currentFloor++;
        }
    }

    public void moveDown() {
        if (currentFloor > 1) {
            currentFloor--;
        }
    }

    public void step() {
        if (requestedFloors.isEmpty()) {
            direction = "IDLE";
            return;
        }

        if (direction.equals("IDLE")) {
            int nextFloor = requestedFloors.first();
            direction = nextFloor > currentFloor ? "UP" : "DOWN";
        }

        if (direction.equals("UP")) {
            moveUp();
        } else if (direction.equals("DOWN")) {
            moveDown();
        }

        if (requestedFloors.contains(currentFloor)) {
            System.out.println("Elevator " + id + ": Stopping at floor " + currentFloor);
            requestedFloors.remove(currentFloor);
            direction = "IDLE";
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public String getDirection() {
        return direction;
    }

    public int getNumberOfRequests() {
        return requestedFloors.size();
    }

    public void handlePerson(Person person, int currentTime) {
        if (currentFloor == person.getStartFloor()) {
            System.out.println("Person " + person.getId() + " boarded Elevator " + id + " at floor " + currentFloor);
            requestFloor(person.getDestinationFloor());
        }
        if (currentFloor == person.getDestinationFloor() && person.hasArrived()) {
            person.setArrivalTime(currentTime);
            System.out.println("Person " + person.getId() + " arrived at destination floor " + currentFloor + " using Elevator " + id);
        }
    }
}
