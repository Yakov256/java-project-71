import hexlet.code.Differs;
import hexlet.code.DiffersStates;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormattersTest {

    @Test
    void plainGetStringOrComplexValueTest() {
        List<Differs> treeMapsDifferences = new LinkedList<>();

        treeMapsDifferences.add(new Differs("chars1", DiffersStates.notChanged, "[a, b, c]", "[a, b, c]"));
        treeMapsDifferences.add(new Differs("chars2", DiffersStates.updated, "[d, e, f]", false));
        treeMapsDifferences.add(new Differs("checked", DiffersStates.updated, false, true));
        treeMapsDifferences.add(new Differs("default", DiffersStates.updated, null, "[value1, value2]"));
        treeMapsDifferences.add(new Differs("id", DiffersStates.updated, 45, null));
        treeMapsDifferences.add(new Differs("key1", DiffersStates.removed, "value1", null));
        treeMapsDifferences.add(new Differs("key2", DiffersStates.added, null, "value2"));
        treeMapsDifferences.add(new Differs("numbers1", DiffersStates.notChanged, "[1, 2, 3, 4]", "[1, 2, 3, 4]"));
        treeMapsDifferences.add(new Differs("numbers2", DiffersStates.updated, "[2, 3, 4, 5]", "[22, 33, 44, 55]"));
        treeMapsDifferences.add(new Differs("numbers3", DiffersStates.removed, "[3, 4, 5]", null));
        treeMapsDifferences.add(new Differs("numbers4", DiffersStates.added, "null", "[4, 5, 6]"));
        //treeMapsDifferences.add(new Differs("obj1", DiffersStates.added, "null", "{nestedKey=value, isNested=true}"));
        treeMapsDifferences.add(new Differs("setting1", DiffersStates.updated, "Some value", "Another value"));
        treeMapsDifferences.add(new Differs("setting2", DiffersStates.updated, 200, 300));
        treeMapsDifferences.add(new Differs("setting3", DiffersStates.updated, true, "none"));

        String rezStr = Plain.getFormattedDiffers(treeMapsDifferences);

        String referenceStr = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: ''value2''\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: '[complex value]'\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'\n";

        assertEquals(rezStr, referenceStr);
    }

    @Test
    void stylishGetStringOrComplexValueTest() {
        List<Differs> treeMapsDifferences = new LinkedList<>();

        treeMapsDifferences.add(new Differs("chars1", DiffersStates.notChanged, "[a, b, c]", "[a, b, c]"));
        treeMapsDifferences.add(new Differs("chars2", DiffersStates.updated, "[d, e, f]", false));
        treeMapsDifferences.add(new Differs("checked", DiffersStates.updated, false, true));
        treeMapsDifferences.add(new Differs("default", DiffersStates.updated, null, "[value1, value2]"));
        treeMapsDifferences.add(new Differs("id", DiffersStates.updated, 45, null));
        treeMapsDifferences.add(new Differs("key1", DiffersStates.removed, "value1", null));
        treeMapsDifferences.add(new Differs("key2", DiffersStates.added, null, "value2"));
        treeMapsDifferences.add(new Differs("numbers1", DiffersStates.notChanged, "[1, 2, 3, 4]", "[1, 2, 3, 4]"));
        treeMapsDifferences.add(new Differs("numbers2", DiffersStates.updated, "[2, 3, 4, 5]", "[22, 33, 44, 55]"));
        treeMapsDifferences.add(new Differs("numbers3", DiffersStates.removed, "[3, 4, 5]", null));
        treeMapsDifferences.add(new Differs("numbers4", DiffersStates.added, "null", "[4, 5, 6]"));
        //treeMapsDifferences.add(new Differs("obj1", DiffersStates.added, "null", "{nestedKey=value, isNested=true}"));
        treeMapsDifferences.add(new Differs("setting1", DiffersStates.updated, "Some value", "Another value"));
        treeMapsDifferences.add(new Differs("setting2", DiffersStates.updated, 200, 300));
        treeMapsDifferences.add(new Differs("setting3", DiffersStates.updated, true, "none"));

        String rezStr = Stylish.getFormattedDiffers(treeMapsDifferences);

        String referenceStr = "{\n"
                + "  chars1: [a, b, c]\n"
                + "- chars2: [d, e, f]\n"
                + "+ chars2: false\n"
                + "- checked: false\n"
                + "+ checked: true\n"
                + "- default: null\n"
                + "+ default: [value1, value2]\n"
                + "- id: 45\n"
                + "+ id: null\n"
                + "- key1: value1\n"
                + "+ key2: value2\n"
                + "  numbers1: [1, 2, 3, 4]\n"
                + "- numbers2: [2, 3, 4, 5]\n"
                + "+ numbers2: [22, 33, 44, 55]\n"
                + "- numbers3: [3, 4, 5]\n"
                + "+ numbers4: [4, 5, 6]\n"
                + "- setting1: Some value\n"
                + "+ setting1: Another value\n"
                + "- setting2: 200\n"
                + "+ setting2: 300\n"
                + "- setting3: true\n"
                + "+ setting3: none\n"
                + "}\n";

        assertEquals(rezStr, referenceStr);
    }

}
