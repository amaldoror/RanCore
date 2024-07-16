package org.rancore.cryptography;
import java.nio.charset.StandardCharsets;


/**
 * <p><u><b>Class Name</b></u></p>
 * <p>KSHA3</p>
 *
 * <p><u><b>Description</b></u></p>
 *
 * KSHA3 is an implementation of the Keccak-f function, which is a component of the SHA-3 cryptographic hash function.
 * The calculateHash method takes an input string, converts it to bytes using UTF-8 encoding,
 * and then applies the Keccak-f function to generate a hash value.
 * The resulting hash is returned as a hexadecimal string.
 * <p>
 * Code breakdown:
 * calculateHash() receives a string input as input.
 * The input string is converted to bytes using the UTF-8 encoding.
 * keccakF() is called with the input bytes to perform the Keccak-f operation and obtain the encrypted bytes.
 * The encrypted bytes are converted to a hexadecimal string using the bytesToHex method.
 * The hexadecimal string representing the hash value is returned as the result.
 * <p>
 * The keccakF method performs the Keccak-f operation on the input state.
 * It consists of multiple rounds, each of which applies different transformations to the state.
 * The rounds include operations such as theta, rho, pi, chi, and iota.
 * These operations modify the state to achieve diffusion and confusion,
 * which are important properties of cryptographic hash functions.
 * <p>
 * The other methods in the code are utility methods for converting between bytes and longs,
 * reshaping and flattening the state, and performing bitwise operations required for the Keccak-f algorithm.
 * <p>
 * It's important to note that the code is specific to the SHA-3-256 variant, where the hash size is 256 bits.
 * The constants and parameters used in the code are tailored for this specific variant.
 */
public class KSHA3 {
    private static final int STATE_SIZE = 1600; // State size in bits
    private static final int RATE = 1088; // Rate for SHA3-256 (in bits)
    private static final int CAPACITY = 512; // Capacity for SHA3-256 (in bits)
    private static final int OUTPUT_LENGTH = 256 / 8; // Output length in bytes

    // Round-constants for Keccak-f
    private static final long[] ROUND_CONSTANTS = {
            0x0000000000000001L, 0x0000000000008082L, 0x800000000000808AL,
            0x8000000080008000L, 0x000000000000808BL, 0x0000000080000001L,
            0x8000000080008081L, 0x8000000000008009L, 0x000000000000008AL,
            0x0000000000000088L, 0x0000000080008009L, 0x000000008000000AL,
            0x000000008000808BL, 0x800000000000008BL, 0x8000000000008089L,
            0x8000000000008003L, 0x8000000000008002L, 0x8000000000000080L,
            0x000000000000800AL, 0x800000008000000AL, 0x8000000080008081L,
            0x8000000000008080L, 0x0000000080000001L, 0x8000000080008008L
    };

    private static final int[][] RHO_OFFSETS = {
            {0, 36, 3, 41, 18},
            {1, 44, 10, 45, 2},
            {62, 6, 43, 15, 61},
            {28, 55, 25, 21, 56},
            {27, 20, 39, 8, 14}
    };

    private static final int[][] X_COORDINATES = {
            {0, 1, 2, 3, 4},
            {1, 2, 3, 4, 0},
            {2, 3, 4, 0, 1},
            {3, 4, 0, 1, 2},
            {4, 0, 1, 2, 3}
    };

    private static final int[][] Y_COORDINATES = {
            {0, 1, 2, 3, 4},
            {1, 2, 3, 4, 0},
            {2, 3, 4, 0, 1},
            {3, 4, 0, 1, 2},
            {4, 0, 1, 2, 3}
    };

    /**
     * Calculates the hash value of the input string using the Keccak-f function.
     *
     * @param input The input string to be hashed.
     * @return The hexadecimal string representing the hash value.
     */
    public static String calculateHash(String input) {
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] paddedInput = padInput(inputBytes);
        byte[] encrypted = keccakF(paddedInput);
        return bytesToHex(encrypted);
    }

    private static byte[] padInput(byte[] input) {
        int rateBytes = RATE / 8;
        int inputLengthBytes = input.length;
        int paddingLength = rateBytes - (inputLengthBytes % rateBytes);
        if (paddingLength == 1) {
            paddingLength += rateBytes;
        }

        byte[] paddedInput = new byte[inputLengthBytes + paddingLength];
        System.arraycopy(input, 0, paddedInput, 0, inputLengthBytes);
        paddedInput[inputLengthBytes] = 0x06; // Domain separator for SHA3-256
        paddedInput[paddedInput.length - 1] |= (byte) 0x80; // Last bit to 1

        return paddedInput;
    }

    /**
     * Performs the Keccak-f operation on the input state.
     *
     * @param input The input byte array representing the state.
     * @return The resulting byte array after applying Keccak-f.
     */
    static byte[] keccakF(byte[] input) {
        int rateBytes = RATE / 8;
        long[] state = new long[STATE_SIZE / 64];

        // Absorb input
        for (int i = 0; i < input.length; i += rateBytes) {
            for (int j = 0; j < rateBytes; j++) {
                if (i + j < input.length) {
                    state[j / 8] ^= ((long) (input[i + j] & 0xFF)) << (8 * (j % 8));
                }
            }
            keccakFRound(state);
        }

        // Squeeze output
        byte[] output = new byte[OUTPUT_LENGTH];
        int offset = 0;
        while (offset < output.length) {
            for (int i = 0; i < rateBytes && offset < output.length; i++) {
                output[offset++] = (byte) (state[i / 8] >> (8 * (i % 8)));
            }
            if (offset < output.length) {
                keccakFRound(state);
            }
        }

        return output;
    }

    private static void keccakFRound(long[] state) {
        for (int round = 0; round < 24; round++) {
            theta(state);
            rho(state);
            pi(state);
            chi(state);
            iota(state, round);
        }
    }

    /**
     * Converts a byte array to a hexadecimal string.
     *
     * @param bytes The input byte array.
     * @return The resulting hexadecimal string.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Applies the theta step of the Keccak-f function.
     *
     * @param state The 2D long array representing the state.
     */
    private static void theta(long[] state) {
        long[] c = new long[5];
        long[] d = new long[5];

        for (int x = 0; x < 5; x++) {
            c[x] = state[x] ^ state[x + 5] ^ state[x + 10] ^ state[x + 15] ^ state[x + 20];
        }

        for (int x = 0; x < 5; x++) {
            d[x] = c[(x + 4) % 5] ^ rotateLeft(c[(x + 1) % 5], 1);
            for (int y = 0; y < 5; y++) {
                state[x + 5 * y] ^= d[x];
            }
        }
    }

    /**
     * Applies the rho step of the Keccak-f function.
     *
     * @param state The 2D long array representing the state.
     */
    private static void rho(long[] state) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                state[x + 5 * y] = rotateLeft(state[x + 5 * y], RHO_OFFSETS[y][x]);
            }
        }
    }

    /**
     * Applies the pi step of the Keccak-f function.
     *
     * @param state The 2D long array representing the state.
     */
    private static void pi(long[] state) {
        long[] tempState = new long[25];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                int newX = Y_COORDINATES[x][y];
                int newY = X_COORDINATES[x][y];
                tempState[newX + 5 * newY] = state[x + 5 * y];
            }
        }
        System.arraycopy(tempState, 0, state, 0, 25);
    }

    /**
     * Applies the chi step of the Keccak-f function.
     *
     * @param state The 2D long array representing the state.
     */
    private static void chi(long[] state) {
        long[] tempState = new long[5];
        for (int y = 0; y < 5; y++) {
            System.arraycopy(state, y * 5, tempState, 0, 5);
            for (int x = 0; x < 5; x++) {
                state[x + 5 * y] = tempState[x] ^ ((~tempState[(x + 1) % 5]) & tempState[(x + 2) % 5]);
            }
        }
    }

    /**
     * Applies the iota step of the Keccak-f function.
     *
     * @param state The 2D long array representing the state.
     * @param round The current round number.
     */
    private static void iota(long[] state, int round) {
        state[0] ^= ROUND_CONSTANTS[round];
    }

    /**
     * Rotates a long value to the left by the specified offset.
     *
     * @param value The input long value.
     * @param offset The number of bits to rotate.
     * @return The resulting long value after rotation.
     */
    private static long rotateLeft(long value, int offset) {
        return (value << offset) | (value >>> (64 - offset));
    }

}

