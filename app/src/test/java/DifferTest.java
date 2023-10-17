import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DifferTest {

    private static String stylishFormatReferenceStr;
    private static String plainFormatReferenceStr;
    private static String jsonFormatReferenceStr;

    @BeforeAll
    static void loadAllReferenceStrings() {
        stylishFormatReferenceStr = Differ.readStringFromFile(System.getProperty("user.dir")
                + "/src/test/resources/StylishFormatReferenceStr.txt");

        plainFormatReferenceStr = Differ.readStringFromFile(System.getProperty("user.dir")
                + "/src/test/resources/PlainFormatReferenceStr.txt");

        jsonFormatReferenceStr = Differ.readStringFromFile(System.getProperty("user.dir")
                + "/src/test/resources/JsonFormatReferenceStr.txt");
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
        assertEquals(stylishFormatReferenceStr, rezStr);
    }

    @Test
    void ymlToStylishFormatTest() {
        String rezStr = getTestStr("file1.yml", "file2.yml", "stylish");
        assertEquals(stylishFormatReferenceStr, rezStr);
    }

    @Test
    void jsonToPlainFormatTest() {
        String rezStr = getTestStr("file1.json", "file2.json", "plain");
        assertEquals(plainFormatReferenceStr, rezStr);
    }

    @Test
    void ymlToPlainFormatTest() {
        String rezStr = getTestStr("file1.yml", "file2.yml", "plain");
        assertEquals(plainFormatReferenceStr, rezStr);
    }

    @Test
    void jsonToJsonFormatTest() {
        String rezStr = getTestStr("file1.json", "file2.json", "json");
        assertEquals(jsonFormatReferenceStr, rezStr);
    }

    @Test
    void ymlToJsonFormatTest() {
        String rezStr = getTestStr("file1.yml", "file2.yml", "json");
        assertEquals(jsonFormatReferenceStr, rezStr);
    }

}
