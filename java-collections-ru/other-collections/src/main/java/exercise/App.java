package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
class App {
    public static LinkedHashMap genDiff(Map<String, Object> data1, Map<String, Object> data2){

        LinkedHashMap<String, String> diffData = new LinkedHashMap();

        for (String key1 : data1.keySet()) {
            if (data2.containsKey(key1)) {
                boolean equals = data2.get(key1).equals(data1.get(key1));
                if (equals) {
                    diffData.put(key1, "unchanged");
                    data2.remove(key1);
                } else {
                    diffData.put(key1, "changed");
                    data2.remove(key1);
                }
            } else {
                diffData.put(key1, "deleted");
            }
        }

        if (data2.isEmpty()) {
            return diffData;
        } else {
            for (String key2 : data2.keySet()) {
                diffData.put(key2, "added");
            }
            return diffData;
        }
    }
}
//END
