package hexlet.code;

import com.fasterxml.jackson.core.JsonToken;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
//import org.yaml.snakeyaml.Yaml;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, String> readMapFromJsonFile(String filePath) throws IOException, ParseException {
        System.out.println("Reading JSON file: " + filePath);

        Object jObj = new JSONParser().parse(new FileReader(filePath));
        JSONObject jsonObject = (JSONObject) jObj;

        Map<String, String> jsonMap = new HashMap<>();

        for (Object key : jsonObject.keySet()) {
            String keyStr = (String) key;
            Object keyValue = jsonObject.get(keyStr);
            jsonMap.put(keyStr, keyValue.toString());
        }

        return jsonMap;
    }

    public static Map<String, String> readMapFromYmlFile(String filePath) throws IOException, ParseException {
        System.out.println("Reading YAML file: " + filePath);

        File file = new File(filePath);
        YAMLFactory yamlFactory = new YAMLFactory();
        YAMLParser yamlParser = yamlFactory.createParser(file);
        JsonToken jsonToken = yamlParser.nextToken();
        Map<String, String> yamlMap = new HashMap<>();
        String fieldName = "";
        while (jsonToken != null) {
            switch (jsonToken) {
                case FIELD_NAME: fieldName = yamlParser.getText();
                    //System.out.println("Key field: " + yamlParser.getText());
                    break;
                case START_OBJECT: //System.out.println("Object Started");
                    break;
                case END_OBJECT: //System.out.println("Object Ended");
                    break;
                case START_ARRAY: //System.out.println("Array Started");
                    break;
                case END_ARRAY: //System.out.println("Array Ended");
                    break;
                //case VALUE_FALSE: //case VALUE_NULL: //case VALUE_NUMBER_FLOAT:
                //case VALUE_NUMBER_INT: //case VALUE_STRING: //case VALUE_TRUE:
                default:
                    if (!fieldName.equals("")) {
                        yamlMap.put(fieldName, yamlParser.getText());
                        fieldName = "";
                    }
                    break;
            }

            jsonToken = yamlParser.nextToken();
        }

        yamlParser.close();
        return yamlMap;
    }

}
