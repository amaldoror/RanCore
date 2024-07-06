package rancore.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiKeys {
    public static void main(String[] args) {
        System.out.println(readApiKey("NASA_API_KEY"));
    }

    public static String readApiKey(String keyName) {
        Properties properties = new Properties();
        String apiKey = null;
        try {
            properties.load(new FileInputStream("config/api_keys.properties"));
            apiKey = properties.getProperty(keyName);
            if (apiKey == null) {
                System.err.println("API Key with name '" + keyName + "' not found");
            } else {
                return apiKey;
            }
        } catch (IOException e) {
            System.err.println("API Key file not found");
        }
        return apiKey;
    }
}
