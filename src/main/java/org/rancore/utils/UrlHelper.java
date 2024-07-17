package org.rancore.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UrlHelper {
    private static Properties properties;

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config/urls.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading urls.properties");
        }
    }

    public static String readUrl(String key) {
        return properties.getProperty(key);
    }
}
