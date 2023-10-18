package hexlet.code;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static hexlet.code.Parser.getTreeMap;
import static hexlet.code.Utils.readStringFromFile;

public class Differ {

    private static String getFormatName(String filePath) {
        String formatName = "";
        if (filePath.endsWith("json")) {
            formatName = "json";
        } else if (filePath.endsWith("yml") || filePath.endsWith("yaml")) {
            formatName = "yml";
        }

        return formatName;
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        Map<String, Object> treeMap1 = getTreeMap(readStringFromFile(filePath1), getFormatName(filePath1));
        Map<String, Object> treeMap2 = getTreeMap(readStringFromFile(filePath2), getFormatName(filePath2));

        List<Map<String, Object>> treeMapsDifferences = Utils.getTreeMapsDifferencesList(treeMap1, treeMap2);
        return Formatter.getFormattedString(treeMapsDifferences, formatName);
    }

    // Для тестов hexlet check, без этого метода не проходят
    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object> treeMap1 = getTreeMap(readStringFromFile(filePath1), getFormatName(filePath1));
        Map<String, Object> treeMap2 = getTreeMap(readStringFromFile(filePath2), getFormatName(filePath2));

        List<Map<String, Object>> listMapsDifferences = Utils.getTreeMapsDifferencesList(treeMap1, treeMap2);
        return Formatter.getFormattedString(listMapsDifferences, "stylish");
    }
}
