import hexlet.code.Parser;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void readTreeMapFromFileTest() throws IOException {
        TreeMap<String, Object> readTreeMapFromJSON =
                Parser.readTreeMapFromFile(System.getProperty("user.dir") + "/src/test/resources/file1.json");
        TreeMap<String, Object> readTreeMapFromYAML =
                Parser.readTreeMapFromFile(System.getProperty("user.dir") + "/src/test/resources/file1.yml");
        String referenceStr = "{chars1=[a, b, c], chars2=[d, e, f], checked=false, default=null, id=45, key1=value1, "
                + "numbers1=[1, 2, 3, 4], numbers2=[2, 3, 4, 5], numbers3=[3, 4, 5], setting1=Some "
                + "value, setting2=200, setting3=true}";
        assertEquals(readTreeMapFromJSON.toString(), referenceStr);
        assertEquals(readTreeMapFromYAML.toString(), referenceStr);
    }

}
