package io.github.slimshadeey1.MilspecLang;

/**
 * Created by Ben Byers on 6/9/2014.
 */
import java.io.*;
import java.util.*;

import org.bukkit.configuration.file.*;
import org.bukkit.plugin.Plugin;

public class config {
    static Plugin MIG = MilspecLang.getPlugin();
    static FileConfiguration files = MIG.getConfig();
    static List<String> swears = files.getStringList("swears");
    static List<String> forbidden1 = files.getStringList("forbiddenGroup1");
    static List<String> customset = files.getStringList("CustomWordActions");
    //    static Integer numberofgroups = files.getInt("numberofgroups");
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
//    public static List<String> getGroupnames() {
//        ArrayList<String> groupa = new ArrayList<String>();
//        for (int a = 1; a < getNumberofgroups() + 1; a++) {
//            groupa.add("groupnumber" + Integer.toString(a));
//        }
//        return groupa;
//    }
//    public static void addgroup() {
//        for(String group : getGroupnames()) {
//            MilspecLang.wordgroupconfigs.createSection(group.replace("'", ""));
//        }
//        MilspecLang.saveNewConfig();
//    }
//    public static List<String> addmessagegroup() {
//        ArrayList<String> groupa = new ArrayList<String>();
//        for (int a = 1; a < getNumberofgroups() + 1; a++) {
//            groupa.add("groupmessage" + Integer.toString(a));
//        }
//        for(String group : groupa) {
//            MilspecLang.wordgroupconfigs.createSection(group.replace("'", ""));
//        }
//        MilspecLang.saveNewConfig();
//        return groupa;
//    }
//    public static List<String> addcommandgroup() {
//        ArrayList<String> groupa = new ArrayList<String>();
//        for (int a = 1; a < getNumberofgroups() + 1; a++) {
//            groupa.add("groupcommand" + Integer.toString(a));
//        }
//        for(String group : groupa) {
//            MilspecLang.wordgroupconfigs.createSection(group.replace("'", ""));
//        }
//        MilspecLang.saveNewConfig();
//        return groupa;
//    }


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
    public static List<String> getCustomlist(){
        List<String> customsetlist = new ArrayList<String>();
        for(String a : getWords()) {
            String setting = "- "+WordGroups.words.indexOf(a)+" "+a+"-"+WordGroups.messages.get(WordGroups.words.indexOf(a))+"-"+WordGroups.commandexec.get(WordGroups.words.indexOf(a));
            customsetlist.add(setting);
        }
        return customsetlist;
    }
    public static boolean editcustommessage(Integer id, String newword){
        WordGroups.messages.set(id, newword);
        refreshconfig();
        return true;
    }
    public static boolean editcustomcommand(Integer id, String newword){
        WordGroups.commandexec.set(id, newword);
        refreshconfig();
        return true;
    }
    public static boolean addcustomset(String word, String message, String command) {
        for (String s : getWords()) {
            if (word.equalsIgnoreCase(s)) {
                return false;
            }
        }
        WordGroups.words.add(word);
        WordGroups.messages.add(message);
        WordGroups.messages.add(command);
        String createconf = " " + word + "-" + message + "-" + command;
        files.set("CustomWordActions", createconf);
        MIG.saveConfig();
        return true;
    }
    public static void refreshconfig() {
        for(String a : getWords()) {
            String setting = " "+a+"-"+WordGroups.messages.get(WordGroups.words.indexOf(a))+"-"+WordGroups.commandexec.get(WordGroups.words.indexOf(a));
            files.set("CustomWordActions", setting.replaceAll("]", ""));
        }
    }
    public static boolean removecustomset(String word) {
        for (String s : getWords()) {
            if (word.equalsIgnoreCase(s)) {
                Integer id = config.getWords().indexOf(word);
                WordGroups.words.remove(word);
                WordGroups.messages.remove(id);
                WordGroups.commandexec.remove(id);
                for(String a : getWords()) {
                    String setting = " "+a+"-"+WordGroups.messages.get(WordGroups.words.indexOf(a))+"-"+WordGroups.commandexec.get(WordGroups.words.indexOf(a));
                    files.set("CustomWordActions", setting.replaceAll("]", ""));
                }
                MIG.saveConfig();
                return true;
            }
        }
        return false;
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

    public static boolean addforbidden(String forbiddenn) {
        for (String s : forbidden1) {
            if (forbiddenn.equalsIgnoreCase(s)) {
                return false;
            }
        }
        forbidden1.add(forbiddenn);
        files.set("forbidden", forbidden1);
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

    public static boolean removeforbidden(String swear) {
        for (String s : forbidden1) {
            if (swear.equalsIgnoreCase(s)) {
                forbidden1.remove(swear);
                files.set("forbidden", forbidden1);
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

    public static List<String> getForbiddenlist() {
        return forbidden1;
    }
    public static List<String> getCustomset() {
        return customset;
    }

//    public static Integer getNumberofgroups() {
//        return numberofgroups;
//    }

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
        String message = WordGroups.messages.get(i).toString();
        return message;
    }
    public static String getCommand(Integer i) {
        String commandexec = WordGroups.commandexec.get(i).toString();
        return commandexec;
    }
    public static boolean commandenabled() {
        return command;
    }
}