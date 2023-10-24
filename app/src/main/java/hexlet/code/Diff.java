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


        /*//Обработка неправильно распознанного null (null вместо строки "null" в файле)
        for (Map.Entry<String, Object> entry : treeMap1.entrySet()) {
            if (entry.getValue() == null) {
                treeMap1.put(entry.getKey(), "null");
            }
        }
        for (Map.Entry<String, Object> entry : treeMap2.entrySet()) {
            if (entry.getValue() == null) {
                treeMap2.put(entry.getKey(), "null");
            }
        }*/

        //System.out.println("11111111111111111111111111");
        Set<String> keySet = new TreeSet<>(treeMap1.keySet());
        keySet.addAll(treeMap2.keySet());
        /*
/////////////
        for (Map.Entry<String, Object> entry : treeMap1.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        System.out.println("----------------------------");

        for (String entry : keySet) {
            System.out.println(entry + "-" + treeMap1.get(entry) + "-" + treeMap1.containsKey(entry));
        }
        System.out.println("222222222222222222222222");
///////////////////
*/

        List<Map<String, Object>> diffList = new ArrayList<>();
        for (String entry : keySet) {
            map1Value = treeMap1.get(entry);
            map2Value = treeMap2.get(entry);

            //System.out.println(treeMap1.containsKey(entry) + " - " + treeMap2.containsKey(entry));

            DiffersStates differencesState = DiffersStates.notChanged;


            if (!treeMap2.containsKey(entry) & !treeMap1.containsKey(entry)) {
                differencesState = DiffersStates.notChanged;
            } else if (!treeMap2.containsKey(entry) & treeMap1.containsKey(entry)) {
                differencesState = DiffersStates.removed;
            } else if (treeMap2.containsKey(entry) & !treeMap1.containsKey(entry)) {
                differencesState = DiffersStates.added;
            } else if (map1Value != map2Value) {
                differencesState = DiffersStates.updated;
                if (map1Value != null & map2Value != null) {
                    if (!map1Value.equals(map2Value)) {
                        differencesState = DiffersStates.updated;
                    } else {
                        differencesState = DiffersStates.notChanged;
                    }
                }
            }

//            System.out.println(entry + ": (" + treeMap1.containsKey(entry) + " - " + treeMap2.containsKey(entry)
//                    + ") | (" + map1Value + " - " + map2Value + ") - " + differencesState
//                    + " 1!=2 " + (map1Value != map2Value));

            /*if (!treeMap1.containsKey(entry)) {
                if (!treeMap2.containsKey(entry)) {
                    differencesState = DiffersStates.notChanged;
                } else {
                    differencesState = DiffersStates.added;
                }
            } else if (!treeMap2.containsKey(entry)) {
                differencesState = DiffersStates.removed;
            } else if (!treeMap1.containsKey(entry) & !treeMap2.containsKey(entry)) {
                differencesState = DiffersStates.notChanged;
            } else if (map1Value == map2Value) {
                differencesState = DiffersStates.updated;
            }*/

            /*if (!treeMap1.containsKey(entry) & treeMap2.containsKey(entry)) {
                differencesState = DiffersStates.added;
            } else if (!treeMap2.containsKey(entry)) {
                differencesState = DiffersStates.removed;
            } else if (!treeMap1.containsKey(entry) & !treeMap2.containsKey(entry)) {
                differencesState = DiffersStates.notChanged;
            } else if (map1Value == map2Value) {
                differencesState = DiffersStates.updated;
            }*/

            //DiffersStates differencesState = DiffersStates.notChanged;
            /*if (map1Value == null & map2Value != null) {
                differencesState = DiffersStates.added;
            } else if (map2Value == null) {
                differencesState = DiffersStates.removed;
            } else if (!map1Value.equals(map2Value)) {
                differencesState = DiffersStates.updated;
            }*/

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
