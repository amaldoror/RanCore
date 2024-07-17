package org.rancore.generator;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {

    // Define the characters that can be used in the password
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String DIGIT = "0123456789";
    private static final String SPECIAL_CHAR = "!@#$%^&*()-_=+[]{}|;:,.<>?/";

    public static void main(String[] args) {
        int passwordLength = 15; // Length of the password
        Long seed = 12345L; // Example seed value, can be null for non-reproducible passwords

        String password = generatePassword(passwordLength, seed, true, true, true, true);
        System.out.println("Generated Password: " + password);
    }

    public static String generatePassword(int length, Long seed, boolean useLower, boolean useUpper, boolean useDigit, boolean useSpecial) {
        if (length < 1) throw new IllegalArgumentException("Password length must be greater than 0");

        StringBuilder passwordAllowBase = new StringBuilder();
        if (useLower) passwordAllowBase.append(CHAR_LOWER);
        if (useUpper) passwordAllowBase.append(CHAR_UPPER);
        if (useDigit) passwordAllowBase.append(DIGIT);
        if (useSpecial) passwordAllowBase.append(SPECIAL_CHAR);

        if (passwordAllowBase.isEmpty()) throw new IllegalArgumentException("No character sets selected");

        char[] passwordAllow = passwordAllowBase.toString().toCharArray();

        Random random = (seed != null) ? new Random(seed) : new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        if (useLower) password.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
        if (useUpper) password.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
        if (useDigit) password.append(DIGIT.charAt(random.nextInt(DIGIT.length())));
        if (useSpecial) password.append(SPECIAL_CHAR.charAt(random.nextInt(SPECIAL_CHAR.length())));

        for (int i = password.length(); i < length; i++) {
            password.append(passwordAllow[random.nextInt(passwordAllow.length)]);
        }

        return shufflePassword(password.toString(), seed);
    }

    private static String shufflePassword(String password, Long seed) {
        char[] passwordArray = password.toCharArray();
        Random random = (seed != null) ? new Random(seed) : new SecureRandom();

        for (int i = passwordArray.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }

        return new String(passwordArray);
    }
}
