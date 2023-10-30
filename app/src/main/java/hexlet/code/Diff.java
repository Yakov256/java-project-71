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
        Set<String> keySet = new TreeSet<>(treeMap1.keySet());
        keySet.addAll(treeMap2.keySet());

        List<Map<String, Object>> diffList = new ArrayList<>();
        for (String entry : keySet) {
            DiffersStates differencesState = DiffersStates.notChanged;
            if (treeMap1.containsKey(entry) && !treeMap2.containsKey(entry)) {
                differencesState = DiffersStates.removed;
            } else if (treeMap2.containsKey(entry) && !treeMap1.containsKey(entry)) {
                differencesState = DiffersStates.added;
            } else if (treeMap1.get(entry) != treeMap2.get(entry)) {
                differencesState = DiffersStates.updated;
                if (treeMap1.get(entry) != null && treeMap2.get(entry) != null) {
                    if (treeMap1.get(entry).equals(treeMap2.get(entry))) {
                        differencesState = DiffersStates.notChanged;
                    }
                }
            }

            Map<String, Object> diffmap = new LinkedHashMap<>();
            diffmap.put("key", entry);
            diffmap.put("Difference", differencesState);
            diffmap.put("file1Value", treeMap1.get(entry));
            diffmap.put("file2Value", treeMap2.get(entry));
            diffList.add(diffmap);
        }

        return diffList;
    }

}
