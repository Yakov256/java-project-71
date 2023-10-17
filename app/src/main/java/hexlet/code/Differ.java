package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.Parser.getTreeMap;

public class Differ {

    public static String readStringFromFile(String filePath) {

        String strFromFile = "";
        try {
            strFromFile = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Could not read file: " + filePath);
            System.out.println(e.getMessage());
        }

        return strFromFile;
    }

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
