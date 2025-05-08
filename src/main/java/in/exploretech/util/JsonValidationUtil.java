package in.exploretech.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class JsonValidationUtil {

    public static List<String> getMissingFields(JsonNode jsonNode, String... requiredFields) {
        List<String> missingFields = new ArrayList<>();

        for (String field : requiredFields) {
            JsonNode valueNode = jsonNode.get(field);
            if (valueNode == null || valueNode.isNull() || valueNode.asText().trim().isEmpty()) {
                missingFields.add(field);
            }
        }

        return missingFields;
    }
}

