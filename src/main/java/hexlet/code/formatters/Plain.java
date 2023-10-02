package hexlet.code.formatters;

import hexlet.code.Differs;
import hexlet.code.DiffersStates;

import java.util.List;

public class Plain {

    private static String getStringOrComplexValue(Object value) {
        //System.out.println(value.getClass().getSimpleName());
        if (value == null) {
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

    public static String getFormattedDiffers(List<Differs> diffs) {
        StringBuilder rezStr = new StringBuilder();

        for (Differs diff: diffs) {
            System.out.println(diff);
            /*switch (diff.getStatus()) {
                case DiffersStates.removed -> rezStr.append("- " + ": " + diff.getOldValue() + "\n");
                case DiffersStates.notChanged -> rezStr.append("  " + ": " + diff.getOldValue() + "\n");
                case DiffersStates.updated  -> {
                    rezStr.append("- " + ": " + diff.getOldValue() + "\n");
                    rezStr.append("+ " + ": " + diff.getNewValue() + "\n");
                }
                case DiffersStates.removed  -> rezStr.append("+ " + ": " + diff.getNewValue() + "\n");
            }
            */
            if (diff.getStatus() == DiffersStates.removed) {
                rezStr.append("Property '" + diff.getKey() + "' was removed\n");
            } else if (diff.getStatus() == DiffersStates.updated) {
                rezStr.append("Property '" + diff.getKey() + "' was updated. From "
                        + getStringOrComplexValue(diff.getOldValue())
                        + " to " + getStringOrComplexValue(diff.getNewValue()) + "\n");
            } else if (diff.getStatus() == DiffersStates.added) {
                rezStr.append("Property '" + diff.getKey() + "' was added with value: '"
                        + getStringOrComplexValue(diff.getNewValue()) + "'\n");
            }
        }

        return rezStr.toString();
    }

}
