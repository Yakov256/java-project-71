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

    private static Map<String,String> readMapFromJsonFile (String filePath) throws IOException, ParseException {
        System.out.println("Reading JSON from file: " + filePath);
        //String filePath = filePath.toString();
        //BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
        //reader.lines().forEach(System.out::println);

        Object jObj = new JSONParser().parse(new FileReader(filePath));
        JSONObject jsonObject = (JSONObject) jObj;

        Map<String,String> jsonMap = new HashMap<>();
        //Map<String,String> jsonMap = new TreeMap<>();

        for (Object key : jsonObject.keySet()) {
            String keyStr = (String)key;
            Object keyValue = jsonObject.get(keyStr);

            jsonMap.put(keyStr, keyValue.toString());
            System.out.println("key: "+ keyStr + " value: " + keyValue);
        }

        System.out.println("Map has been read: " + jsonMap);
        return jsonMap;
    }

    public static String generate(String filePath1, String filePath2) throws IOException, ParseException {
        Map<String, String> jsonMap1 = readMapFromJsonFile(filePath1);
        Map<String, String> jsonMap2 = readMapFromJsonFile(filePath2);

        System.out.println("-------- Finding diffs --------");
        Map<String, String> jsonDiff = new TreeMap<>();

        for (Map.Entry<String, String> entry : jsonMap1.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            String map2Value = jsonMap2.get(entry.getKey());
            if (map2Value != null) {
                if (!map2Value.equals(entry.getValue())) {
                    System.out.println("- " + entry.getKey() + " : " + entry.getValue());
                    System.out.println("+ " + entry.getKey() + " : " + map2Value);
                    jsonDiff.put(entry.getKey(), "-");
                    jsonDiff.put(entry.getKey(), "+");
                } else {
                    System.out.println("  " + entry.getKey() + " : " + entry.getValue());
                    jsonDiff.put(entry.getKey(), " ");
                }
            } else {
                System.out.println("- " + entry.getKey() + " : " + entry.getValue() + " - " + map2Value);
                jsonDiff.put(entry.getKey(), "-");
            }

            // Оcталось вывести разницу между map2 - map1 со знаком +
            //System.out.println(map2Value);
        }


        for (Map.Entry<String, String> entry : jsonMap2.entrySet()) {
            String map1Value = jsonMap1.get(entry.getKey());
            if (map1Value == null) {
                System.out.println("+ " + entry.getKey() + " : " + map1Value);
                jsonDiff.put(entry.getKey(), "+");
            }
        }


        System.out.println();
        System.out.println("--- sorted rezult ---");
        System.out.println(jsonDiff);

        System.out.println("generate");
        return "";
    }
}
