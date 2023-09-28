package hexlet.code;

import org.json.simple.parser.ParseException;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.Parser.readTreeMapFromFile;

public class Differ {

    /*private static Map<String, String> readMapFromFile(String filePath) throws Exception {
        //Map<String, String> mapFromFile = new HashMap<>();
        TreeMap<String, Object> treeMapFromFile = new TreeMap<>();
        if (filePath.endsWith("json")) {
            mapFromFile = Parser.readMapFromJsonFile(filePath);
        } else if (filePath.endsWith("yml")) {
            mapFromFile = Parser.readMapFromYmlFile(filePath);
        }
        treeMapFromFile = readTreeMapFromFile(filePath);
        return mapFromFile;
    }*/
    //private static TreeMap<String, Object> readMapFromFile(String filePath) throws Exception {
        //return readTreeMapFromFile(filePath);
    //}

    public static String getLineDifferences(String key, String map1Value, String map2Value) {
        StringBuilder rezStr = new StringBuilder();
        if (map1Value != null) {
            if (map2Value == null) { //удалено во тором файле
                rezStr.append("- " + key + ": " + map1Value + "\n");
                //rezStr.append("+ " + key + ": " + map2Value + "\n");
            } else if (map1Value.equals(map2Value)) { // значения равны
                rezStr.append("  " + key + ": " + map1Value + "\n");
            } else { //else if (!map1Value.equals(map2Value))  //изменено во тором файле
                rezStr.append("- " + key + ": " + map1Value + "\n");
                rezStr.append("+ " + key + ": " + map2Value + "\n");
            }
        } else {
            if (map2Value != null) {
                //rezStr.append("- " + key + ": " + map1Value + "\n");
                rezStr.append("+ " + key + ": " + map2Value + "\n");
            }
        }
        return rezStr.toString();
    }

/*public static String getMapsDifferences(Map<String, String> map1, Map<String, String> map2) throws ParseException {
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
    }*/

    public static void soutTreeMap(TreeMap<String, Object> treeMap) {
        System.out.println("-------------------------------------------");
        System.out.println(treeMap);
        System.out.println("-------------------------------------------");
    }

    public static String getMapsDifferences(TreeMap<String, Object> treeMap1,
                                            TreeMap<String, Object> treeMap2) throws ParseException {
        String map1Value;
        String map2Value;

        soutTreeMap(treeMap1);
        soutTreeMap(treeMap2);

        // Получаем отсортированный Map с ключами из двух json файлов
        TreeMap<String, Object> keysFromBothFile = new TreeMap<>();
        keysFromBothFile.putAll(treeMap1);
        keysFromBothFile.putAll(treeMap2);

        StringBuilder rezStr = new StringBuilder("{\n");

        for (Map.Entry<String, Object> entry : keysFromBothFile.entrySet()) {
            if (treeMap1.get(entry.getKey()) == null) {
                map1Value = null;
            } else {
                map1Value = treeMap1.get(entry.getKey()).toString();
            }

            if (treeMap2.get(entry.getKey()) == null) {
                map2Value = null;
            } else {
                map2Value = treeMap2.get(entry.getKey()).toString();
            }

            rezStr.append(getLineDifferences(entry.getKey(), map1Value, map2Value));
        }

        rezStr.append("}\n");
        return rezStr.toString();

        /*
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
        */
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        TreeMap<String, Object> treeMap1 = readTreeMapFromFile(filePath1);
        TreeMap<String, Object> treeMap2 = readTreeMapFromFile(filePath2);
        return getMapsDifferences(treeMap1, treeMap2);

        /*
        Map<String, String> jsonMap1 = readMapFromFile(filePath1);
        Map<String, String> jsonMap2 = readMapFromFile(filePath2);
        return getMapsDifferences(jsonMap1, jsonMap2);
         */
    }
}
