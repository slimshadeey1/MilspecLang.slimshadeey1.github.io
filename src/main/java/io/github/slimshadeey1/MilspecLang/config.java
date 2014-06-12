package io.github.slimshadeey1.MilspecLang;

/**
 * Created by Ben Byers on 6/9/2014.
 */
import java.awt.*;
import java.util.*;
import java.util.List;

import org.bukkit.configuration.file.*;
import org.bukkit.plugin.Plugin;

public class config {
    static Plugin MIG = MilspecLang.getPlugin();
    static FileConfiguration files = MIG.getConfig();
    static List<String> swears = files.getStringList("swears");
    static List<String> customset = files.getStringList("CustomWordActions");
    static List<String> exceptions = files.getStringList("exception");
    static Boolean disabled = files.getBoolean("DisableSwearing");
    static Boolean command = files.getBoolean("commandChecking");
    static String message = files.getString("ChatMessage");


    public static void enable() {
        files.options().copyDefaults(true);
        // Create group files
        //save files
        MIG.saveConfig();
    }

    public void get() {

    }

    public static boolean addswear(String swear) {
        for (String s : swears) {
            if (swear.equalsIgnoreCase(s)) {
                return false;
            }
        }
        swears.add(swear);
        files.set("swears", swears);
        MIG.saveConfig();
        return true;
    }

//    public static boolean editcustommessage(Integer id, String newword){
//        WordGroups.messages.set(id, newword);
//        String edited = getWord(id)+"-"+getMessage(id)+"-"+getCommand(id);
//        customset.add(id, edited);
//        files.set("swears", swears);
//        MIG.saveConfig();
//        WordGroups.seperator();
//        return true;
//    }
//    public static boolean editcustomcommand(Integer id, String newword){
//        WordGroups.commandexec.set(id, newword);
//        String edited = getWord(id)+"-"+getMessage(id)+"-"+getCommand(id);
//        customset.add(id, edited);
//        files.set("swears", swears);
//        MIG.saveConfig();
//        WordGroups.seperator();
//        return true;
//    }
    public static boolean addcustomset(String word, String message, String command) {
        for (String s : getWords()) {
            if (word.equalsIgnoreCase(s)) {
                return false;
            }
        }
        WordGroups.words.add(word);
        WordGroups.messages.add(message);
        WordGroups.commandexec.add(command);
        customset.add((word.toString().trim()+"-"+message.toString().trim()+"-"+command.toString().trim()).replaceAll("'", ""));
        files.set("CustomWordActions", customset);
        MIG.saveConfig();
        WordGroups.seperator();
        return true;
    }
    public static boolean removecustomset(Integer id) {
        WordGroups.words.remove(WordGroups.words.get(id));
        WordGroups.messages.remove(WordGroups.messages.get(id));
        WordGroups.commandexec.remove(WordGroups.commandexec.get(id));
                customset.remove(customset.get(id));
                files.set("CustomWordActions", customset);
                MIG.saveConfig();
                WordGroups.seperator();
                return true;
    }
    public static boolean addexempt(String exempt) {
        for (String s : exceptions) {
            if (exempt.equalsIgnoreCase(s)) {
                return false;
            }
        }
        exceptions.add(exempt);
        files.set("exception", exceptions);
        MIG.saveConfig();
        return true;
    }


    public static boolean removeswear(String swear) {
        for (String s : swears) {
            if (swear.equalsIgnoreCase(s)) {
                swears.remove(swear);
                files.set("swears", swears);
                MIG.saveConfig();
                return true;
            }
        }
        return false;
    }

    public static boolean removeexempt(String swear) {
        for (String s : exceptions) {
            if (swear.equalsIgnoreCase(s)) {
                exceptions.remove(swear);
                files.set("exception", exceptions);
                MIG.saveConfig();
                return true;
            }
        }
        return false;
    }

    public static List<String> getswearlist() {
        return swears;
    }

    public static List<String> getexceptionlist() {
        return exceptions;
    }
    public static List<String> getCustomset() {
        return customset;
    }


    public static String chatmessage() {
        return message;
    }

    public static boolean swearisdisabled() {
        return disabled;
    }
    public static List<String> getWords() {
        return WordGroups.words;
    }
    public static String getMessage(Integer i) {
        return WordGroups.messages.get(i);
    }
    public static String getCommand(Integer i) {
        return WordGroups.commandexec.get(i);
    }
    public static String getWord(Integer i) {
        return WordGroups.words.get(i);
    }
    public static boolean commandenabled() {
        return command;
    }
}