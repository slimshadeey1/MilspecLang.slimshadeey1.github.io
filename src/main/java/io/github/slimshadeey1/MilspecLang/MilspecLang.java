package io.github.slimshadeey1.MilspecLang;

import org.bukkit.*;

import java.io.*;
import java.util.List;

import org.bukkit.configuration.file.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by Ben Byers on 6/8/2014.
 */
public class MilspecLang extends JavaPlugin implements Listener {
    private static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    } //to allow access to plugin configs from other classes
    public static File wordgroups;//name of file
    public static FileConfiguration wordgroupconfigs;
    @Override
    public void onEnable() {
        //getLogger().info(config.getNumberofgroups());
        plugin = this; //DONT PUT FUCKIN SHIT BEFORE THIS
        //register events in this class
        wordgroups = new File(getDataFolder(), "wordgroups.yml"); // set the file location
        wordgroupconfigs = YamlConfiguration.loadConfiguration(wordgroups); // this will give you all the functions such as .getInt, getString ect..
        getServer().getPluginManager().registerEvents(this, this);
        config.enable();
        //set swear command to the class commands
        getCommand("language").setExecutor(new Commands());
        getLogger().info("Number of Word groups currently enabled: "+config.getNumberofgroups());
        // print to the console that the plugin in enabled
        getLogger().info("[MilspecLang] has been Enabled!");
        //getLogger().info(config.getGroups());
        config.addgroup();
        config.addmessagegroup();
        config.addcommandgroup();
    }
    public static void saveNewConfig(){
        try{
        wordgroupconfigs.save(wordgroups);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // lowest event priority to stop lagg
    @EventHandler(priority = EventPriority.NORMAL) //This is the standard chat checker
    public void chat(final AsyncPlayerChatEvent ev) {
        if (ev.isCancelled()) return;
        if (ev.getPlayer().hasPermission("language.exempt")) return;

        //Same for both lag and no lag
        List<String> swearlist = Wordcatch.isswear(ev.getMessage());
        if (swearlist != null) {
            for (String swear : swearlist) {
                ev.getPlayer().sendMessage(ChatColor.RED+"NO BADWORDS");
                // message contains a swear
                // cancel event
                ev.setCancelled(true);
            }
        }
        List<String> forbiddenlist = Wordcatch.isforbidden(ev.getMessage());
        if (forbiddenlist != null) {
            for (String forbidden : forbiddenlist) {
                //ev.getPlayer().sendMessage(forbidden);
                ev.setMessage(config.chatmessage());
                //sends message in configs
                //ev.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void commands(PlayerCommandPreprocessEvent ev) { //this is the command checker
        if (ev.isCancelled()) return;
        if (ev.getPlayer().hasPermission("language.exempt")) return;
        if (!(config.commandenabled())) return;
        String[] args = ev.getMessage().replace("/", "").split(" ");
        String command = args[0];
        if (command.equalsIgnoreCase("language") || command.equalsIgnoreCase("language")) {
            // dont run any checks on it
            return;
        }
        //Same for both lag and no lagg
        List<String> swearlist = Wordcatch.isswear(ev.getMessage());
        if (swearlist != null) {
            for (String swear : swearlist) {
                ev.getPlayer().sendMessage(ChatColor.RED + "NO BADWORDS");
                // message contains a swear
                // cancel event
                ev.setCancelled(true);
            }
        }
        List<String> forbiddenlist = Wordcatch.isforbidden(ev.getMessage());
        if (ev.getPlayer().hasPermission("language.forbidden")) return;
        if (forbiddenlist != null) {
            for (String forbidden : forbiddenlist) {
                ev.getPlayer().sendMessage(ChatColor.RED+"Forbidden word: " + forbidden);
                //ev.setMessage(config.chatmessage());
                ev.setCancelled(true);
            }
        }
    }
}
