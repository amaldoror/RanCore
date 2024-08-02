package org.rancore.ad.util;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadBinaryFile {
    public static void main(String[] args) {
        String fileName = "src/resources/4runs.bin";

        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            while (dis.available() > 0) {
                int b = dis.read();
                System.out.println(b);
            }
            while (dis.available() > 0) {
                int i = dis.readInt();
                System.out.println(i);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
