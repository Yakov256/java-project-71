package hexlet.code;

import java.util.Map;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Utils {

    public static DiffersStates getLineDifferencesState(Object map1Value, Object map2Value) {
        if (map1Value == null) {
            if (map2Value != null) {
                return DiffersStates.added;
            }
        } else if (map2Value == null) {
            return DiffersStates.removed;
        } else if (!map1Value.equals(map2Value)) {
            return DiffersStates.updated;
        }

        return DiffersStates.notChanged;
    }

    public static List<Map<String, Object>> getTreeMapsDifferencesList(Map<String, Object> treeMap1,
                                                                       Map<String, Object> treeMap2) {
        Object map1Value;
        Object map2Value;

        //Обработка неправильно распознанного null (null вместо строки "null" в файле)
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
        keysFromBothFile.putAll(treeMap1);
        keysFromBothFile.putAll(treeMap2);

        List<Map<String, Object>> diffList = new ArrayList<>();
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
