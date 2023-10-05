package formatters;

import hexlet.code.Differs;
import hexlet.code.DiffersStates;
import hexlet.code.formatters.Json;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    @Test
    void getFormattedDiffersTest() {
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
        treeMapsDifferences.add(new Differs("obj1", DiffersStates.added, "null", "{nestedKey=value, isNested=true}"));
        treeMapsDifferences.add(new Differs("setting1", DiffersStates.updated, "Some value", "Another value"));
        treeMapsDifferences.add(new Differs("setting2", DiffersStates.updated, 200, 300));
        treeMapsDifferences.add(new Differs("setting3", DiffersStates.updated, true, "none"));

        String rezStr = Json.getFormattedDiffers(treeMapsDifferences);

        String referenceStr = ""//"{\n"
                + "{\"key\":\"chars1\",\"status\":\"notChanged\",\"oldValue\":\"[a, b, c]\",\"newValue\":\""
                + "[a, b, c]\"}\n"
                + "{\"key\":\"chars2\",\"status\":\"updated\",\"oldValue\":\"[d, e, f]\",\"newValue\":false}\n"
                + "{\"key\":\"checked\",\"status\":\"updated\",\"oldValue\":false,\"newValue\":true}\n"
                + "{\"key\":\"default\",\"status\":\"updated\",\"oldValue\":null,\"newValue\":\"[value1, value2]\"}\n"
                + "{\"key\":\"id\",\"status\":\"updated\",\"oldValue\":45,\"newValue\":null}\n"
                + "{\"key\":\"key1\",\"status\":\"removed\",\"oldValue\":\"value1\",\"newValue\":null}\n"
                + "{\"key\":\"key2\",\"status\":\"added\",\"oldValue\":null,\"newValue\":\"value2\"}\n"
                + "{\"key\":\"numbers1\",\"status\":\"notChanged\",\"oldValue\":\"[1, 2, 3, 4]\",\"newValue\":\""
                + "[1, 2, 3, 4]\"}\n"
                + "{\"key\":\"numbers2\",\"status\":\"updated\",\"oldValue\":\"[2, 3, 4, 5]\",\"newValue\":\""
                + "[22, 33, 44, 55]\"}\n"
                + "{\"key\":\"numbers3\",\"status\":\"removed\",\"oldValue\":\"[3, 4, 5]\",\"newValue\":null}\n"
                + "{\"key\":\"numbers4\",\"status\":\"added\",\"oldValue\":\"null\",\"newValue\":\"[4, 5, 6]\"}\n"
                + "{\"key\":\"obj1\",\"status\":\"added\",\"oldValue\":\"null\",\"newValue\":\"{nestedKey=value"
                + ", isNested=true}\"}\n"
                + "{\"key\":\"setting1\",\"status\":\"updated\",\"oldValue\":\"Some value\",\"newValue\":\""
                + "Another value\"}\n"
                + "{\"key\":\"setting2\",\"status\":\"updated\",\"oldValue\":200,\"newValue\":300}\n"
                + "{\"key\":\"setting3\",\"status\":\"updated\",\"oldValue\":true,\"newValue\":\"none\"}\n";
                //+ "}";

        assertEquals(rezStr, referenceStr);
    }
}
