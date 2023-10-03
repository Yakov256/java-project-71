package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import hexlet.code.Differs;

import java.io.IOException;
import java.util.List;

public class Json {

    public static String getFormattedDiffers(List<Differs> diffs){
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder rezStr = new StringBuilder("{\n");

        for (Differs diff: diffs) {
            try {
                //jsonStr = objectMapper.writeValueAsString(diffs);
                rezStr.append(objectMapper.writeValueAsString(diff));
            } catch (JsonProcessingException e) {
                System.out.println("Failed to generate json!");
                System.out.println(e.getMessage());
            }
            rezStr.append("\n");
        }
        rezStr.append("}");

        return rezStr.toString();

    }

}
