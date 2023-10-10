import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReferenceStr {

    public String getReferenceStrFromResource(String filename) {
        String refStrFile = System.getProperty("user.dir") + "/src/test/resources/" + filename;
        String referenceStr = "";
        try {
            referenceStr = new String(Files.readAllBytes(Paths.get(refStrFile)));
        } catch (IOException e) {
            System.out.println("Could not read file: " + refStrFile);
            System.out.println(e.getMessage());
        }

        return referenceStr;
    }

}
