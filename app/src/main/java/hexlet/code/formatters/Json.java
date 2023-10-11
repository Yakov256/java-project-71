package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {


    public static String getFormattedDiffers(List<Map<String, Object>> diffs) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder rezStr = new StringBuilder();

        for (Map<String, Object> diff: diffs) {
            try {
                rezStr.append(objectMapper.writeValueAsString(diff));
            } catch (JsonProcessingException e) {
                System.out.println("Failed to generate json!");
                System.out.println(e.getMessage());
            }
            rezStr.append("\n");
        }

        return rezStr.toString();
    }

}
