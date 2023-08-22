package hexlet.code;

/*
 * Picocli: Checksum calculator
 * Specify another file name as command line parameter, e.g. '/usr/bin/java' or '/proc/cpuinfo'
 * Taken from: <a href="https://picocli.info/#_example_application">Picocli user manual</a>
 * @author Remko Popma
 */
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

//import java.io.*;
import java.io.File;
//import java.nio.file.Files;
//import java.util.HashMap;
import java.util.concurrent.Callable;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
//public class App {

    @Parameters(index = "0", description = "path to first file",
            defaultValue = "/home/yakov/IdeaProjects/java-project-71/app/src/main/resources/file1.json")
    private File filepath1 = new File("/resources/file1.json");

    @Parameters(index = "1", description = "path to second file",
            defaultValue = "/home/yakov/IdeaProjects/java-project-71/app/src/main/resources/file2.json")
    private File filepath2 = new File("/resources/file2.json");

    @Option(names = {"-f", "--format"}, description = "[default: stylish]")
    private String format = "stylish";

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        //System.out.println("----------------------");
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }


    @Override
    public Integer call() throws Exception {

        System.out.println(filepath1.toString());
        System.out.println(filepath2.toString());
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String rezStr = Differ.generate(filepath1.toString(), filepath2.toString());

        System.out.println("------ sorted rezult ------");
        System.out.println(rezStr);

        //Map<String,String> jsonMap1 = readMapFromJsonFile(filepath1.toString());
        //Map<String,String> jsonMap2 = readMapFromJsonFile(filepath2.toString());

        //System.out.println("---------- MAPs ----------");
        //System.out.println(jsonMap1);
        //System.out.println(jsonMap2);


        //byte[] fileContents = Files.readAllBytes(file.toPath());
        //byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
        //System.out.printf(algorithm + " hash of " + file.getPath() + ": %0" + (digest.length*2)
        // + "x%n", new BigInteger(1, digest));
        return 0;
    }
}
