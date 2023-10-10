package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    private static ObjectMapper getObjectMapper(String filePath) {
        ObjectMapper mapper = null;
        if (filePath.endsWith("json")) {
            mapper = new ObjectMapper();
        } else if (filePath.endsWith("yml") || filePath.endsWith("yaml")) {
            mapper = new YAMLMapper();
        }

        return mapper;
    }

    public static Map<String, Object> getTreeMap(String strFromFile, String filePath) throws IOException {
        TreeMap<String, Object> rezTreeMap;
        ObjectMapper mapper = getObjectMapper(filePath);
        rezTreeMap = mapper.readValue(strFromFile, new TypeReference<>() { });

        return rezTreeMap;
    }

}
