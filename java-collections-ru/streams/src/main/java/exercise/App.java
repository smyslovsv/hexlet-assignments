package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static int getCountOfFreeEmails(List<String> emails) {
        long freeEmails = emails.stream()
                .filter(email -> email.contains("@yandex.ru"))
                .count() +
                emails.stream()
                        .filter(email -> email.contains("@gmail.com"))
                        .count() +
                emails.stream()
                        .filter(email -> email.contains("@hotmail.com"))
                        .count();

        return (int) freeEmails;

    }
}
// END
