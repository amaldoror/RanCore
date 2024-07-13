package org.rancore.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.rancore.crypto.SHA3Example.bytesToHex;

public class KSHA3Example {
    public static void main(String[] args) {
        String input = "abc";
        String expectedHash = "3a985da74fe225b2045c172d6bd390bd855f086e3e9d525b46bfe24511431532";
        String actualHash = KSHA3.calculateHash(input);
        System.out.println("Input: " + input);
        System.out.println("Expected: " + expectedHash);
        System.out.println("Actual: " + actualHash);
        System.out.println("Correct: " + expectedHash.equals(actualHash));


        String ksha3Hash = KSHA3.calculateHash(input);
        String javaHash = calculateJavaSHA3(input);

        System.out.println("Input: " + (input.length() > 20 ? input.substring(0, 20) + "..." : input));
        System.out.println("KSHA3 Hash:  " + ksha3Hash);
        System.out.println("Java Hash:   " + javaHash);
        System.out.println("Match: " + ksha3Hash.equals(javaHash));
        System.out.println();
    }

    private static String calculateJavaSHA3(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA3-256");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA3-256 algorithm not available", e);
        }
    }
}

