package hexlet.code.formatters;

import hexlet.code.DiffersStates;

import java.util.List;
import java.util.Map;

public class Plain {

    private static String getPlainFormattedString(Object value) {

        if (value == null) { ///***
            return null;
        }

        if (value.toString().contains("[") || value.toString().contains("{")) {
            return "[complex value]";
        } else if (value instanceof String) {
            if (value.equals("null")) {
                return null;
            }
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }

    public static String getFormattedDiffers(List<Map<String, Object>> diffs) {
        StringBuilder rezStr = new StringBuilder();

        for (Map diff : diffs) {
            if (diff.get("Difference") == DiffersStates.removed) {
                rezStr.append("Property '" + diff.get("key") + "' was removed");
                rezStr.append("\n");
            } else if (diff.get("Difference") == DiffersStates.updated) {
                rezStr.append("Property '" + diff.get("key") + "' was updated. From "
                        + getPlainFormattedString(diff.get("file1Value"))
                        + " to " + getPlainFormattedString(diff.get("file2Value")));
                rezStr.append("\n");
            } else if (diff.get("Difference") == DiffersStates.added) {
                rezStr.append("Property '" + diff.get("key") + "' was added with value: "
                        + getPlainFormattedString(diff.get("file2Value")));
                rezStr.append("\n");
            }
        }

        if (rezStr.length() > 0) {
            rezStr.setLength(rezStr.length() - 1);
        }
        return rezStr.toString();
    }

}
