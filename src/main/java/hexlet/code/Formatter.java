package hexlet.code;

public class Formatter {

    public static void sout(Formats format, String str) {
        if (format == Formats.plain) {
            //plain formatter
        } else {
            System.out.println("Differences between files:");
            System.out.println(str);
        }
    }
}
