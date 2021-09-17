package ru.lmru.mentoring.education.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class SquareMeter {
    public static void main(String[] args) {
        //входные слова -> "нос", "зов", "сон", "атлас", "салат", "воз", "марка", "рамка", "лапша", "масло", "смола",
        // "макар"
//        final var words = ;//
        List<String> words = new ArrayList<>(asList("нос", "зов", "сон", "атлас", "салат", "воз", "марка", "рамка",
                                                    "лапша", "масло", "смола", "макар"));
        System.out.println(groupByAnagram(words));
        //вывести результат -> [[нос, сон], [марка, рамка, макар], [атлас, салат], [зов, воз], [масло, смола], [лапша]]
    }

    public static Collection<List<String>> groupByAnagram(List<String> input) {
        List<String> sortedWords = new ArrayList<>();
        for (String word : input) {
            sortedWords.add(sortWord(word));
        }

        Map<String, ArrayList<String>> wordsMap = new HashMap<>();
        for (int i = 0; i < sortedWords.size() - 1; i++) {
            if (wordsMap.get(sortedWords.get(i)) == null) {
                wordsMap.put(sortedWords.get(i), new ArrayList<>());
            }
            wordsMap.get(sortedWords.get(i)).add(input.get(i));
        }

        return Collections.emptyList();
    }

    private static String sortWord(String word) {
        String temp = word.chars().sorted().collect(StringBuilder::new,
                                                    StringBuilder::appendCodePoint, StringBuilder::append)
                          .toString();

        return temp;
    }
}
