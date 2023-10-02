package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {

    public static String getFormattedString(List<Differs> diffs, String formatName) {

        return switch (formatName) {
            case "plain" -> Plain.getFormattedDiffers(diffs);
            default        -> Stylish.getFormattedDiffers(diffs);
        };

    }
}
