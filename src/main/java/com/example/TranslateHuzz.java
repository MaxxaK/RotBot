package com.example;

//import java.util.HashMap;

public class TranslateHuzz {
    public static String translateUzzToRegular(String word) {
        return HuzzMapOps.getUzzLibrary().getOrDefault(word.toLowerCase(), "No translation found");
    }
    
    public static String translateRegularToUzz(String word) {
        for (var entry : HuzzMapOps.getUzzLibrary().entrySet()) {
            if (entry.getValue().contains(word.toLowerCase())) {
                return entry.getKey();
            }
        }
        return "No uzz word found for the given translation";
    }

    public static String translateSentence(String sentence) {
        String[] words = sentence.toLowerCase().split(" ");
        for (int i = 0; i < words.length; i++) {
            if (HuzzMapOps.getUzzLibrary().containsKey(words[i])) {
                words[i] = HuzzMapOps.getUzzLibrary().get(words[i]);
            }
        }
        return String.join(" ", words);
    }
}
