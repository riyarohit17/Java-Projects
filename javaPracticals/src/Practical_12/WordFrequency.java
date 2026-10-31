package Practical_12;


import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {

        String paragraph = "Java is easy and Java is powerful "
                + "and Java is popular";

        String[] words = paragraph.toLowerCase().split("\\W+");

        HashMap<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(map.entrySet());

        list.sort((a, b) -> {
            int result = b.getValue().compareTo(a.getValue());
            if (result == 0) {
                return a.getKey().compareTo(b.getKey());
            }
            return result;
        });

        System.out.println("Word Frequency (Highest First):");

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " : "
                    + entry.getValue());
        }
    }
}
