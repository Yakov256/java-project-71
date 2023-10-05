package hexlet.code.formatters;

import hexlet.code.Differs;
import hexlet.code.DiffersStates;
import java.util.List;

public class Plain {

    private static String getPlainFormattedString(Object value) {
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

    private static String getStringOrComplexValue(Object value) {
        if (value == null) {
            return null;
        }
        return getPlainFormattedString(value);
    }

    public static String getFormattedDiffers(List<Differs> diffs) {
        StringBuilder rezStr = new StringBuilder();
        boolean strAdded = false;

        for (Differs diff: diffs) {
            if (strAdded) {
                rezStr.append("\n");
                strAdded = false;
            }

            if (diff.getStatus() == DiffersStates.removed) {
                rezStr.append("Property '" + diff.getKey() + "' was removed");
                strAdded = true;
            } else if (diff.getStatus() == DiffersStates.updated) {
                rezStr.append("Property '" + diff.getKey() + "' was updated. From "
                        + getStringOrComplexValue(diff.getOldValue())
                        + " to " + getStringOrComplexValue(diff.getNewValue()));
                strAdded = true;
            } else if (diff.getStatus() == DiffersStates.added) {
                rezStr.append("Property '" + diff.getKey() + "' was added with value: "
                        + getStringOrComplexValue(diff.getNewValue()));
                strAdded = true;
            }
        }

        return rezStr.toString();
    }

}
