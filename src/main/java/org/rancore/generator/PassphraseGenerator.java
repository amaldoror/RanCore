package org.rancore.generator;

import java.security.SecureRandom;
import java.util.Random;

public class PassphraseGenerator {

    private static final String[] WORD_LIST = {
            "apfel", "orange", "banane", "traube", "pfirsich", "kirsche", "pflaume", "mango", "kiwi", "erdbeere"
    };

    public static void main(String[] args) {
        int wordCount = 4;
        Long seed = 12345L;

        String passphrase = generatePassphrase(wordCount, seed);
        System.out.println("Generated Passphrase: " + passphrase);
    }

    public static String generatePassphrase(int wordCount, Long seed) {
        if (wordCount < 1) throw new IllegalArgumentException("Word count must be greater than 0");

        Random random = (seed != null) ? new Random(seed) : new SecureRandom();
        StringBuilder passphrase = new StringBuilder();

        for (int i = 0; i < wordCount; i++) {
            int randomIndex = random.nextInt(WORD_LIST.length);
            passphrase.append(WORD_LIST[randomIndex]);
            if (i < wordCount - 1) {
                passphrase.append("-");
            }
        }

        return passphrase.toString();
    }
}
