package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> list = new ArrayList<>();
        try {
            Field[] fields = address.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (field.get(address) == null) {
                        String fieldName = field.toString();
                        int index = fieldName.lastIndexOf(".") + 1;
                        list.add(fieldName.substring(index));
                        System.out.println(field + " : " + fieldName.substring(index));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> map = new HashMap<>();
        try {
            Field[] fields = address.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                List<String> list = new ArrayList<>();
                String fieldName = field.getName(); //.toString();
                //int index = fieldName.lastIndexOf(".") + 1;.substring(index)

                if (field.isAnnotationPresent(NotNull.class)) {
                    if (field.get(address) == null) {
                        list.add("can not be null");
                        map.put(fieldName, list);
                    }
                }
                if (field.isAnnotationPresent(MinLength.class) && field.get(address) != null) {
                    if (field.get(address).toString().length() < field.getAnnotation(MinLength.class).minLength()) {
                        list.add("length less than " + field.getAnnotation(MinLength.class).minLength());
                        map.put(fieldName, list);
                    }
                }
                System.out.println(field + " : " + map);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
}
// END
