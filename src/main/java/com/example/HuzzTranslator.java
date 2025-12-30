//WAS THE ORIGINAL CONCEPT IDEA USING SCANNER AND OUTPUTTING TO CONSOLE

/*package com.example;


import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HuzzTranslator{

    public static void main(String[] args) throws NoSuchElementException{

        HashMap<String, String> uzzLibrary = new HashMap<>();
        uzzLibrary.put("huzz", "hoes, fine shyt, or bitches");
        uzzLibrary.put("bruzz", "bros, or bro huzz");

        try (Scanner in = new Scanner(System.in)) {
            boolean exit = false;
            while(!exit){
                System.out.println("\nPlease choose from one of the following. Enter \"stop\" to exit.");
                System.out.println("1. uzz to translation");
                System.out.println("2. regular word to uzz");
                System.out.println("3. sentence translation");
                String choice = in.next();
                if(choice.toLowerCase().equals("stop"))
                    exit = true;
                else{
                    switch(choice){
                        case "1" -> {
                            in.nextLine();
                            System.out.println("Please enter your \"uzz\" word. Enter \"stop\" to exit.");
                            String word = in.nextLine().toLowerCase();
                            if(!word.equals("stop"))
                                System.out.print(word.toUpperCase() + ": " + HuzzMapOps.uzzTranslation(word, uzzLibrary, in) + "\n");
                            else
                                exit = true;
                        }
                        case "2" -> {
                            in.nextLine();
                            System.out.println("Please enter your \"regular\" word. Enter \"stop\" to exit.");
                            String word = in.nextLine().toLowerCase();
                            if(!word.equals("stop"))
                                System.out.print(word + ": " + HuzzMapOps.defTranslation(word, uzzLibrary, in) + "\n");
                            else
                                exit = true;
                        }
                        case "3" -> {
                            in.nextLine();
                            System.out.println("Please enter the sentence to be translated. Only \"uzz\" words will become normal text. Enter \"stop\" to exit.");
                            String sentence = in.nextLine().toLowerCase();
                            String[] words;
                            if(!sentence.equals("stop")){
                                words = sentence.split(" ");
                                for (int i = 0; i < words.length; i++) {
                                    for(String key : uzzLibrary.keySet()){
                                        if(words[i].equals(key)){
                                            words[i] = uzzLibrary.get(key);
                                        }
                                    }
                                }
                                System.out.println();
                                for(String word : words){
                                    System.out.print(word + " ");
                                }
                                System.out.println();
                            }
                            else
                                exit = true;
                        }
                    }
                }
            }
        }
    }
}
    */