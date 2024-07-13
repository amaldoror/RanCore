package org.rancore.annotation;

import java.lang.reflect.Proxy;
import java.util.Random;

public class RetryDemo {
    private static final Random random = new Random();

    public interface RetryInterface {
        void retryMethod() throws Exception;
    }

    public static class RetryImplementation implements RetryInterface {
        @Retry(maxAttempts = 10, delay = 2000)
        public void retryMethod() throws Exception {
            if (random.nextDouble() < 0.5) {  // 50% Chance auf Fehler
                throw new Exception("Operation failed");
            }
            System.out.println("Operation successful!");
        }
    }

    public static void main(String[] args) throws Exception {
        RetryInterface implementation = new RetryImplementation();
        RetryInterface proxy = (RetryInterface) Proxy.newProxyInstance(
                RetryDemo.class.getClassLoader(),
                new Class<?>[] { RetryInterface.class },
                new RetryHandler(implementation)
        );

        try {
            proxy.retryMethod();
        } catch (Exception e) {
            System.out.println("Operation ultimately failed after all attempts: " + e.getMessage());
        }
    }
}