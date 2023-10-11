import hexlet.code.Differ;
import hexlet.code.DiffersStates;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static hexlet.code.Differ.getLineDifferencesState;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTest {
/*
    @Test
    void getTreeMapsDifferencesListTest() {
        Map<String, Object> testTreeMap1 = new TreeMap<>();
        final int id45 = 45;
        final int setting200 = 200;

        testTreeMap1.put("chars1", "{'a', 'b', 'c'}");
        testTreeMap1.put("chars2", "{'d', 'e', 'f'}");
        testTreeMap1.put("checked", false);
        testTreeMap1.put("default", null);
        testTreeMap1.put("id", id45);
        testTreeMap1.put("key1", "value1");
        testTreeMap1.put("numbers1", "{1, 2, 3, 4}");
        testTreeMap1.put("numbers2", "{2, 3, 4, 5}");
        testTreeMap1.put("numbers3", "{3, 4, 5}");
        testTreeMap1.put("setting1", "Some value");
        testTreeMap1.put("setting2", setting200);
        testTreeMap1.put("setting3", true);

        TreeMap<String, Object> testTreeMap2 = new TreeMap<>();
        testTreeMap2.put("chars1", "{'a', 'b', 'c'}");
        testTreeMap2.put("chars2", false);
        testTreeMap2.put("checked", true);
        testTreeMap2.put("default", "{\"value1\", \"value2\"}");
        testTreeMap2.put("id", null);
        testTreeMap2.put("key2", "value2");
        testTreeMap2.put("numbers1", "{1, 2, 3, 4}");
        testTreeMap2.put("numbers2", "{22, 33, 44, 55}");
        testTreeMap2.put("numbers4", "{4, 5, 6}");
        testTreeMap2.put("obj1", "{nestedKey=value, isNested=true}");
        testTreeMap2.put("setting1", "Another value");
        testTreeMap2.put("setting2", "300");
        testTreeMap2.put("setting3", "none");

        String referenceStr = Differ.readStringFromFile(System.getProperty("user.dir")
                + "/src/test/resources/DifferTestReferenceStr.txt");

        String rezStr = getTreeMapsDifferencesList(testTreeMap1, testTreeMap2).toString();
        assertEquals(referenceStr, rezStr);
    }*/

    @Test
    void getLineDifferencesStateTest() {
        final int[] numbers1234 = {1, 2, 3, 4};
        final int[] numbers2345 = {2, 3, 4, 5};
        final int int123 = 123;
        final int int234 = 234;

        assertEquals(DiffersStates.notChanged, getLineDifferencesState(null, null));
        assertEquals(DiffersStates.notChanged, getLineDifferencesState(numbers1234, numbers1234));
        assertEquals(DiffersStates.added, getLineDifferencesState(null, "string"));
        assertEquals(DiffersStates.added, getLineDifferencesState(null, int123));
        assertEquals(DiffersStates.removed, getLineDifferencesState("string", null));
        assertEquals(DiffersStates.updated, getLineDifferencesState("string1", "string2"));
        assertEquals(DiffersStates.updated, getLineDifferencesState(int123, int234));
        assertEquals(DiffersStates.updated, getLineDifferencesState(numbers1234, numbers2345));
    }

    String getTestStr(String filename1, String filename2, String format) {
        String filepath1 = System.getProperty("user.dir") + "/src/test/resources/" + filename1;
        String filepath2 = System.getProperty("user.dir") + "/src/test/resources/" + filename2;

        String testStr = "";
        try {
            testStr = Differ.generate(filepath1.toString(), filepath2.toString(), format);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return testStr;
    }

    @Test
    void jsonToStylishFormatTest() {
        String rezStr = getTestStr("file1.json", "file2.json", "stylish");
        String referenceStr = Differ.readStringFromFile(System.getProperty("user.dir")
                + "/src/test/resources/StylishFormatReferenceStr.txt");
        assertEquals(referenceStr, rezStr);
    }

    @Test
    void ymlToStylishFormatTest() {
        String rezStr = getTestStr("file1.yml", "file2.yml", "stylish");
        String referenceStr = Differ.readStringFromFile(System.getProperty("user.dir")
                + "/src/test/resources/StylishFormatReferenceStr.txt");
        assertEquals(referenceStr, rezStr);
    }

    @Test
    void jsonToPlainFormatTest() {
        String rezStr = getTestStr("file1.json", "file2.json", "plain");
        String referenceStr = Differ.readStringFromFile(System.getProperty("user.dir")
                + "/src/test/resources/PlainFormatReferenceStr.txt");
        assertEquals(referenceStr, rezStr);
    }

    @Test
    void ymlToPlainFormatTest() {
        String rezStr = getTestStr("file1.yml", "file2.yml", "plain");
        String referenceStr = Differ.readStringFromFile(System.getProperty("user.dir")
                + "/src/test/resources/PlainFormatReferenceStr.txt");
        assertEquals(referenceStr, rezStr);
    }

    @Test
    void jsonToJsonFormatTest() {
        String rezStr = getTestStr("file1.json", "file2.json", "json");
        String referenceStr = Differ.readStringFromFile(System.getProperty("user.dir")
                + "/src/test/resources/JsonFormatReferenceStr.txt");
        assertEquals(referenceStr, rezStr);
    }

    @Test
    void ymlToJsonFormatTest() {
        String rezStr = getTestStr("file1.yml", "file2.yml", "json");
        String referenceStr = Differ.readStringFromFile(System.getProperty("user.dir")
                + "/src/test/resources/JsonFormatReferenceStr.txt");
        assertEquals(referenceStr, rezStr);
    }

}
