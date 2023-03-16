package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Iterator;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        System.out.println("Входной список книг - " + books);
        System.out.println("Книга на проверку - " + where);

        List<Map<String, String>> result = new ArrayList<>();

        Iterator<Map<String, String>> it = books.iterator();
        while(it.hasNext()) {
            Map<String, String> cur = it.next();
            if(bookFind(cur, where)) {
                result.add(cur);
                System.out.println(" Книга соответствует");
            } else {
                System.out.println(" Книга НЕ соответствует");
            }
        }
        System.out.println("Найденные книги - " + result + "/n");

        return result;
    }
    public static boolean bookFind(Map<String, String> book, Map<String, String> where) {
        System.out.println(" Проверка книги - " + book);
        boolean check = false;
        for (Map.Entry<String, String> entry : where.entrySet()) {
            if (book.containsKey(entry.getKey())) {
                if (book.get(entry.getKey()).equals(entry.getValue())) {
                    System.out.println("  Ключ " + entry.getKey() + " в книге найден, значение " + entry.getValue() + " совпало");
                    check = true;
                } else {
                    System.out.println("  Ключ " + entry.getKey() + " в книге найден, значение " + entry.getValue() + " НЕ совпало");
                    return false;
                }
            } else {
                System.out.println("  Ключ %s в книге НЕ найден" + entry.getKey());
                return false;
            }
        }
        return check;
    }
}
//END
