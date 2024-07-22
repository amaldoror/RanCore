package org.rancore.sim.elevator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ElevatorController {
    private List<Elevator> elevators;
    private int numberOfFloors;

    public ElevatorController(int numberOfElevators, int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
        this.elevators = new ArrayList<>();
        for (int i = 1; i <= numberOfElevators; i++) {
            elevators.add(new Elevator(i, numberOfFloors));
        }
    }

    public void requestFloor(Person person) {
        int floor = person.getStartFloor();
        if (floor < 1 || floor > numberOfFloors) {
            System.out.println("Invalid floor requested.");
            return;
        }

        // Find the best elevator to handle the request
        Elevator bestElevator = elevators.stream()
                .min(Comparator.comparingInt(e -> {
                    int distance = Math.abs(e.getCurrentFloor() - floor);
                    boolean sameDirection = (e.getDirection().equals("UP") && floor > e.getCurrentFloor()) ||
                            (e.getDirection().equals("DOWN") && floor < e.getCurrentFloor());
                    return distance + (sameDirection ? 0 : numberOfFloors) + e.getNumberOfRequests();
                }))
                .orElse(null);

        if (bestElevator != null) {
            bestElevator.requestFloor(floor);
        }
    }

    public void step(List<Person> people, int currentTime) {
        for (Elevator elevator : elevators) {
            elevator.step();
            for (Person person : people) {
                if (person.hasArrived()) {
                    elevator.handlePerson(person, currentTime);
                }
            }
        }
    }

    public void displayStatus() {
        for (Elevator elevator : elevators) {
            System.out.println("Elevator " + elevator.getId() + " - Current floor: " + elevator.getCurrentFloor() + ", Direction: " + elevator.getDirection() + ", Requests: " + elevator.getNumberOfRequests());
        }
    }
}
