package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;

/*
>>
https://github.com/Yakov256/java-project-71/blob/86ed99ab612eb69d10fb58150b4dc67349e064be/app/src/main/java
/hexlet/code/Differ.java#L52
- формирование дифа вынеси в отдельный класс, так же у тебя тут много методов для этого, мне кажется там можно
подсократить, попробуй написать все в одном методе, посмотрим что выйдет.

*/

public class Utils {

    public static String readStringFromFile(String fileNameOrFullPath) {
        String filePath;
        if (fileNameOrFullPath.contains("/")) {
            filePath = fileNameOrFullPath;
        } else {
            filePath = System.getProperty("user.dir") + "/src/main/resources/" + fileNameOrFullPath;

            if (!Files.exists(Paths.get(filePath))) {
                filePath = System.getProperty("user.dir") + "/src/test/resources/" + fileNameOrFullPath;
            }
        }

        String strFromFile = "";
        try {
            strFromFile = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Could not read file: " + filePath);
            System.out.println(e.getMessage());
        }

        return strFromFile;
    }

    public static DiffersStates getLineDifferencesState(Object map1Value, Object map2Value) {

        if (map1Value == null) {
            if (map2Value != null) { // Добавлено
                return DiffersStates.added;
            }
        } else if (map2Value == null) { // Удалено
            return DiffersStates.removed;
        } else if (!map1Value.equals(map2Value)) { // Изменено
            return DiffersStates.updated;
        }

        return DiffersStates.notChanged; // Нет изменений
    }

    public static List<Map<String, Object>> getTreeMapsDifferencesList(Map<String, Object> treeMap1,
                                                                       Map<String, Object> treeMap2) {
        Object map1Value;
        Object map2Value;

////////
        //addKeysFromMapAndFixNull(keysFromBothFile, treeMap1);
        //addKeysFromMapAndFixNull(keysFromBothFile, treeMap2);
///////
        // Обработка неправильно распозного "null"
        for (Map.Entry<String, Object> entry : treeMap1.entrySet()) {
            if (entry.getValue() == null) {
                treeMap1.put(entry.getKey(), "null");
            }
        }
        for (Map.Entry<String, Object> entry : treeMap2.entrySet()) {
            if (entry.getValue() == null) {
                treeMap2.put(entry.getKey(), "null");
            }
        }

        Map<String, Object> keysFromBothFile = new TreeMap<>();
        for (Map.Entry<String, Object> entry : treeMap1.entrySet()) {
            keysFromBothFile.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Object> entry : treeMap2.entrySet()) {
            keysFromBothFile.put(entry.getKey(), entry.getValue());
        }
//////
        List<Map<String, Object>> diffList = new LinkedList<>();

        for (Map.Entry<String, Object> entry : keysFromBothFile.entrySet()) {
            map1Value = treeMap1.get(entry.getKey());
            map2Value = treeMap2.get(entry.getKey());

            Map<String, Object> diffmap = new LinkedHashMap<>();
            diffmap.put("key", entry.getKey());
            diffmap.put("Difference", getLineDifferencesState(map1Value, map2Value));
            diffmap.put("file1Value", map1Value);
            diffmap.put("file2Value", map2Value);
            diffList.add(diffmap);
        }

        return diffList;
    }

}
