//import hexlet.code.Differ;
//import hexlet.code.Parser;
//import hexlet.code.Parser;
//import hexlet.code.Differs;
//import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.io.IOException;
//import java.util.List;
import java.util.TreeMap;

import static hexlet.code.Differ.getTreeMapsDifferencesList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    void getTreeMapsDifferencesPlainTest() throws ParseException {
        TreeMap<String, Object> testTreeMap1 = new TreeMap<>();

        /*char[] abc =  {'a', 'b', 'c'};
        char[] def =  {'d', 'e', 'f'};
        int[] numbers1234 = {1, 2, 3, 4};
        int[] numbers2345 = {2, 3, 4, 5};
        int[] numbers345 = {3, 4, 5};*/

        //testTreeMap1.put("chars1", abc);
        testTreeMap1.put("chars1", "{'a', 'b', 'c'}");
        //testTreeMap1.put("chars2", def);
        testTreeMap1.put("chars2", "{'d', 'e', 'f'}");
        testTreeMap1.put("checked", false);
        testTreeMap1.put("default", null);
        testTreeMap1.put("id", 45);
        testTreeMap1.put("key1", "value1");
        testTreeMap1.put("numbers1", "{1, 2, 3, 4}");
        testTreeMap1.put("numbers2", "{2, 3, 4, 5}");
        testTreeMap1.put("numbers3", "{3, 4, 5}");
        testTreeMap1.put("setting1", "Some value");
        testTreeMap1.put("setting2", 200);
        testTreeMap1.put("setting3", true);

        TreeMap<String, Object> testTreeMap2 = new TreeMap<>();
        //{  obj1={nestedKey=value, isNested=true},
        /*String[] value12 =  {"value1", "value2"};
        int[] numbers22334455 = {22, 33, 44, 55};*/
        int[] numbers456 = {4, 5, 6};

        testTreeMap2.put("chars1", "{'a', 'b', 'c'}");
        testTreeMap2.put("chars2", false);
        testTreeMap2.put("checked", true);
        testTreeMap2.put("default", "{\"value1\", \"value2\"}");
        testTreeMap2.put("id", null);
        testTreeMap2.put("key2", "value2");
        testTreeMap2.put("numbers1", "{1, 2, 3, 4}");
        testTreeMap2.put("numbers2", "{22, 33, 44, 55}");
        testTreeMap2.put("numbers4", "{4, 5, 6}");
        //testTreeMap2.put("numbers4", numbers456);
        testTreeMap2.put("obj1", "{nestedKey=value, isNested=true}");
        testTreeMap2.put("setting1", "Another value");
        testTreeMap2.put("setting2", "300");
        testTreeMap2.put("setting3", "none");

        String referenceStr = "{\n"
                + "  chars1: {'a', 'b', 'c'}\n"
                + "- chars2: {'d', 'e', 'f'}\n"
                + "+ chars2: false\n"
                + "- checked: false\n"
                + "+ checked: true\n"
                + "+ default: {\"value1\", \"value2\"}\n"
                + "- id: 45\n"
                + "- key1: value1\n"
                + "+ key2: value2\n"
                + "  numbers1: {1, 2, 3, 4}\n"
                + "- numbers2: {2, 3, 4, 5}\n"
                + "+ numbers2: {22, 33, 44, 55}\n"
                + "- numbers3: {3, 4, 5}\n"
                + "+ numbers4: {4, 5, 6}\n"
                + "+ obj1: {nestedKey=value, isNested=true}\n"
                + "- setting1: Some value\n"
                + "+ setting1: Another value\n"
                + "- setting2: 200\n"
                + "+ setting2: 300\n"
                + "- setting3: true\n"
                + "+ setting3: none\n"
                + "}\n";

        String rezStr = Stylish.getFormattedDiffers(getTreeMapsDifferencesList(testTreeMap1, testTreeMap2));
        assertEquals(rezStr, referenceStr);
    }

}
