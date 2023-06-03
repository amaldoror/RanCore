package rancore.space;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ISSDataRetriever {
    public static void main(String[] args) {
        try {
            final String apiKey = "YrOoswKCYJBFhxNcDAu9ZhsQfedyjl71TKlqedbx";

            URL url = new URL("https://api.nasa.gov/iss-now.json?api_key=" + apiKey);
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

                // Process the response data
                System.out.println(response);


                // Parse the JSON response
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.toString());

                // Extract the latitude and longitude values
                String latitude = jsonNode.get("iss_position").get("latitude").asText();
                String longitude = jsonNode.get("iss_position").get("longitude").asText();

                // Display the latitude and longitude
                System.out.println("Latitude: " + latitude);
                System.out.println("Longitude: " + longitude);

            } else {
                System.out.println("Error: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
