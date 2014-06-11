package io.github.slimshadeey1.MilspecLang;

import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;

import java.util.*;

/**
 * Created by Ben Byers on 6/10/2014.
 */
public class WordGroups {
//    public static Map<String, List<String>> GroupWordList;
//
//    public static void FinalGroup() {
//        for (String group : config.getGroupnames()) {
//            List<String> wordgroupout = MilspecLang.wordgroupconfigs.getStringList(group.replace("'", ""));
//            GroupWordList.put(group.replace("'", ""), wordgroupout);
//        }
//    }
//    public static String finalgroupID(List<String> value) {
//        for (Map.Entry entry : GroupWordList.entrySet()) {
//            if (value.equals(entry.getValue())) {
//                String key = entry.getKey().toString(); //no break, looping entire hashtable
//                return key;
//            }
//        }
//    return null;
//    }
//}

    //Start of new code
    public static ArrayList words = new ArrayList();
    public static ArrayList messages = new ArrayList();
    public static ArrayList commandexec = new ArrayList();

    public static void seperator() {
        for (String s : config.getCustomset()) {
            String[] parts = config.getCustomset().toString().split("-");
            words.add(parts[0].replace("[","").replace("[",""));
            messages.add(parts[1].replace("[","").replace("[",""));
            commandexec.add(parts[2].replace("[","").replace("[",""));
        }
    }

    public static ArrayList[] wordfinder;{


    }
}