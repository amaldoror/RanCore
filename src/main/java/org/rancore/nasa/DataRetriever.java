package org.rancore.nasa;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.JsonNode;

import org.rancore.utils.FileHelper;

import static org.rancore.utils.ApiKeyHelper.readApiKey;
import static org.rancore.utils.FileHelper.saveToFile;
import static org.rancore.utils.JsonHelper.formatJson;
import static org.rancore.utils.JsonHelper.parseJson;

public class DataRetriever {
    private static final String FILE_PATH_JSON = "json/";
    private static final String FILE_PATH_OUTPUT = "output/";
    private static final String DEMO_API_KEY = "DEMO_KEY";
    private static final String NASA_API_KEY = readApiKey("NASA_API_KEY");
    private static final String MARS_QUERY = "https://api.nasa.gov/insight_weather/?api_key="
            + DEMO_API_KEY + "&feedtype=json&ver=1.0";

    public static void main(String[] args) {
        try {
            URL url = new URL(MARS_QUERY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String jsonString = response.toString();

                // Parse and process the JSON
                JsonNode jsonNode = parseJson(jsonString);

                // Generate readable output
                String readableOutput = formatJson(jsonNode);

                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTime = currentTime.format(formatter);
                System.out.println("Formatted time: " + formattedTime);

                // Save JSON to file
                saveToFile(readableOutput, FILE_PATH_JSON + "nasa_data_" + FileHelper.generateTimestampForFilename() + ".json");

                // Print readable output to console
                System.out.println(readableOutput);

            } else {
                System.out.println("Error: " + responseCode);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }




}