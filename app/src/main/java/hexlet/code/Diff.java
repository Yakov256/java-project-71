package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;

public class Diff {

    /*public static DiffersStates getLineDifferencesState(Object map1Value, Object map2Value) {
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
    }*/

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

        Set<String> keySet = new TreeSet<>(treeMap1.keySet());
        keySet.addAll(treeMap2.keySet());

        List<Map<String, Object>> diffList = new ArrayList<>();
        for (String entry : keySet) {
            map1Value = treeMap1.get(entry);
            map2Value = treeMap2.get(entry);

            DiffersStates differencesState = DiffersStates.notChanged;
            if (map1Value == null & map2Value != null) {
                differencesState = DiffersStates.added;
            } else if (map2Value == null) {
                differencesState = DiffersStates.removed;
            } else if (!map1Value.equals(map2Value)) {
                differencesState = DiffersStates.updated;
            }

            Map<String, Object> diffmap = new LinkedHashMap<>();
            diffmap.put("key", entry);
            diffmap.put("Difference", differencesState);
            diffmap.put("file1Value", map1Value);
            diffmap.put("file2Value", map2Value);
            diffList.add(diffmap);
        }

        return diffList;
    }

}
