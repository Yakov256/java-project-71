package hexlet.code.formatters;

import hexlet.code.Differs;
import java.util.List;

public class Stylish {

    public static String toStringExceptNull(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return obj.toString();
        }
    }

    public static String getFormattedDiffers(List<Differs> diffs) {
        StringBuilder rezStr = new StringBuilder("{\n");

        for (Differs diff: diffs) {

            switch (diff.getStatus()) {
                case removed -> rezStr.append("  - " + diff.getKey() + ": "
                        + toStringExceptNull(diff.getOldValue()) + "\n");
                case notChanged -> rezStr.append("    " + diff.getKey() + ": "
                        + toStringExceptNull(diff.getOldValue()) + "\n");
                case updated  -> {
                    rezStr.append("  - " + diff.getKey() + ": " + toStringExceptNull(diff.getOldValue()) + "\n");
                    rezStr.append("  + " + diff.getKey() + ": " + toStringExceptNull(diff.getNewValue()) + "\n");
                }
                default  -> rezStr.append("  + " + diff.getKey() + ": " + toStringExceptNull(diff.getNewValue()) + "\n");
            }
            /*
            if (diff.getStatus() == DiffersStates.removed) {
                rezStr.append("- " + diff.getKey() + ": " + toStringExceptNull(diff.getOldValue()) + "\n");
            } else if (diff.getStatus() == DiffersStates.notChanged) {
                rezStr.append("  " + diff.getKey() + ": " + toStringExceptNull(diff.getOldValue()) + "\n");
            } else if (diff.getStatus() == DiffersStates.updated) {
                rezStr.append("- " + diff.getKey() + ": " + toStringExceptNull(diff.getOldValue()) + "\n");
                rezStr.append("+ " + diff.getKey() + ": " + toStringExceptNull(diff.getNewValue()) + "\n");
            } else if (diff.getStatus() == DiffersStates.added) {
                rezStr.append("+ " + diff.getKey() + ": " + toStringExceptNull(diff.getNewValue()) + "\n");
            }
             */
        }

        rezStr.append("}");
        return rezStr.toString();
    }
}
