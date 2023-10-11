package hexlet.code.formatters;

import java.util.List;
import java.util.Map;


public class Stylish {

    public static String toStringExceptNull(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return obj.toString();
        }
    }

    public static String getFormattedDiffers(List<Map<String, Object>> diffs) {
        StringBuilder rezStr = new StringBuilder("{\n");

        for (Map diff: diffs) {

            switch (diff.get("Difference").toString()) {
                case "removed" -> rezStr.append("  - " + diff.get("key") + ": "
                        + toStringExceptNull(diff.get("file1Value")) + "\n");
                case "notChanged" -> rezStr.append("    " + diff.get("key") + ": "
                        + toStringExceptNull(diff.get("file1Value")) + "\n");
                case "updated"  -> {
                    rezStr.append("  - " + diff.get("key") + ": " + toStringExceptNull(diff.get("file1Value")) + "\n");
                    rezStr.append("  + " + diff.get("key") + ": " + toStringExceptNull(diff.get("file2Value")) + "\n");
                }
                default  -> rezStr.append("  + " + diff.get("key") + ": " + toStringExceptNull(diff.get("file2Value"))
                        + "\n");
            }
        }

        rezStr.append("}");
        return rezStr.toString();
    }

    /*public static String getFormattedDiffers(List<Differs> diffs) {
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
                default  -> rezStr.append("  + " + diff.getKey() + ": " + toStringExceptNull(diff.getNewValue())
                        + "\n");
            }
        }

        rezStr.append("}");
        return rezStr.toString();
    }
    */
}
