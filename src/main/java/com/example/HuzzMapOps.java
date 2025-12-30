package com.example;
import java.util.ArrayList;
import java.util.HashMap;

public class HuzzMapOps {

    private static final HashMap<String, String> uzzLibrary = new HashMap<>();

    static {
        uzzLibrary.put("huzz", "hoes, fine shyt, or bitches");
        uzzLibrary.put("bruzz", "bros, or bro huzz");
        uzzLibrary.put("chuzz", "chopped huzz");
        uzzLibrary.put("fruzz", "freshmen huzz");
    }

    public static HashMap<String,String> getUzzLibrary(){
        return uzzLibrary;
    }

    public static void addUzzWord(String key, String def){
        uzzLibrary.put(key, def);
    }

    public static void removeUzzWord(String word){
        uzzLibrary.remove(word);
    }

    private static final ArrayList<String> bannedUzz = new ArrayList<>();

    static{
        bannedUzz.add("hitluzz");
    }

    public static ArrayList<String> getBannedList(){
        return bannedUzz;
    }

    public static boolean searchBanned(String word){
        for(String w : bannedUzz){
            if(word.contains(w))
                return false;
        }
        return true;
    }

    public static void banUzz(String word){
        bannedUzz.add(word);
    }

    public static boolean unbanUzz(String word){
        return bannedUzz.remove(word);
    }

    //WERE ORIGINAL METHODS USING SCANNER, THAT ARE NOW IN TranslateHuzz CLASS

    /**
     * translates from uzz to regular, or adds the uzz word to the hashmap using method
     * @param word to be translated from uzz to a regular word
     * @param map HashMap containing the uzz word matched with its definition
     * @param in the Scanner from the main method
     * @return the definition of the uzz word
     */
    /*
    public static String uzzTranslation(String word, HashMap<String, String> map, Scanner in){
        String ans = map.getOrDefault(word, "No translation available");

        if(ans.equals("No translation available")){
            System.out.println("\nIt seems your word is not in the database. Would you like to add it? (Y/N)");
            char reply = in.next().charAt(0);
            in.nextLine();
            if(reply == 'Y' || reply == 'y')
                ans = addDefinition(map, in);
        }
        return ans;
    }
    */
    /**
     * translates from regular to uzz, or adds the uzz word to the hashmap using method
     * @param word to be translated from regular word to uzz word
     * @param map HashMap containing the uzz word matched with its definition
     * @param in the Scanner from the main method
     * @return the uzz word associated with the definition
     */
    /*
    public static String defTranslation(String word, HashMap<String, String> map, Scanner in){
        String ans = map.getOrDefault(word, "No translation available");
        for(Map.Entry<String, String> w : map.entrySet()){
            if(w.getValue().equals(word))
                return w.getKey();
        }
        if(ans.equals("No translation available")){
            System.out.println("\nIt seems your translation is not in the database. Would you like to add it? (Y/N)");
            char reply = in.next().charAt(0);
            in.nextLine();
            if(reply == 'Y' || reply == 'y')
                ans = addTranslation(map, in);
        }
        return ans;
    }
    */

    /**
     * adds a definition for the chosen uzz word
     * @param map HashMap containing the uzz word matched with its definition
     * @param in Scanner passed from main to translation to this method
     * @return definition of uzz word
     */
    /* 
    public static String addDefinition(HashMap<String,String> map, Scanner in){
        System.out.println("\nAdd the \"uzz\" word first.");
        String keyWord = in.nextLine();
        System.out.println("\nNow add its definition!");
        String def = in.nextLine();
        map.put(keyWord,def);
        System.out.println("\nYour word has been added!");
        return def;
    }
    */

    /**
     * creates uzz word from regular word. if second letter is vowel, replaces with uzz. if second letter is consonant, uses first two letters
     * @param map HashMap containing the uzz word matched with its definition
     * @param in Scanner passed from main to translation to this method
     * @return uzz word from given definition
     */
    /*
    public static String addTranslation(HashMap<String, String> map, Scanner in){
        System.out.println("\nAdd your word you want made into \"uzz\".");
        String def = in.nextLine();
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        String starter = def.substring(0, 2);
        for(char c : vowels){
            if(starter.indexOf(c) != -1){
                String keyWord = starter.substring(0,1) + "uzz";
                map.put(keyWord, def);
                return keyWord;
            }
        }
        map.put(starter + "uzz", def);
        return starter + "uzz";
    }
    */


}
