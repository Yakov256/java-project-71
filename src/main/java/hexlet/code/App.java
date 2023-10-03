package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "0.7",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file",
            defaultValue = "/home/yakov/IdeaProjects/java-project-71/src/main/resources/file1.json")
            //defaultValue = "/home/yakov/IdeaProjects/java-project-71/src/main/resources/file1.yml")
    private File filepath1 = new File("/resources/file1.json");

    @Parameters(index = "1", description = "path to second file",
            defaultValue = "/home/yakov/IdeaProjects/java-project-71/src/main/resources/file2.json")
            //defaultValue = "/home/yakov/IdeaProjects/java-project-71/src/main/resources/file2.yml")
    private File filepath2 = new File("/resources/file2.json");

    @Option(names = {"-f", "--format"}, description = "[default: stylish]")
    private String format = "stylish";

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        //System.out.println("Working Directory: " + System.getProperty("user.dir"));
        //filepath1 = new File(System.getProperty("user.dir") + "//" + filepath1.getPath());
        //filepath2 = new File(System.getProperty("user.dir") + "//" + filepath2.getPath());

        System.out.println("File 1: " + filepath1);
        System.out.println("File 2: " + filepath2);
        //filepath1 = System.getProperty("user.dir") + filepath1;

        String rezStr = "";
        try {
            //rezStr = Differ.generate(filepath1.toString(), filepath2.toString(), format.toLowerCase());
            rezStr = Differ.generate(filepath1.toString(), filepath2.toString(), format);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(rezStr);
        return 0;
    }
}
