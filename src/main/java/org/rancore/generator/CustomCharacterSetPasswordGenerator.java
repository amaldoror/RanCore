package org.rancore.generator;

import java.security.SecureRandom;
import java.util.Random;

public class CustomCharacterSetPasswordGenerator {

    public static void main(String[] args) {
        int passwordLength = 12;
        Long seed = 12345L;
        String customCharacterSet = "abcABC123!@#";

        String password = generatePassword(passwordLength, seed, customCharacterSet);
        System.out.println("Generated Password: " + password);
    }

    public static String generatePassword(int length, Long seed, String customCharacterSet) {
        if (length < 1) throw new IllegalArgumentException("Password length must be greater than 0");
        if (customCharacterSet == null || customCharacterSet.isEmpty()) throw new IllegalArgumentException("Custom character set must not be empty");

        char[] passwordAllow = customCharacterSet.toCharArray();
        Random random = (seed != null) ? new Random(seed) : new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(passwordAllow.length);
            password.append(passwordAllow[randomIndex]);
        }

        return password.toString();
    }
}
