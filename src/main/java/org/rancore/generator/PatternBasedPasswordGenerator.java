package org.rancore.generator;

import java.security.SecureRandom;
import java.util.Random;

public class PatternBasedPasswordGenerator {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String DIGIT = "0123456789";

    public static void main(String[] args) {
        String pattern = "LUDLUD";
        Long seed = 12345L;

        String password = generatePassword(pattern, seed);
        System.out.println("Generated Password: " + password);
    }

    public static String generatePassword(String pattern, Long seed) {
        if (pattern == null || pattern.isEmpty()) throw new IllegalArgumentException("Pattern must not be empty");

        Random random = (seed != null) ? new Random(seed) : new SecureRandom();
        StringBuilder password = new StringBuilder(pattern.length());

        for (char c : pattern.toCharArray()) {
            switch (c) {
                case 'L':
                    password.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
                    break;
                case 'U':
                    password.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
                    break;
                case 'D':
                    password.append(DIGIT.charAt(random.nextInt(DIGIT.length())));
                    break;
                default:
                    throw new IllegalArgumentException("Pattern contains invalid character: " + c);
            }
        }

        return password.toString();
    }
}
