package exercise;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] image) {

        List<String[]> result = new ArrayList<>();

        for (String[] array : image) {
                result.add(Arrays.asList(array).stream().flatMap(u -> Stream.of(u, u)).toArray(String[]::new));
        }
        String[][] my = result.stream().flatMap(u -> Stream.of(u, u)).toArray(String[][]::new);

        //Нативный метод
/*        String[][] tmp = new String[image.length * 2][image[0].length * 2];

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                tmp[i * 2][j *2] = image[i][j];
                tmp[i * 2][j *2 + 1] = image[i][j];
            }
            tmp[i * 2 + 1] = tmp[i * 2];
        }
        System.out.println(Arrays.deepToString(tmp));
        return tmp;*/

        System.out.println(Arrays.deepToString(my));

        return my;
    }
}
// END
