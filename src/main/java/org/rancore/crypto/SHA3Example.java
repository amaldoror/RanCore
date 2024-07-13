package org.rancore.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class SHA3Example {
    public static void main(String[] args) {
        String input = "Hallo, Welt!";
        try {
            byte[] hash = calculateSHA3(input);
            String hexHash = bytesToHex(hash);
            System.out.println("Input: " + input);
            System.out.println("Hash:  " + hexHash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Der angeforderte Algorithmus ist nicht vorhanden!");
        }
    }

    public static byte[] calculateSHA3(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        return digest.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
