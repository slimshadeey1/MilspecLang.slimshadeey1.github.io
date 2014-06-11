package io.github.slimshadeey1.MilspecLang;

import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;

import java.util.*;

/**
 * Created by Ben Byers on 6/10/2014.
 */
public class WordGroups {
    public static Map<String, List<String>> GroupWordList;

    public static void FinalGroup() {
        for (String group : config.getGroupnames()) {
            List<String> wordgroupout = MilspecLang.wordgroupconfigs.getStringList(group.replace("'", ""));
            GroupWordList.put(group.replace("'", ""), wordgroupout);
        }
    }
    public static String finalgroupID(List<String> value) {
        for (Map.Entry entry : GroupWordList.entrySet()) {
            if (value.equals(entry.getValue())) {
                String key = entry.getKey().toString(); //no break, looping entire hashtable
                return key;
            }
        }
    return null;
    }
}
