package org.rancore.generator;

import java.util.UUID;

public class UUIDGenerator {
    public static UUID generate() {
        return UUID.randomUUID();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(UUIDGenerator.generate());
        }
    }
}
