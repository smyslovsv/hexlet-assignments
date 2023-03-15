package exercise;

import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

// BEGIN
class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int count) {
        Map<Double, String> treeMap = new TreeMap<>();

        for (Home apartment : apartments) {
            treeMap.put(apartment.getArea(), apartment.toString());
        }
        List<String> sortedList = new ArrayList<>();
        for (Map.Entry<Double, String> entry : treeMap.entrySet()) {
            if (sortedList.size() < count) {
                sortedList.add(entry.getValue());
            } else {
                break;
            }
        }
        return sortedList;
    }
}
// END
