package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.Parser.getTreeMap;

public class Differ {

    public static String readStringFromFile(String fileNameOrFullPath) throws IOException {
        Path absolutePath = Paths.get(fileNameOrFullPath).toAbsolutePath().normalize();
        return Files.readString(absolutePath);
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

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }
}
