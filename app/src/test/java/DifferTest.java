import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    static void loadAllReferenceStrings() throws IOException {
        stylishFormatReferenceStr = Differ.readStringFromFile(getTestFilePath("StylishFormatReferenceStr.txt"));
        plainFormatReferenceStr = Differ.readStringFromFile(getTestFilePath("PlainFormatReferenceStr.txt"));
        jsonFormatReferenceStr = Differ.readStringFromFile(getTestFilePath("JsonFormatReferenceStr.txt"));
    }

    static String getTestFilePath(String filename) {
        return System.getProperty("user.dir") + "/src/test/resources/" + filename;
    }

    String getTestStr(String filename1, String filename2, String format) {
        String filepath1 = getTestFilePath(filename1);
        String filepath2 = getTestFilePath(filename2);

        String testStr = "";
        try {
            testStr = Differ.generate(filepath1, filepath2, format);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return testStr;
    }

    String getTestStr(String filename1, String filename2) {
        String filepath1 = getTestFilePath(filename1);
        String filepath2 = getTestFilePath(filename2);

        String testStr = "";
        try {
            testStr = Differ.generate(filepath1, filepath2);
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
    void jsonToJsonFormatTest() throws JsonProcessingException {
        String rezStr = getTestStr("file1.json", "file2.json", "json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree1 = mapper.readTree(jsonFormatReferenceStr);
        JsonNode tree2 = mapper.readTree(rezStr);
        assertEquals(tree1, tree2);
    }

    @Test
    void ymlToJsonFormatTest() throws JsonProcessingException {
        String rezStr = getTestStr("file1.yml", "file2.yml", "json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree1 = mapper.readTree(jsonFormatReferenceStr);
        JsonNode tree2 = mapper.readTree(rezStr);
        assertEquals(tree1, tree2);
    }

    void jsonToDefaultFormatTest() {
        String rezStr = getTestStr("file1.json", "file2.json");
        assertEquals(stylishFormatReferenceStr, rezStr);
    }

    @Test
    void ymlToDefaultFormatTest() {
        String rezStr = getTestStr("file1.yml", "file2.yml");
        assertEquals(stylishFormatReferenceStr, rezStr);
    }

}
