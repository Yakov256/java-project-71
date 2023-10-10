import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void readTreeMapFromFileTest() throws IOException {

        Map<String, Object> readTreeMapFromJSON =
                Parser.getTreeMap(Differ.readStringFromFile(System.getProperty("user.dir")
                                + "/src/test/resources/file1.json"), "/src/test/resources/file1.json");

        Map<String, Object> readTreeMapFromYAML =
                Parser.getTreeMap(Differ.readStringFromFile(System.getProperty("user.dir")
                        + "/src/test/resources/file1.json"), "/src/test/resources/file2.json");

        String referenceStr = "{chars1=[a, b, c], chars2=[d, e, f], checked=false, default=null, id=45, key1=value1, "
                + "numbers1=[1, 2, 3, 4], numbers2=[2, 3, 4, 5], numbers3=[3, 4, 5], setting1=Some "
                + "value, setting2=200, setting3=true}";

        assertEquals(readTreeMapFromJSON.toString(), referenceStr);
        assertEquals(readTreeMapFromYAML.toString(), referenceStr);
    }

}
