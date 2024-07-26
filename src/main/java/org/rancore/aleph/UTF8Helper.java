package org.rancore.aleph;

import java.nio.charset.StandardCharsets;

public class UTF8Helper {
    private static final byte[] utf8Bytes = {72, 101, 108, 108, 111, 44, 32, 85, 84, 70, 45, 56, 33}; // "Hello, UTF-8!"
    private static final String COMPLETE =
            " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
    private static final String ALPHABET_SMALL = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHABET_CAPITAL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";

    public static void main(String[] args) {
        encodeString(ALPHABET_SMALL);
        encodeString(ALPHABET_CAPITAL);
        encodeString(NUMBERS);
        encodeString(COMPLETE);
        byte[] bytes = generateBytes(95);
        System.out.println(decodeBytes(bytes));
        System.out.println(decodeBytes(utf8Bytes));
    }

    public static byte[] generateBytes(int n){
        byte[] b = new byte[n];
        for (int i = 0; i<n; i++){
            b[i] = (byte)(i+32);
        }
        return b;
    }

    public static void encodeString(String str) {
        byte[] utf8Bytes = str.getBytes(StandardCharsets.UTF_8);
        for (byte b : utf8Bytes) {
            System.out.print(b + " ");
        }
        System.out.println();
    }

    public static String decodeBytes(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
