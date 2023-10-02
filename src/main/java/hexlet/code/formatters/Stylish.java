package hexlet.code.formatters;

import hexlet.code.Differs;
import hexlet.code.DiffersStates;

import java.util.List;
//import java.util.Map;

public class Stylish {

    public static String getFormattedDiffers(List<Differs> diffs) {

        StringBuilder rezStr = new StringBuilder("{\n");

        for (Differs diff: diffs) {

            //map1Value = toStringExceptNull(treeMap1.get(entry.getKey()));
            //map2Value = toStringExceptNull(treeMap2.get(entry.getKey()));
            /*
            if (treeMap1.get(entry.getKey()) == null) {
                map1Value = null;
            } else {
                map1Value = treeMap1.get(entry.getKey()).toString();
            }

            if (treeMap2.get(entry.getKey()) == null) {
                map2Value = null;
            } else {
                map2Value = treeMap2.get(entry.getKey()).toString();
            }
             */

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

            /*
                if (diff.getStatus() == DiffersStates.removed) { //удалено во тором файле
                    //rezStr.append("- " + ": " + map1Value + "\n");
                    rezStr.append("- " + ": " + diff.getOldValue() + "\n");
                } else if (map1Value.equals(map2Value)) { // значения равны
                    //rezStr.append("  " + ": " + map1Value + "\n");
                   //return DiffersStates.notChanged;
                } else { //else if (!map1Value.equals(map2Value))  //изменено во тором файле
                    //rezStr.append("- " + ": " + map1Value + "\n");
                    //rezStr.append("+ " + ": " + map2Value + "\n");
                    return DiffersStates.updated;
                }

                if (map2Value != null) { //Есть только в первом
                    //rezStr.append("+ " + ": " + map2Value + "\n");
                    return DiffersStates.removed;
                }

            rezStr.append(getLineDifferences(entry.getKey(), map1Value, map2Value));
             */
        }

        rezStr.append("}\n");

        return rezStr.toString();
    }
}
