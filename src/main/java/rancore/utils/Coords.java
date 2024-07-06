package rancore.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Coords {
    public static void main(String[] args) {
        String coordsKey = "DIEDENHOFER_STR";
        System.out.println("Coords for " + coordsKey + ": " + readCoordsKey(coordsKey));
    }

    public static String readCoordsKey(String coordsKey) {
        Properties properties = new Properties();
        String coords = "";
        try {
            properties.load(new FileInputStream("config/coords.properties"));
            coords = properties.getProperty(coordsKey);
            if (coords == null) {
                System.err.println("Coords with key '" + coordsKey + "' not found");
            } else {
                return coords;
            }
        } catch (IOException e) {
            System.err.println("Coords Key file not found");
        }
        return coords;
    }
}
