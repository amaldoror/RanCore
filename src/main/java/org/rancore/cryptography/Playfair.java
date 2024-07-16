package org.rancore.cryptography;

import java.util.*;

/**
 * <p><u><b>Class name</b></u></p>
 * <p>Playfair</p>
 * <p><u><b>Description</b></u></p>
 * This class implements the Playfair cipher, which was created by Charles Wheatstone in 1854.
 * It was used as a field cipher for tactical communication in World War I.
 * This cipher is a polyalphabetic substitution cipher.
 * It uses an alphabetic key to encipher a text
 */
public class Playfair {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int SIZE = 5;

    private static class Position {
        int row, col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static char[][] createKeySquare(String key) {
        char[][] keySquare = new char[SIZE][SIZE];
        Map<Character, Position> keyPos = new HashMap<>();

        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        Set<Character> usedChars = new HashSet<>();

        int row = 0, col = 0;
        for (char c : key.toCharArray()) {
            if (!usedChars.contains(c)) {
                keySquare[row][col] = c;
                keyPos.put(c, new Position(row, col));
                usedChars.add(c);
                col++;
                if (col == SIZE) {
                    col = 0;
                    row++;
                }
            }
        }

        for (char c : ALPHABET.toCharArray()) {
            if (c != 'J' && !usedChars.contains(c)) {
                keySquare[row][col] = c;
                keyPos.put(c, new Position(row, col));
                col++;
                if (col == SIZE) {
                    col = 0;
                    row++;
                }
            }
        }

        return keySquare;
    }

    private static String preprocessText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder processed = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            processed.append(currentChar);
            if (i + 1 < text.length() && currentChar == text.charAt(i + 1)) {
                processed.append('X');
            }
        }

        if (processed.length() % 2 != 0) {
            processed.append('X');
        }

        return processed.toString();
    }

    private static String encryptPair(char a, char b, char[][] keySquare, Map<Character, Position> keyPos) {
        Position posA = keyPos.get(a);
        Position posB = keyPos.get(b);

        if (posA.row == posB.row) {
            return "" + keySquare[posA.row][(posA.col + 1) % SIZE] + keySquare[posB.row][(posB.col + 1) % SIZE];
        } else if (posA.col == posB.col) {
            return "" + keySquare[(posA.row + 1) % SIZE][posA.col] + keySquare[(posB.row + 1) % SIZE][posB.col];
        } else {
            return "" + keySquare[posA.row][posB.col] + keySquare[posB.row][posA.col];
        }
    }

    private static String decryptPair(char a, char b, char[][] keySquare, Map<Character, Position> keyPos) {
        Position posA = keyPos.get(a);
        Position posB = keyPos.get(b);

        if (posA.row == posB.row) {
            return "" + keySquare[posA.row][(posA.col + SIZE - 1) % SIZE] + keySquare[posB.row][(posB.col + SIZE - 1) % SIZE];
        } else if (posA.col == posB.col) {
            return "" + keySquare[(posA.row + SIZE - 1) % SIZE][posA.col] + keySquare[(posB.row + SIZE - 1) % SIZE][posB.col];
        } else {
            return "" + keySquare[posA.row][posB.col] + keySquare[posB.row][posA.col];
        }
    }

    public static String playfairEncrypt(String text, String key) {
        char[][] keySquare = createKeySquare(key);
        Map<Character, Position> keyPos = new HashMap<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                keyPos.put(keySquare[i][j], new Position(i, j));
            }
        }

        text = preprocessText(text);
        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            encrypted.append(encryptPair(text.charAt(i), text.charAt(i + 1), keySquare, keyPos));
        }

        return encrypted.toString();
    }

    public static String playfairDecrypt(String text, String key) {
        char[][] keySquare = createKeySquare(key);
        Map<Character, Position> keyPos = new HashMap<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                keyPos.put(keySquare[i][j], new Position(i, j));
            }
        }

        // Ensure text length is even
        if (text.length() % 2 != 0) {
            text += 'X';
        }

        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            if (a == b) {
                // If both characters are the same, add only the first character
                decrypted.append(a);
                i--; // Decrement i to handle next pair correctly
            } else {
                decrypted.append(decryptPair(a, b, keySquare, keyPos));
            }
        }

        return decrypted.toString();
    }


    public static void main(String[] args) {
        String key = "thisIsTheKey";
        String text = "hello WORLD";

        String encrypted = playfairEncrypt(text, key);
        String decrypted = playfairDecrypt(encrypted, key);

        System.out.println("Key:\t\t" + key + "\n" +
                            "Text:\t\t" + text + "\n" +
                            "Encrypted:\t" + encrypted + "\n" +
                            "Decrypted:\t" + decrypted);
    }
}
