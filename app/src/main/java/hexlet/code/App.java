package hexlet.code;

/**
 * Picocli: Checksum calculator
 * Specify another file name as command line parameter, e.g. '/usr/bin/java' or '/proc/cpuinfo'
 * Taken from: <a href="https://picocli.info/#_example_application">Picocli user manual</a>
 * @author Remko Popma
 */
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command(name = "App", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
//public class App {

    //@Parameters(index = "0", description = "The file whose checksum to calculate.", defaultValue = "/etc/hosts")
    //private File file = new File("/etc/hosts");

    //@Option(names = {"-a", "--algorithm"}, description = "MD5, SHA-1, SHA-256, ...")
    private String algorithm = "MD5";

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        System.out.println("----------------------");
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        //byte[] fileContents = Files.readAllBytes(file.toPath());
        //byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
        //System.out.printf(algorithm + " hash of " + file.getPath() + ": %0" + (digest.length*2) + "x%n", new BigInteger(1, digest));
        return 0;
    }
}