package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {
    public static String getForwardedVariables(String conf) {
        System.out.println("начало:" + conf + "конец\n");

        System.out.println("удаляем environment и лишние пробелы\n");
        String list = Arrays.stream(conf.split("\n"))
                .filter(str -> str.startsWith("environment"))
                .map(str -> str.replaceAll(" ", ""))
                .map(str -> str.replaceAll("environment=", ""))
                .peek(System.out::println)
                .collect(Collectors.joining(","));

        System.out.println("удаляем кавычки и X_FORWARDED_\n");

        list = Arrays.stream(list.split(","))
                .filter(str -> str.length() > 0)
                .map(str -> str.replaceAll("\"", ""))
                .filter(str -> str.startsWith("var") || str.startsWith("X_FORWARDED_"))
                .map(str -> str.replaceAll("X_FORWARDED_", ""))
                .peek(System.out::println)
                .collect(Collectors.joining(","));

        System.out.println("count elements : " + list.split(",").length);
        if (list.split(",").length == 1) {
            String[] res = list.split(",");
            return res[0];
        } else {
            return list;
        }
    }
}
//END
