package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    private static ObjectMapper getObjectMapper(String formatName) {
        ObjectMapper mapper = null;
        if (formatName.endsWith("json")) {
            mapper = new ObjectMapper();
        } else if (formatName.endsWith("yml") || formatName.endsWith("yaml")) {
            mapper = new YAMLMapper();
        }

        return mapper;
    }

    public static Map<String, Object> getTreeMap(String strFromFile, String formatName) throws IOException {
        TreeMap<String, Object> rezTreeMap;
        ObjectMapper mapper = getObjectMapper(formatName);
        rezTreeMap = mapper.readValue(strFromFile, new TypeReference<>() { });
        return rezTreeMap;
    }

}
