package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

//import com.fasterxml.jackson.core.JsonToken;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
//import org.yaml.snakeyaml.Yaml;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

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

    public static TreeMap<String, Object> readTreeMapFromFile(String filePath) throws IOException {
        TreeMap<String, Object> rezTreeMap;
        ObjectMapper mapper = getObjectMapper(filePath);

        String strFromFile = new String(Files.readAllBytes(Paths.get(filePath)));
        rezTreeMap = mapper.readValue(strFromFile, new TypeReference<>() { });


        // "Искуственно" исправляем, проблему когда текстовое "null" распознается как null
        for (Map.Entry<String, Object> entry : rezTreeMap.entrySet()) {
            if (entry.getValue() == null) {
                entry.setValue("null");
            }
        }

        return rezTreeMap;
    }

}
