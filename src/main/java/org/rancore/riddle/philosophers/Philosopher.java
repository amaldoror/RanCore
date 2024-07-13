package org.rancore.riddle.philosophers;

import java.util.concurrent.locks.Lock;

/**
 * The Philosopher class models a philosopher in the classic "Dining Philosophers" problem.
 * Each philosopher alternates between thinking and eating, but requires two forks to eat.
 * This class uses a concurrency mechanism to avoid deadlock by ensuring a strict order
 * of resource acquisition.
 */
class Philosopher implements Runnable {
    private final Lock lowerFork;
    private final Lock higherFork;
    private final int id;
    private volatile boolean running = true;

    /**
     * Constructs a Philosopher with a unique identifier and two locks representing forks.
     * Forks are ordered to prevent deadlock by ensuring each philosopher always picks up
     * the lower hashcode fork first.
     *
     * @param id the identifier for the philosopher
     * @param leftFork the lock representing the left fork
     * @param rightFork the lock representing the right fork
     */
    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        // Assign forks based on their numerical order to enforce the resource hierarchy
        if (leftFork.hashCode() < rightFork.hashCode()) {
            this.lowerFork = leftFork;
            this.higherFork = rightFork;
        } else {
            this.lowerFork = rightFork;
            this.higherFork = leftFork;
        }
    }

    /**
     * Runs the philosopher's lifecycle, alternating between thinking and eating.
     * The philosopher picks up forks in a fixed order to avoid deadlock and releases
     * them after eating.
     */
    @Override
    public void run() {
        try {
            while (running) {
                think();
                if (pickUpForks()) {
                    try {
                        eat();
                    } finally {
                        putDownForks();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Simulates the philosopher thinking for a random amount of time.
     *
     * @throws InterruptedException if the thread is interrupted while thinking
     */
    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    /**
     * Attempts to pick up both forks in the correct order.
     * If successful, the philosopher can proceed to eat.
     *
     * @return true if both forks are successfully picked up, false otherwise
     * @throws InterruptedException if the thread is interrupted while picking up forks
     */
    private boolean pickUpForks() throws InterruptedException {
        if (lowerFork.tryLock()) {
            try {
                if (higherFork.tryLock()) {
                    return true;
                } else {
                    lowerFork.unlock(); // Unlock lowerFork if higherFork is not available
                }
            } catch (Exception e) {
                lowerFork.unlock(); // Unlock lowerFork if any exception occurs
                throw e;
            }
        }
        return false;
    }

    /**
     * Simulates the philosopher eating for a random amount of time.
     *
     * @throws InterruptedException if the thread is interrupted while eating
     */
    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    /**
     * Releases both forks after eating.
     */
    private void putDownForks() {
        higherFork.unlock();
        lowerFork.unlock();
    }

    /**
     * Stops the philosopher's lifecycle.
     */
    public void stop() {
        running = false;
    }
}