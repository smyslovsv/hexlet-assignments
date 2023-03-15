package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map getWordCount (String pr) {
        Map<String, Integer> map = new HashMap<>();
        String[] words = pr.split(" ");
        //System.out.println("Входное предложение : " + pr);
        //System.out.println("map -" + map  + ";");

        if (pr.length() != 0) {
            for (String word : words) {
                //System.out.println("Слово - " + word + ";");
                if (map.containsKey(word)) {
                    map.replace("" + word, map.get(word) + 1);
                } else {
                    map.put("" + word, 1);
                }
            }
        }
        //System.out.println("Результат : " + map + ";");
        return map;
    }

    public static String toString(Map<String, Integer> map) {
        String result = "{";
        if (!map.isEmpty()) {
            result += "\n";
            for (String key : map.keySet()) {
                result += "  " + key.toString() + ": " + map.get(key) + "\n";
            }
        }
        return result + "}";
    }
}
//END
