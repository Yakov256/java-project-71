package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/*
>>
https://github.com/Yakov256/java-project-71/blob/86ed99ab612eb69d10fb58150b4dc67349e064be/app/src/main/java
/hexlet/code/Differ.java#L52
- формирование дифа вынеси в отдельный класс, так же у тебя тут много методов для этого, мне кажется там можно
подсократить, попробуй написать все в одном методе, посмотрим что выйдет.

*/

public class Utils {

    private static void addKeysFromMapAndFixNull(Map<String, Object> mapForAdding, Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                mapForAdding.put(entry.getKey(), "null");
                map.put(entry.getKey(), "null");
            } else {
                mapForAdding.put(entry.getKey(), entry.getValue());
            }
        }
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

        Map<String, Object> keysFromBothFile = new TreeMap<>();
        addKeysFromMapAndFixNull(keysFromBothFile, treeMap1);
        addKeysFromMapAndFixNull(keysFromBothFile, treeMap2);

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
