package rancore.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class Json {
    public static JsonNode parseJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(jsonString);
    }

    public static String formatJson(JsonNode jsonNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectMapper.writeValueAsString(jsonNode);
        } catch (Exception e) {
            System.err.println("Error formatting JSON: " + e.getMessage());
            return jsonNode.toString();
        }
    }
}
