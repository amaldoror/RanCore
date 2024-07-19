package org.rancore.generator;

import java.security.SecureRandom;
import java.util.Random;

public class PronounceablePasswordGenerator {

    private static final String VOWELS = "aeiou";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";

    public static void main(String[] args) {
        int length = 8;
        Long seed = 12345L;

        String password = generatePassword(length, seed);
        System.out.println("Generated Password: " + password);
    }

    public static String generatePassword(int length, Long seed) {
        if (length < 1) throw new IllegalArgumentException("Password length must be greater than 0");

        Random random = (seed != null) ? new Random(seed) : new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        boolean startWithVowel = random.nextBoolean();
        for (int i = 0; i < length; i++) {
            if ((i % 2 == 0 && startWithVowel) || (i % 2 == 1 && !startWithVowel)) {
                password.append(VOWELS.charAt(random.nextInt(VOWELS.length())));
            } else {
                password.append(CONSONANTS.charAt(random.nextInt(CONSONANTS.length())));
            }
        }

        return password.toString();
    }
}
