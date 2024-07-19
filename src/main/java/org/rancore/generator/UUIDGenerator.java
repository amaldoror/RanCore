package org.rancore.generator;

import static org.rancore.utils.FileHelper.saveToFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class UUIDGenerator {
    private static final int NUM_UUIDS = 10;
    private static final String outputFilePath = "src/main/resources/uuids.txt";

    public static void main(String[] args) {
        List<String> uuids = new ArrayList<>(NUM_UUIDS);

        for (int i = 0; i < NUM_UUIDS; i++) {
            UUID uuid = UUIDGenerator.generate();
            System.out.println("UUID " + i + ":\t" + uuid);
            uuids.add(uuid.toString());
        }

        // Join the UUIDs with a newline separator
        String uuidsString = String.join("\n", uuids);
        saveToFile(uuidsString, outputFilePath);
    }

    public static UUID generate() {
        return UUID.randomUUID();
    }
}