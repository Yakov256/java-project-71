package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.Parser.getTreeMap;

public class Differ {

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

    private static String readStringFromFile(String filePath) {

        String strFromFile = "";
        try {
            strFromFile = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Could not read file: " + filePath);
            System.out.println(e.getMessage());
        }

        return strFromFile;
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        TreeMap<String, Object> treeMap1 = getTreeMap(readStringFromFile(filePath1), filePath1);
        TreeMap<String, Object> treeMap2 = getTreeMap(readStringFromFile(filePath2), filePath2);

        List<Differs> treeMapsDifferences = getTreeMapsDifferencesList(treeMap1, treeMap2);
        return Formatter.getFormattedString(treeMapsDifferences, formatName);
    }

    // Для тестов hexlet check, без этого метода не проходят
    public static String generate(String filePath1, String filePath2) throws IOException {
        TreeMap<String, Object> treeMap1 = getTreeMap(readStringFromFile(filePath1), filePath1);
        TreeMap<String, Object> treeMap2 = getTreeMap(readStringFromFile(filePath2), filePath2);

        List<Differs> treeMapsDifferences = getTreeMapsDifferencesList(treeMap1, treeMap2);
        return Formatter.getFormattedString(treeMapsDifferences, "stylish");
    }
}
