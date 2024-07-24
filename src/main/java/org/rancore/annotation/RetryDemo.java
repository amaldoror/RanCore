package org.rancore.annotation;

import java.util.Random;

/**
 * This class demonstrates how to use the {@link Retry} annotation.
 *
 *
 */
public class RetryDemo implements IRetry {
    private static final Random random = new Random();


    /**
     * Implementation of the method to retry. Should be declared in an interface.
     * The class that defines the method must implement that interface.
     * @throws RetryFailedException Exception when the operation fails after a number of attempts.
     */
    @Override
    @Retry(maxAttempts = 10, delay = 2000)
    public void retryMethod() throws RetryFailedException {
        if (random.nextDouble() < 0.9) {  // 50% Chance auf Fehler
            throw new RetryFailedException();
        }
        System.out.println("Operation successful!");
    }


    /**
     * <p>Demonstration of usage:</p>
     * <ul>
     *     <li>Create a proxy of the class that defines the method to retry.</li>
     *     <li>Call method in a try-catch block</li>
     *     <li>Catch {@link RetryFailedException}</li>
     * </ul>
     * @param args args
     */
    public static void main(String[] args) {
        IRetry retryService = RetryHandler.createProxy(new RetryDemo(), IRetry.class);

        try {
            retryService.retryMethod();
        } catch (RetryFailedException e) {
            System.err.println(e.getMessage());
        }
    }
}