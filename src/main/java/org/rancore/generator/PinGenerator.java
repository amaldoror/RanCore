package org.rancore.generator;

import java.security.SecureRandom;
import java.util.Random;

public class PinGenerator {

    public static void main(String[] args) {
        int pinLength = 6;
        Long seed = 12345L;

        String pin = generatePin(pinLength, seed);
        System.out.println("Generated PIN: " + pin);
    }

    public static String generatePin(int length, Long seed) {
        if (length < 1) throw new IllegalArgumentException("PIN length must be greater than 0");

        Random random = (seed != null) ? new Random(seed) : new SecureRandom();
        StringBuilder pin = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            pin.append(random.nextInt(10)); // Append a digit (0-9)
        }

        return pin.toString();
    }
}
