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
            reversStorage.put(storage.get(key, "default"), key);
            storage.unset(key);
        }
        Set<String> keys2 = reversStorage.keySet();
        for (String key : keys) {
            storage.set(reversStorage.get(key), key);
        }
    }
}
// END
