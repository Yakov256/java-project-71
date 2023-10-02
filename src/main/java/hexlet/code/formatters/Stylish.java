package hexlet.code.formatters;

import hexlet.code.Differs;
import hexlet.code.DiffersStates;

import java.util.List;
//import java.util.Map;

public class Stylish {

    public static String getFormattedDiffers(List<Differs> diffs) {

        StringBuilder rezStr = new StringBuilder("{\n");

        for (Differs diff: diffs) {
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
                rezStr.append("- " + diff.getKey() + ": " + diff.getOldValue().toString() + "\n");
            } else if (diff.getStatus() == DiffersStates.notChanged) {
                rezStr.append("  " + diff.getKey() + ": " + diff.getOldValue().toString() + "\n");
            } else if (diff.getStatus() == DiffersStates.updated) {
                rezStr.append("- " + diff.getKey() + ": " + diff.getOldValue().toString() + "\n");
                rezStr.append("+ " + diff.getKey() + ": " + diff.getNewValue().toString() + "\n");
            } else if (diff.getStatus() == DiffersStates.added) {
                rezStr.append("+ " + diff.getKey() + ": " + diff.getNewValue().toString() + "\n");
            }
        }

        rezStr.append("}\n");
        return rezStr.toString();
    }
}
