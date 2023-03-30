package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {
    String path = "";

    public FileKV(String path, KeyValueStorage map) {
        this.path = path;
        Utils.writeFile(path, Utils.serialize(map.toMap()));
    }
    @Override
    public void set(String key, String value) {
        Map<String, String> storage = Utils.unserialize(Utils.readFile(this.path));
        storage.put(key, value);
        Utils.writeFile(this.path, Utils.serialize(storage));
    }

    @Override
    public void unset(String key) {
        Map<String, String> storage = Utils.unserialize(Utils.readFile(this.path));
        storage.remove(key);
        Utils.writeFile(this.path, Utils.serialize(storage));
    }

    @Override
    public String get(String key, String defaultValue) {
        return Utils.unserialize(Utils.readFile(this.path)).getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(Utils.unserialize(Utils.readFile(this.path)));
    }
}
// END
