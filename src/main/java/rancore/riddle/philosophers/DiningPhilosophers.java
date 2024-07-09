package rancore.riddle.philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The DiningPhilosophers class models the classic "Dining Philosophers" problem.
 * This problem illustrates synchronization issues and potential deadlock scenarios
 * in concurrent programming.
 * <p><br>
 * The main method sets up and starts the simulation, where multiple philosophers
 * alternately think and eat while sharing forks. Each philosopher needs two forks to eat,
 * and this class ensures that philosophers pick up and put down forks in a way
 * that minimizes the risk of deadlock.
 * <p><br>
 * This class also includes a mechanism to stop the simulation gracefully after a specified time.
 */
public class DiningPhilosophers {

    static final int SIMLUATION_TIME = 10_000;  // Simulation time in milliseconds
    static final int NUM_PHILOSOPHERS = 5;      // Number of philosophers
    static final Lock[] FORKS = new ReentrantLock[NUM_PHILOSOPHERS];
    static final Philosopher[] PHILOSOPHERS = new Philosopher[NUM_PHILOSOPHERS];
    static final Thread[] THREADS = new Thread[NUM_PHILOSOPHERS];

    /**
     * The entry point of the simulation. Initializes the philosophers and their forks,
     * starts the philosopher threads, and includes a mechanism to stop the simulation after
     * a specified duration.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize forks
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            FORKS[i] = new ReentrantLock();
        }

        // Initialize philosophers and start their threads
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            Lock leftFork = FORKS[i];
            Lock rightFork = FORKS[(i + 1) % NUM_PHILOSOPHERS];
            PHILOSOPHERS[i] = new Philosopher(i, leftFork, rightFork);
            THREADS[i] = new Thread(PHILOSOPHERS[i]);
            THREADS[i].start();
        }

        // Example of stopping the simulation after some time
        try {
            Thread.sleep(SIMLUATION_TIME); // Let them dine for n seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Signal all philosophers to stop
        for (Philosopher philosopher : PHILOSOPHERS) {
            philosopher.stop();
        }

        // Wait for all threads to finish
        for (Thread thread : THREADS) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}