package hexlet.code;

import java.util.Map;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.List;

/*
Дмитрий, добрый день!
Большое спасибо, за интересные замечания к проекту! Вижу, как они улучшают логику работы программы.

>> https://github.com/Yakov256/java-project-71/blob/86ed99ab612eb69d10fb58150b4dc67349e064be/app/src/main/java
/hexlet/code/Differ.java#L52
>> - формирование дифа вынеси в отдельный класс, так же у тебя тут много методов для этого, мне кажется там можно
подсократить, попробуй написать все в одном методе, посмотрим что выйдет.

Объединил все в один метод, оставил отдельно только getLineDifferencesState(...), он вроде логично смотрится отдельно.
А разбивать на отдельные методы я начал, потому, что CodecLimate ругается на комплексную сложность методов и их длину.
Стоит в обще обращать внимание на оценку CodecLimate?
*/

public class Utils {

    /*
    public static String readStringFromFile(String fileNameOrFullPath) {
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
    }

     */

    public static DiffersStates getLineDifferencesState(Object map1Value, Object map2Value) {
        if (map1Value == null) {
            if (map2Value != null) {
                return DiffersStates.added;
            }
        } else if (map2Value == null) {
            return DiffersStates.removed;
        } else if (!map1Value.equals(map2Value)) {
            return DiffersStates.updated;
        }

        return DiffersStates.notChanged;
    }

    public static List<Map<String, Object>> getTreeMapsDifferencesList(Map<String, Object> treeMap1,
                                                                       Map<String, Object> treeMap2) {
        Object map1Value;
        Object map2Value;
/*
     >> https://github.com/Yakov256/java-project-71/blob/86ed99ab612eb69d10fb58150b4dc67349e064be/app/src/main
        /java/hexlet/code/Differ.java#L34 - замена null на строку логически больше тяготеет к форматтерам вынеси
        эту логику туда.

        Вынести это в Formatter я не могу т.к. в него попадает уже результат сравнения файлов. Эту обработку
        приходится делать до сравнения, иначе перепутаются "null"/null и результат будет не корректен.
 */
        //Обработка неправильно распознанного null (null вместо строки "null" в файле)
        for (Map.Entry<String, Object> entry : treeMap1.entrySet()) {
            if (entry.getValue() == null) {
                treeMap1.put(entry.getKey(), "null");
            }
        }
        for (Map.Entry<String, Object> entry : treeMap2.entrySet()) {
            if (entry.getValue() == null) {
                treeMap2.put(entry.getKey(), "null");
            }
        }

        Map<String, Object> keysFromBothFile = new TreeMap<>();
        keysFromBothFile.putAll(treeMap1);
        keysFromBothFile.putAll(treeMap2);

        List<Map<String, Object>> diffList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : keysFromBothFile.entrySet()) {
            map1Value = treeMap1.get(entry.getKey());
            map2Value = treeMap2.get(entry.getKey());

            Map<String, Object> diffmap = new LinkedHashMap<>();
            diffmap.put("key", entry.getKey());
            diffmap.put("Difference", getLineDifferencesState(map1Value, map2Value));
            diffmap.put("file1Value", map1Value);
            diffmap.put("file2Value", map2Value);
            diffList.add(diffmap);
        }

        return diffList;
    }

}
