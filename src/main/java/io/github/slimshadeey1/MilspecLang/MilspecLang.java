package io.github.slimshadeey1.MilspecLang;

import org.bukkit.*;

import java.util.*;

import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;
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
    @Override
    public void onEnable() {
        plugin = this; //DONT PUT FUCKIN SHIT BEFORE THIS
        getServer().getPluginManager().registerEvents(this, this);
        config.enable();
        //config.advertiserdb();//Yup i am an idiot.
        WordGroups.seperator();
        getCommand("language").setExecutor(new Commands());
        //getLogger().info("Words on custom: " + WordGroups.words);
        //getLogger().info("Messages on custom: "+WordGroups.messages);
        //getLogger().info("Commands on custom: "+WordGroups.commandexec);
        //getLogger().info("Advertiser Raw: "+config.addresses);
        //getLogger().info("Advertiser Check: "+);
        getLogger().info("[MilspecLang] has been Enabled!");
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

                ev.setMessage(config.chatmessage());
                //ev.getPlayer().sendMessage(config.getWords().toString());
                //ev.getPlayer().sendMessage(config.getCustomset().toString());
            }
        }
        List<String> Advertiser = Wordcatch.isipaddress(ev.getMessage());
        if (Advertiser != null) {
            for (String addr : Advertiser) {
                getLogger().info("Player: "+ev.getPlayer().getName()+" Advertising Server: "+addr);
                getServer().broadcastMessage(ChatColor.RED + "Advertiser detected! Player: " + ChatColor.UNDERLINE + ev.getPlayer().getName());
                String K = ev.getPlayer().getName();
                config.addAd(K,1);//Implemented for future use.
                for (String pun : config.getPunishmentad(ev.getPlayer().getName())) {
                    String playerconf = "<player>";
                    String punish = "";
                    if(!(pun.isEmpty())&&((pun.contains("<player>")))) {
                        punish = pun.replaceAll(playerconf, ev.getPlayer().getName());
                    }
                    if (!(punish.isEmpty()|pun.isEmpty())) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), punish.trim());
                    }

                }
                //config.addresses.add(addr);
                ev.setCancelled(true);
            }
        }
        List<String> forbiddenlist = Wordcatch.isforbidden(ev.getMessage());
        if (forbiddenlist != null) {
            for (String forbidden : forbiddenlist) {
                Integer id = config.getWords().indexOf(forbidden);
                ev.getPlayer().sendMessage(config.getMessage(id));
                String player = ev.getPlayer().getName().replace("]", "");
                String playerconf = "<player>";
                String commandplay = config.getCommand(id).replaceAll(playerconf, player).replace("]", "");
//                List<String> command = new ArrayList<>();
//                List<Integer> ident = new ArrayList<>();
//                String[] rawcom = commandplay.split(";");
//                for (Integer i=0; i<=rawcom.length; i++){
//                    command.add(rawcom[i].split("@")[0]);
//                    ident.add(Integer.parseInt(rawcom[i].split("@")[0]));
//                }
//                if(!(command.get(ident.indexOf(count)).isEmpty())) {
//                    String comand = command.get(ident.indexOf(count));
//                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comand);
//                }else{
//                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.get(1));
//                }
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandplay);
                ev.setMessage(config.chatmessage());
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
        if (forbiddenlist != null) {
            for (String forbidden : forbiddenlist) {
                Integer id = config.getWords().indexOf(forbidden);
                ev.getPlayer().sendMessage(config.getMessage(id));
                String player = ev.getPlayer().getName().replace("]", "");
                String playerconf = "<player>";
                String commandplay = config.getCommand(id).replaceAll(playerconf, player).replace("]", "");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandplay);
                ev.getPlayer().sendMessage(config.getMessage(id));
                ev.setCancelled(true);
            }
        }
    }
}
