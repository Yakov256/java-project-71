package hexlet.code;

import com.fasterxml.jackson.core.JsonToken;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import java.io.File;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Differ {

    public static Map<String, String> readMapFromJsonFile(String filePath) throws IOException, ParseException {
        System.out.println("Reading JSON from file: " + filePath);

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
        System.out.println("Reading JSON from file: " + filePath);

        File file = new File(filePath);
        YAMLFactory yamlFactory = new YAMLFactory();
        YAMLParser yamlParser = yamlFactory.createParser(file);
        JsonToken jsonToken = yamlParser.nextToken();
        Map<String, String> yamlMap = new HashMap<>();
        String fieldName = "";
        String fieldValue = "";
        while (jsonToken != null) {
            switch (jsonToken) {
                //System.out.println("--- " + jsonToken.toString());
                case FIELD_NAME:fieldName = yamlParser.getText();
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
                //case VALUE_FALSE:
                //case VALUE_NULL:
                //case VALUE_NUMBER_FLOAT:
                //case VALUE_NUMBER_INT:
                //case VALUE_STRING:
                //case VALUE_TRUE:
                default: fieldValue = yamlParser.getText();
                    //System.out.println("Key value: " + yamlParser.getText() + " - " + i);
                    if (!fieldValue.equals("")) {
                        yamlMap.put(fieldName, fieldValue);
                        fieldName = "";
                    }
                    break;
            }

            jsonToken = yamlParser.nextToken();
        }
        yamlParser.close();

        return yamlMap;
    }

    private static Map<String, String> readMapFromFile(String filePath) throws IOException, ParseException {
        Map<String, String> jsonMap = new HashMap<>();

        System.out.println(filePath);
        if (filePath.endsWith("json")) {
            //System.out.println("Обрабатываем json");
            jsonMap = readMapFromJsonFile(filePath);
        } else if (filePath.endsWith("yml")) {
            //System.out.println("Обрабатываем yml");
            jsonMap = readMapFromYmlFile(filePath);
        }

        return jsonMap;
    }

    public static String getLineDifferences(String key, String map1Value, String map2Value) {
        StringBuilder rezStr = new StringBuilder();
        if (map1Value != null) {
            if (map2Value == null) {
                rezStr.append("- " + key + ": " + map1Value + "\n");
            } else if (map1Value.equals(map2Value)) {
                rezStr.append("  " + key + ": " + map1Value + "\n");
            } else if (!map1Value.equals(map2Value)) {
                rezStr.append("- " + key + ": " + map1Value + "\n");
                rezStr.append("+ " + key + ": " + map2Value + "\n");
            }
        } else {
            if (map2Value != null) {
                rezStr.append("+ " + key + ": " + map2Value + "\n");
            }
        }
        return rezStr.toString();
    }

    public static String getMapsDifferences(Map<String, String> map1, Map<String, String> map2) throws ParseException {

        // Получаем отсортированный Map с ключами из двух json файлов
        Map<String, String> keysFromBothJson = new TreeMap<>();
        keysFromBothJson.putAll(map1);
        keysFromBothJson.putAll(map2);

        StringBuilder rezStr = new StringBuilder("{\n");

        for (Map.Entry<String, String> entry : keysFromBothJson.entrySet()) {
            String map1Value = map1.get(entry.getKey());
            String map2Value = map2.get(entry.getKey());
            rezStr.append(getLineDifferences(entry.getKey(), map1Value, map2Value));
        }

        rezStr.append("}\n");
        return rezStr.toString();
    }

    public static String generate(String filePath1, String filePath2) throws IOException, ParseException {
        //readMapFromFile(filePath1);
        Map<String, String> jsonMap1 = readMapFromFile(filePath1);
        Map<String, String> jsonMap2 = readMapFromFile(filePath2);
        return getMapsDifferences(jsonMap1, jsonMap2);
    }
}
