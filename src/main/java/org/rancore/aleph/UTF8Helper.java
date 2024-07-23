package org.rancore.aleph;

import java.nio.charset.StandardCharsets;

public class UTF8Helper {
    static byte[] utf8Bytes = {72, 101, 108, 108, 111, 44, 32, 85, 84, 70, 45, 56, 33}; // "Hello, UTF-8!"

    public static void main(String[] args) {
        //encodeString("abcdefghijklmnopqrstuvwxyz");
        //encodeString("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        //encodeString("0123456789");
        byte[] bytes = generateBytes(1000);
        System.out.println(decodeBytes(bytes));
    }

    public static byte[] generateBytes(int n){
        byte[] b = new byte[n];
        for (int i = 0; i<n; i++){
            b[i] = (byte)i;
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
