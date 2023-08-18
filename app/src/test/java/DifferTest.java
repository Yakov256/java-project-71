
import hexlet.code.Differ;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

//import static org.assertj.core.api.Assertions.assertThat;
public class DifferTest {


    @Test
    void getMapsDifferencesTest() throws ParseException {
        Map<String, String> testMap1 = new HashMap<>();
        testMap1.put("host","hexlet.io");
        testMap1.put("timeout", "50");
        testMap1.put("proxy", "123.234.53.22");
        testMap1.put("follow", "false");

        Map<String, String> testMap2 = new HashMap<>();
        testMap2.put("timeout","20");
        testMap2.put("verbose", "true");
        testMap2.put("host", "hexlet.io");

        String referenceStr = "{\n" +
                "- follow: false\n" +
                "  host: hexlet.io\n" +
                "- proxy: 123.234.53.22\n" +
                "- timeout: 50\n" +
                "+ timeout: 20\n" +
                "+ verbose: true\n" +
                "}\n";

        String rezStr = Differ.getMapsDifferences(testMap1, testMap2);
        System.out.println(rezStr);

        //assertThat(true, rezStr.equals(referenceStr));
    }

}
