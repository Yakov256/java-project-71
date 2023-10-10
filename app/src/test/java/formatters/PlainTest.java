package formatters;

import hexlet.code.Differs;
import hexlet.code.DiffersStates;
import hexlet.code.formatters.Plain;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static hexlet.code.Differ.readStringFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainTest {

    @Test
    void getFormattedDiffersTest() {
        List<Differs> treeMapsDifferences = new LinkedList<>();
        final int int45 = 45;
        final int int200 = 200;
        final int int300 = 300;

        treeMapsDifferences.add(new Differs("chars1", DiffersStates.notChanged, "[a, b, c]", "[a, b, c]"));
        treeMapsDifferences.add(new Differs("chars2", DiffersStates.updated, "[d, e, f]", false));
        treeMapsDifferences.add(new Differs("checked", DiffersStates.updated, false, true));
        treeMapsDifferences.add(new Differs("default", DiffersStates.updated, null, "[value1, value2]"));
        treeMapsDifferences.add(new Differs("id", DiffersStates.updated, int45, null));
        treeMapsDifferences.add(new Differs("key1", DiffersStates.removed, "value1", null));
        treeMapsDifferences.add(new Differs("key2", DiffersStates.added, null, "value2"));
        treeMapsDifferences.add(new Differs("numbers1", DiffersStates.notChanged, "[1, 2, 3, 4]", "[1, 2, 3, 4]"));
        treeMapsDifferences.add(new Differs("numbers2", DiffersStates.updated, "[2, 3, 4, 5]", "[22, 33, 44, 55]"));
        treeMapsDifferences.add(new Differs("numbers3", DiffersStates.removed, "[3, 4, 5]", null));
        treeMapsDifferences.add(new Differs("numbers4", DiffersStates.added, "null", "[4, 5, 6]"));
        //treeMapsDifferences.add(new Differs("obj1", DiffersStates.added, "null", "{nestedKey=value, isNested=true}"));
        treeMapsDifferences.add(new Differs("setting1", DiffersStates.updated, "Some value", "Another value"));
        treeMapsDifferences.add(new Differs("setting2", DiffersStates.updated, int200, int300));
        treeMapsDifferences.add(new Differs("setting3", DiffersStates.updated, true, "none"));

        String rezStr = Plain.getFormattedDiffers(treeMapsDifferences);

        String referenceStr = readStringFromFile(System.getProperty("user.dir")
                + "/src/test/resources/PlainTestReferenceStr.txt");

        assertEquals(referenceStr, rezStr);
    }

}
