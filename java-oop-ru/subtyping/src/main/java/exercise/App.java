package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> reversStorage = new HashMap<>();
        Set<String> keys = storage.toMap().keySet();
        for (String key : keys) {
            reversStorage.put(new String(storage.get(key, "default")), new String(key));
            storage.unset(key);
        }
        Set<String> keys2 = reversStorage.keySet();
        for (String key : keys2) {
            storage.set(new String(key), new String(reversStorage.get(key)));
        }
    }
}
// END
