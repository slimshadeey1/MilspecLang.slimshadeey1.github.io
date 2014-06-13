package io.github.slimshadeey1.MilspecLang;

/**
 * Created by Ben Byers on 6/9/2014.
 */
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
    static Boolean command = files.getBoolean("commandChecking");
    static String message = files.getString("ChatMessage");
    static List<String> apunishment = files.getStringList("AdvertiserPunishment");
    static List<String> addresses = files.getStringList("Advertisers");
    static Map<String, Integer> advertiserdbin = new HashMap<>();

    public static boolean setad(String a, Integer b){
        if(advertiserdbin.containsKey(a.trim())) {
            addresses.remove(a.trim() + "-" + advertiserdbin.get(a.trim()));
            advertiserdbin.remove(a.trim());
            addresses.add(a.trim()+"-"+b);
            advertiserdbin.put(a.trim(), b);
            files.set("Advertisers", addresses);
            MIG.saveConfig();
            return true;
        }
        return false;
    }
    public static boolean removead(String a){
        if(advertiserdbin.containsKey(a.trim())) {
            addresses.remove(a.trim() + "-" + advertiserdbin.get(a.trim()));
            advertiserdbin.remove(a.trim());
            files.set("Advertisers", addresses);
            MIG.saveConfig();
            return true;
        }
        return false;
    }
    public static boolean addAd(String a,Integer b){
        addresses.add(a.trim() + "-" + b);
        files.set("Advertisers", addresses);
        MIG.saveConfig();
        if(advertiserdbin.containsKey(a.trim())) {
            Integer value = advertiserdbin.get(a.trim()) + b;
            advertiserdbin.remove(a.trim());
            advertiserdbin.put(a.trim(),value);
            return true;
        }
        advertiserdbin.put(a,b);
        return true;
    }

    public static List<String> getPunishmentad(String name) {
        //command@number
        Integer number = advertiserdbin.get(name);
        List<String> com = new ArrayList<String>();
        List<String> punc = new ArrayList<String>();
        List<Integer> punn = new ArrayList<Integer>();
        for(String n:apunishment) {
            punc.add(n.split("@")[0]);
            punn.add(Integer.parseInt(n.split("@")[1]));
        }
        for(Integer i = 0; i <= punc.size();i++){
            Integer loc = punn.indexOf(number);
            com.add(punc.get(loc));
        }
        return com;
    }
    public static void advertiserup(){
        for(String a:addresses){
            String key = a.split("-")[0].trim();
            Integer number = Integer.parseInt(a.split("-")[1]);
            if(advertiserdbin.containsKey(key)){
                Integer value = advertiserdbin.get(key)+number;
                advertiserdbin.remove(key);
                advertiserdbin.put(key,value);

            }else{
                advertiserdbin.put(key,number);
            }
        }
    }
    public static void enable() {
        advertiserup();
        files.options().copyDefaults(true);
        //Bukkit.getLogger().info("Advertiser debug: ");//cool commands can be made :D
        // Create group files
        //save files
        MIG.saveConfig();
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

    public static List<String> getPunishment() {
        return apunishment;
    }

    public static String chatmessage() {
        return message;
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

    public static List<String> adnames() {
        List<String> names = new ArrayList<String>();
        for (String raw:config.addresses) {
            names.add(raw.split("-")[0].trim());
        }
        return names;
    }

    public static boolean commandenabled() {
        return command;
    }

    public void get() {

    }
}