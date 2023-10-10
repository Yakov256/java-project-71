import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static hexlet.code.Differ.readStringFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void readTreeMapFromFileTest() throws IOException {

        Map<String, Object> readTreeMapFromJSON =
                Parser.getTreeMap(Differ.readStringFromFile(System.getProperty("user.dir")
                                + "/src/test/resources/file1.json"), "/src/test/resources/file1.json");

        Map<String, Object> readTreeMapFromYAML =
                Parser.getTreeMap(Differ.readStringFromFile(System.getProperty("user.dir")
                        + "/src/test/resources/file1.yml"), "/src/test/resources/file1.yml");

        String referenceStr = readStringFromFile(System.getProperty("user.dir")
                + "/src/test/resources/ParserTestReferenceStr.txt");

        assertEquals(readTreeMapFromJSON.toString(), referenceStr);
        assertEquals(readTreeMapFromYAML.toString(), referenceStr);
    }

}
