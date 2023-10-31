package hexlet.code;

import java.util.Objects;
import java.util.TreeSet;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Diff {

    public static List<Map<String, Object>> getTreeMapsDifferencesList(Map<String, Object> treeMap1,
                                                                       Map<String, Object> treeMap2) {
        Set<String> keySet = new TreeSet<>(treeMap1.keySet());
        keySet.addAll(treeMap2.keySet());

        List<Map<String, Object>> diffList = new ArrayList<>();
        for (String entry : keySet) {
            DiffersStates differencesState = DiffersStates.notChanged;
            if (!treeMap2.containsKey(entry)) {
                differencesState = DiffersStates.removed;
            } else if (!treeMap1.containsKey(entry)) {
                differencesState = DiffersStates.added;
            } else if (!Objects.equals(treeMap1.get(entry), treeMap2.get(entry))) {
                differencesState = DiffersStates.updated;
            }

            Map<String, Object> diffmap = new LinkedHashMap<>();
            diffmap.put("key", entry);
            diffmap.put("Difference", differencesState);
            diffmap.put("value", treeMap1.get(entry));
            if (differencesState == DiffersStates.added || differencesState == DiffersStates.updated) {
                diffmap.put("newValue", treeMap2.get(entry));
            }
            diffList.add(diffmap);
        }

        //System.out.println(diffList);
        return diffList;
    }

}
