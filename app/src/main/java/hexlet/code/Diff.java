package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;

public class Diff {

    public static List<Map<String, Object>> getTreeMapsDifferencesList(Map<String, Object> treeMap1,
                                                                       Map<String, Object> treeMap2) {
        Object map1Value;
        Object map2Value;

        Set<String> keySet = new TreeSet<>(treeMap1.keySet());
        keySet.addAll(treeMap2.keySet());

        List<Map<String, Object>> diffList = new ArrayList<>();
        for (String entry : keySet) {
            map1Value = treeMap1.get(entry);
            map2Value = treeMap2.get(entry);

            DiffersStates differencesState = DiffersStates.notChanged;
            if (treeMap1.containsKey(entry) & !treeMap2.containsKey(entry)) {
                differencesState = DiffersStates.removed;
            } else if (treeMap2.containsKey(entry) & !treeMap1.containsKey(entry)) {
                differencesState = DiffersStates.added;
            } else if (map1Value != map2Value) {
                differencesState = DiffersStates.updated;
                if (map1Value != null & map2Value != null) {
                    if (map1Value.equals(map2Value)) {
                        differencesState = DiffersStates.notChanged;
                    }
                }
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
