package hexlet.code;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static hexlet.code.Parser.readTreeMapFromFile;

public class Differ {

    public static DiffersStates getLineDifferencesState(Object map1Value, Object map2Value) {

        if (map1Value == null) {
            if (map2Value != null) { // Добавлено
                return DiffersStates.added;
            }
        } else if (map2Value == null) { // Удалено
            return DiffersStates.removed;
        } else if (!map1Value.equals(map2Value)) { //Изменено
            return DiffersStates.updated;
        }

        return DiffersStates.notChanged;
    }

/*
    public static DiffersStates getLineDifferencesState_old(Object map1Value, Object map2Value) {
        StringBuilder rezStr = new StringBuilder();
        DiffersStates differsStates = DiffersStates.notChanged;
        if (map1Value != null) {
            if (map2Value == null) { //удалено во тором файле
                //return DiffersStates.removed;
                differsStates = DiffersStates.removed;
            } else if (!map1Value.equals(map2Value)) { // изменено во втором файле
                //return DiffersStates.notChanged;
                differsStates = DiffersStates.updated;
            //} else { //изменено во втором файле
                //return DiffersStates.updated;
            }
        } else if (map2Value != null) { //Есть только в первом
            //return DiffersStates.added;
            differsStates = DiffersStates.added;
        }

        return differsStates;
    }*/

    public static List<Differs> getTreeMapsDifferencesList(TreeMap<String, Object> treeMap1,
                                                           TreeMap<String, Object> treeMap2) {
        List<Differs> treeMapsDifferences = new LinkedList<>();
        Object map1Value;
        Object map2Value;

        TreeMap<String, Object> keysFromBothFile = new TreeMap<>();
        keysFromBothFile.putAll(treeMap1);
        keysFromBothFile.putAll(treeMap2);

        for (Map.Entry<String, Object> entry : keysFromBothFile.entrySet()) {

            map1Value = treeMap1.get(entry.getKey());
            map2Value = treeMap2.get(entry.getKey());

            Differs differs = new Differs(entry.getKey(), getLineDifferencesState(map1Value, map2Value),
                    map1Value, map2Value);
            treeMapsDifferences.add(differs);

        }

        return treeMapsDifferences;
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        TreeMap<String, Object> treeMap1 = readTreeMapFromFile(filePath1);
        TreeMap<String, Object> treeMap2 = readTreeMapFromFile(filePath2);
        ///--- переделываю на новый формат
        List<Differs> treeMapsDifferences = getTreeMapsDifferencesList(treeMap1, treeMap2);
        /*
        System.out.println("------------------------ TreeMapsDifferences ------------------------");
        System.out.println(treeMapsDifferences);
        System.out.println("------------------------ Plain ------------------------");
        System.out.println(Plain.getFormattedDiffers(treeMapsDifferences));
        System.out.println("------------------------ Stylish ------------------------");
        System.out.println(Stylish.getFormattedDiffers(treeMapsDifferences));
        System.out.println("------------------------ TreeMapsDifferences ------------------------");
        */

        return Formatter.getFormattedString(treeMapsDifferences, formatName);
    }
}
