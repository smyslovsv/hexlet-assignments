package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static boolean scrabble (String charArray, String word) {
        boolean check = true;
        System.out.println("Исходный набор - " + charArray);
        System.out.println("      символов - " + charArray.length());
        System.out.println("    уникальных - " + countUniqueCharacters(charArray));

        System.out.println("Исходное слово - " + word);
        System.out.println("      символов - " + word.length());
        System.out.println("    уникальных - " + countUniqueCharacters(word));


        if (charArray.length() > word.length() || countUniqueCharacters(charArray) > countUniqueCharacters(word)) {

            System.out.println("Количество символов и уникальных символов во входном наборе больше, чем в слове");
            List<Character> charsArray = convertStringToCharList(charArray.toLowerCase());
            List<Character> charsWord = convertStringToCharList(word.toLowerCase());

            for (char item : charsWord) {
                    if (charsArray.contains(item)) {
                        charsArray.remove(Character.valueOf(item));
                        System.out.println("Найден символ - " + item);
                    } else {
                        System.out.println("Cимвол - " + item + " не найден");
                        check = false;
                        break;
                    }
                //array = list.toArray(new String[list.size()]);
            }
        } else {
            System.out.println("Количество символов и уникальных символов во входном наборе меньше, чем в слове");
            check = false;
        }

        return check;
    }
    public static int countUniqueCharacters(String input) {
        String buffer = "";
        String inputLowerCase = input.toLowerCase();
        for (int i = 0; i < input.length(); i++) {
            if (!buffer.contains(String.valueOf(inputLowerCase.charAt(i)))) {
                buffer += inputLowerCase.charAt(i);
            }
        }
        return buffer.length();
    }
    public static List<Character> convertStringToCharList(String str) {
        List<Character> chars = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            chars.add(ch);
        }
        return chars;
    }
}
//END
