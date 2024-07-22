package org.rancore.sim.elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSimulation {
    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(3, 10); // 3 elevators with 10 floors
        List<Person> people = new ArrayList<>();

        // Create people with requests
        people.add(new Person(1, 1, 5, 0));
        people.add(new Person(2, 2, 7, 1));
        people.add(new Person(3, 3, 6, 2));
        people.add(new Person(4, 4, 2, 3));
        people.add(new Person(5, 8, 1, 4));

        // Simulating the elevator moving
        for (int time = 0; time < 20; time++) {
            for (Person person : people) {
                if (time == person.getRequestTime()) {
                    controller.requestFloor(person);
                }
            }
            controller.step(people, time);
            controller.displayStatus();
            System.out.println("----");
        }

        // Displaying the time it took for each person to reach their destination
        for (Person person : people) {
            System.out.println("Person " + person.getId() + " took " + (person.getArrivalTime() - person.getRequestTime()) + " time units to reach their destination.");
        }
    }
}
