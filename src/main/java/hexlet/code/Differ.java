package hexlet.code;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    private static Map<String, String> readMapFromJsonFile(String filePath) throws IOException, ParseException {
        System.out.println("Reading JSON from file: " + filePath);

        Object jObj = new JSONParser().parse(new FileReader(filePath));
        JSONObject jsonObject = (JSONObject) jObj;

        Map<String, String> jsonMap = new HashMap<>();

        for (Object key : jsonObject.keySet()) {
            String keyStr = (String) key;
            Object keyValue = jsonObject.get(keyStr);

            jsonMap.put(keyStr, keyValue.toString());
            //System.out.println("key: "+ keyStr + " value: " + keyValue);
        }
        //System.out.println("Map has been read: " + jsonMap);
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
            /*
            if (map1Value != null) {
                if (map2Value == null) {
                    rezStr.append("- " + entry.getKey() + ": " + map1Value + "\n");
                } else if (map1Value.equals(map2Value)) {
                    rezStr.append("  " + entry.getKey() + ": " + map1Value + "\n");
                } else if (!map1Value.equals(map2Value)) {
                    rezStr.append("- " + entry.getKey() + ": " + map1Value + "\n");
                    rezStr.append("+ " + entry.getKey() + ": " + map2Value + "\n");
                }
            } else {
                if (map2Value != null) {
                    rezStr.append("+ " + entry.getKey() + ": " + map2Value + "\n");
                }
            }
             */
        }
        rezStr.append("}\n");
        return rezStr.toString();
    }

    public static String generate(String filePath1, String filePath2) throws IOException, ParseException {
        Map<String, String> jsonMap1 = readMapFromJsonFile(filePath1);
        Map<String, String> jsonMap2 = readMapFromJsonFile(filePath2);
        return getMapsDifferences(jsonMap1, jsonMap2);
    }
}
