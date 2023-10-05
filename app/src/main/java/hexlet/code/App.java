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
public final class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file",
            //defaultValue = "/home/yakov/IdeaProjects/java-project-71/src/main/resources/file1.json")
            defaultValue = "")
    private File filepath1 = new File("");

    @Parameters(index = "1", description = "path to second file",
            //defaultValue = "/home/yakov/IdeaProjects/java-project-71/src/main/resources/file2.json")
            defaultValue = "")
    private File filepath2 = new File("");

    @Option(names = {"-f", "--format"}, description = "[default: stylish]")
    private String format = "stylish";

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        /*if (filepath1.toString().equals("") || filepath2.toString().equals("")) {
            return 0;
        } else {*/
            //System.out.println("Working Directory: " + System.getProperty("user.dir"));
            //filepath1 = new File(System.getProperty("user.dir") + "//" + filepath1.getPath());
            //filepath2 = new File(System.getProperty("user.dir") + "//" + filepath2.getPath());

            //System.out.println("File 1: " + filepath1);
            //System.out.println("File 2: " + filepath2);

        String rezStr = "";
        try {
            rezStr = Differ.generate(filepath1.toString(), filepath2.toString(), format.toLowerCase());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(rezStr);
        //}
        return 0;
    }
}
