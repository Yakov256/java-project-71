package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.Parser.getTreeMap;

public class Differ {

    private static Path getAbsolutPath(String fileNameOrFullPath) {

        System.out.println("getAbsolutPath");
        System.out.println("1." + fileNameOrFullPath);

        String filePath;
        if (fileNameOrFullPath.contains("/")) {
            filePath = fileNameOrFullPath;
        } else {
            filePath = System.getProperty("user.dir") + "/src/main/resources/" + fileNameOrFullPath;
            if (!Files.exists(Paths.get(filePath))) {
                filePath = System.getProperty("user.dir") + "/src/test/resources/" + fileNameOrFullPath;
            }
        }

        System.out.println("2." + filePath);
        Path absolutePath = Paths.get(filePath).toAbsolutePath();
        System.out.println("3." + absolutePath);

        return absolutePath;
    }

    public static String readStringFromFile(String fileNameOrFullPath) throws IOException {
        //return Files.readString(getAbsolutPath(fileNameOrFullPath));

        ///System.out.println(fileNameOrFullPath);
        //Path absolutePath = Paths.get(fileNameOrFullPath).toAbsolutePath().normalize();
        //System.out.println(absolutePath);

        Path absolutePath = Paths.get(fileNameOrFullPath).toAbsolutePath().normalize();
        return Files.readString(absolutePath);

        /*
        String filePath;
        if (fileNameOrFullPath.contains("/")) {
            filePath = fileNameOrFullPath;
        } else {
            filePath = System.getProperty("user.dir") + "/src/main/resources/" + fileNameOrFullPath;
            if (!Files.exists(Paths.get(filePath))) {
                filePath = System.getProperty("user.dir") + "/src/test/resources/" + fileNameOrFullPath;
            }
        }

        String strFromFile = "";
        try {
            strFromFile = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Could not read file: " + filePath);
            System.out.println(e.getMessage());
        }

        return strFromFile;
        */
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
