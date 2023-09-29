package hexlet.code;

//import org.json.simple.parser.ParseException;
import java.util.Map;
import java.util.TreeMap;
import static hexlet.code.Parser.readTreeMapFromFile;

public class Differ {

    public static String getLineDifferences(String key, String map1Value, String map2Value) {
        StringBuilder rezStr = new StringBuilder();
        if (map1Value != null) {
            if (map2Value == null) { //удалено во тором файле
                rezStr.append("- " + key + ": " + map1Value + "\n");
                //rezStr.append("+ " + key + ": " + map2Value + "\n");
            } else if (map1Value.equals(map2Value)) { // значения равны
                rezStr.append("  " + key + ": " + map1Value + "\n");
            } else { //else if (!map1Value.equals(map2Value))  //изменено во тором файле
                rezStr.append("- " + key + ": " + map1Value + "\n");
                rezStr.append("+ " + key + ": " + map2Value + "\n");
            }
        } else {
            if (map2Value != null) { //Есть только в первом
                //rezStr.append("- " + key + ": " + map1Value + "\n");
                rezStr.append("+ " + key + ": " + map2Value + "\n");
            }
        }
        return rezStr.toString();
    }

    public static void soutTreeMap(TreeMap<String, Object> treeMap) {
        System.out.println("-------------------------------------------");
        System.out.println(treeMap);
        System.out.println("-------------------------------------------");
    }

    private static String toStringExceptNull(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return obj.toString();
        }
    }

    public static String getTreeMapsDifferences(TreeMap<String, Object> treeMap1, TreeMap<String, Object> treeMap2) {
        String map1Value;
        String map2Value;

        //soutTreeMap(treeMap1);
        //soutTreeMap(treeMap2);

        TreeMap<String, Object> keysFromBothFile = new TreeMap<>();
        keysFromBothFile.putAll(treeMap1);
        keysFromBothFile.putAll(treeMap2);

        StringBuilder rezStr = new StringBuilder("{\n");

        for (Map.Entry<String, Object> entry : keysFromBothFile.entrySet()) {

            map1Value = toStringExceptNull(treeMap1.get(entry.getKey()));
            map2Value = toStringExceptNull(treeMap2.get(entry.getKey()));
            /*
            if (treeMap1.get(entry.getKey()) == null) {
                map1Value = null;
            } else {
                map1Value = treeMap1.get(entry.getKey()).toString();
            }

            if (treeMap2.get(entry.getKey()) == null) {
                map2Value = null;
            } else {
                map2Value = treeMap2.get(entry.getKey()).toString();
            }
             */

            rezStr.append(getLineDifferences(entry.getKey(), map1Value, map2Value));
        }

        rezStr.append("}\n");
        return rezStr.toString();

    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        TreeMap<String, Object> treeMap1 = readTreeMapFromFile(filePath1);
        TreeMap<String, Object> treeMap2 = readTreeMapFromFile(filePath2);
        return getTreeMapsDifferences(treeMap1, treeMap2);
    }
}
