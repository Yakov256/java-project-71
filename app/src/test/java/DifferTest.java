
import hexlet.code.DiffersStates;;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;

import static hexlet.code.Differ.getLineDifferencesState;
import static hexlet.code.Differ.getTreeMapsDifferencesList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    void getTreeMapsDifferencesListTest() {
        TreeMap<String, Object> testTreeMap1 = new TreeMap<>();
        final int id45 = 45;
        final int setting200 = 200;

        /*char[] abc =  {'a', 'b', 'c'};
        char[] def =  {'d', 'e', 'f'};
        int[] numbers1234 = {1, 2, 3, 4};
        int[] numbers2345 = {2, 3, 4, 5};
        /*int[] numbers345 = {3, 4, 5};*/

        //testTreeMap1.put("chars1", abc);
        testTreeMap1.put("chars1", "{'a', 'b', 'c'}");
        //testTreeMap1.put("chars2", def);
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

        String referenceStr = "[\"chars1\", \"notChanged\", \"{'a', 'b', 'c'}\", \"{'a', 'b', 'c'}\", "
                + "\"chars2\", \"updated\", \"{'d', 'e', 'f'}\", \"false\", \"checked\", \"updated\", \"false\", "
                + "\"true\", \"default\", \"added\", \"null\", \"{\"value1\", \"value2\"}\", \"id\", \"removed\", "
                + "\"45\", \"null\", \"key1\", \"removed\", \"value1\", \"null\", \"key2\", \"added\", \"null\", "
                + "\"value2\", \"numbers1\", \"notChanged\", \"{1, 2, 3, 4}\", \"{1, 2, 3, 4}\", \"numbers2\", "
                + "\"updated\", \"{2, 3, 4, 5}\", \"{22, 33, 44, 55}\", \"numbers3\", \"removed\", \"{3, 4, 5}\", "
                + "\"null\", \"numbers4\", \"added\", \"null\", \"{4, 5, 6}\", \"obj1\", \"added\", \"null\", "
                + "\"{nestedKey=value, isNested=true}\", \"setting1\", \"updated\", \"Some value\", \"Another value\""
                + ", \"setting2\", \"updated\", \"200\", \"300\", \"setting3\", \"updated\", \"true\", \"none\"]";

        String rezStr = getTreeMapsDifferencesList(testTreeMap1, testTreeMap2).toString();
        assertEquals(rezStr, referenceStr);
    }

    @Test
    void getLineDifferencesStateTest() {
        int[] numbers1234 = {1, 2, 3, 4};
        int[] numbers2345 = {2, 3, 4, 5};

        assertEquals(DiffersStates.notChanged, getLineDifferencesState(null, null));
        assertEquals(DiffersStates.notChanged, getLineDifferencesState(numbers1234, numbers1234));
        assertEquals(DiffersStates.added, getLineDifferencesState(null, "string"));
        assertEquals(DiffersStates.added, getLineDifferencesState(null, 123));
        assertEquals(DiffersStates.removed, getLineDifferencesState("string", null));
        assertEquals(DiffersStates.updated, getLineDifferencesState("string1", "string2"));
        assertEquals(DiffersStates.updated, getLineDifferencesState(123, 234));
        assertEquals(DiffersStates.updated, getLineDifferencesState(numbers1234, numbers2345));
    }

}
