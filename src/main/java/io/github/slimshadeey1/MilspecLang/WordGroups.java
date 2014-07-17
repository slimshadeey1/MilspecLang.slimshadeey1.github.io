package io.github.slimshadeey1.MilspecLang;

import java.util.*;

/**
 * Created by Ben Byers on 6/10/2014.
 */
public class WordGroups {
    //Start of new code
    public static ArrayList<String> words = new ArrayList();
    public static ArrayList<String> messages = new ArrayList();
    public static ArrayList<String> commandexec = new ArrayList();
    public static Map<Integer,List<String>> custcommand = new HashMap<>();
    public static List<String> seperator() {
        List<String> debug = new ArrayList<>();
        for(int i = 0; i <= config.getCustomset().size()-1; i++) { //config.customset.toString().length(
            words.add(config.getCustomset().get(i).split("-")[0]);
            messages.add(config.getCustomset().get(i).split("-")[1]);
            commandexec.add(config.getCustomset().get(i).split("-")[2]);
        }
    return debug;
    }
}