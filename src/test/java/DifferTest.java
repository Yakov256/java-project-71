import hexlet.code.Differ;
//import hexlet.code.Parser;
import hexlet.code.Parser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
import java.io.IOException;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    void getMapsDifferencesTest() throws ParseException {
        TreeMap<String, Object> testMap1 = new TreeMap<>();
        testMap1.put("host", "hexlet.io");
        testMap1.put("timeout", "50");
        testMap1.put("proxy", "123.234.53.22");
        testMap1.put("follow", "false");

        TreeMap<String, Object> testMap2 = new TreeMap<>();
        testMap2.put("timeout", "20");
        testMap2.put("verbose", "true");
        testMap2.put("host", "hexlet.io");

        String referenceStr = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}\n";

        String rezStr = Differ.getTreeMapsDifferences(testMap1, testMap2);
        assertEquals(rezStr, referenceStr);
    }

    @Test
    void readTreeMapFromFileTest() throws IOException {
        TreeMap<String, Object> readTreeMapFromJSON =
                Parser.readTreeMapFromFile(System.getProperty("user.dir") + "/src/test/resources/file1.json");
        TreeMap<String, Object> readTreeMapFromYAML =
                Parser.readTreeMapFromFile(System.getProperty("user.dir") + "/src/test/resources/file1.yml");
        String defaultStr = "{chars1=[a, b, c], chars2=[d, e, f], checked=false, default=null, id=45, key1=value1, "
                + "numbers1=[1, 2, 3, 4], numbers2=[2, 3, 4, 5], numbers3=[3, 4, 5], setting1=Some "
                + "value, setting2=200, setting3=true}";
        assertEquals(readTreeMapFromJSON.toString(), defaultStr);
        assertEquals(readTreeMapFromYAML.toString(), defaultStr);
    }

}
