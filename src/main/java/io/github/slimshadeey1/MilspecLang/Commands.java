package io.github.slimshadeey1.MilspecLang;

/**
 * Created by Ben Byers on 6/9/2014.
 */

    import org.bukkit.ChatColor;
    import org.bukkit.command.Command;
    import org.bukkit.command.CommandExecutor;
    import org.bukkit.command.CommandSender;

    import java.util.*;

public class Commands  implements CommandExecutor {

        public static String tag = ChatColor.BLUE +"Language filter";
        public static String linetag = ChatColor.BLUE+"Language Filter";
        public static String line = ChatColor.GOLD+"==============================================";

        public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
            if (command.getName().equalsIgnoreCase("language")) {
                if(args.length == 0){
                    sender.sendMessage(linetag);
                    if(sender.hasPermission("language.admin")){
                        sender.sendMessage(ChatColor.YELLOW+"/language add [word] - "+ChatColor.WHITE+"adds a word to the swear list.");
                        sender.sendMessage(ChatColor.YELLOW+"/language remove [word] - "+ChatColor.WHITE+" removes a word from the swear list.");
                        sender.sendMessage(ChatColor.YELLOW+"/language exempt [word] - "+ChatColor.WHITE+"adds a word to the exemption list.");
                        sender.sendMessage(ChatColor.YELLOW+"/language rexempt [word] - "+ChatColor.WHITE+"removes a word from the exemption list.");
                        sender.sendMessage(ChatColor.YELLOW+"/language list - "+ChatColor.WHITE+"shows the swear list.");
                        sender.sendMessage(ChatColor.YELLOW+"/language elist - "+ChatColor.WHITE+"shows the exception list.");
                        sender.sendMessage(ChatColor.YELLOW+"/language addword [word] message:[message] [Command]-"+ChatColor.WHITE+"Adds word to custom configuration with messages and commands");
                        sender.sendMessage(ChatColor.YELLOW+"/language getlist -"+ChatColor.WHITE+"Lists all custom configs and displays ID's");
                        sender.sendMessage(ChatColor.YELLOW+"/language removeword [id] -"+ChatColor.WHITE+"removes word from custom configuration");

                    }
                    sender.sendMessage(line);
                    return true;
                }

                if(args.length >= 1){
                    //Custom set start
                    if(args[0].equalsIgnoreCase("addword")){
                        if(sender.hasPermission("language.admin")) {
                            //sender.sendMessage(linetag);
                            String newwords = "";
                            for (Integer i = 0; i < args.length; i++) {
                                String arg = args[i]+" ";
                                newwords = newwords+arg;
                            }
                            String commandnew = newwords.split("/")[1];//works perfectly
                            String messagenew = newwords.split("/")[0].split("message:")[1];
                            String newword = newwords.split("/")[0].split("message:")[0].replaceAll("addcustomword", "");
                            sender.sendMessage("New Word: "+newword+" New Message: "+messagenew+" New Command: "+commandnew);
                            config.addcustomset(newword, messagenew, commandnew);
                                return true;

                            }else{
                                sender.sendMessage(ChatColor.RED+"You don't have permission");
                                return true;
                            }
                        }
                    //------------------------------------------------------------------------------------------------------
                    if(args[0].equalsIgnoreCase("getlist")){
                        if(args.length == 1){
                            if(sender.hasPermission("language.admin")){
                                sender.sendMessage(linetag);
                                config.getCustomset();
                                for (String s : config.getCustomset()){
                                    sender.sendMessage("-"+config.getCustomset().indexOf(s)+" "+s);
                                }
                                sender.sendMessage(line);
                                return true;
                            }else{
                                sender.sendMessage(ChatColor.RED+"You don't have permission");
                                return true;
                            }
                        }
                    }
                    //--------------------------------------------------------------------------------------------------
                    if(args[0].equalsIgnoreCase("removeword")){
                            if(sender.hasPermission("language.admin")){
                                sender.sendMessage(linetag);
                                config.removecustomset(Integer.parseInt(args[1]));
                                sender.sendMessage(line);
                                return true;
                            }else{
                                sender.sendMessage(ChatColor.RED+"You don't have permission");
                                return true;
                            }
                    }
                    //--------------------------------------------------------------------------------------------------
//                    if(args[0].equalsIgnoreCase("editcustommessage")){
//                            if(sender.hasPermission("language.admin")){
//                                sender.sendMessage(linetag);
//                                String newwords = "";
//                                for (Integer i = 0; i < args.length; i++) {
//                                    String arg = args[i]+" ";
//                                    newwords = newwords+arg;
//                                }
//                                Integer id = Integer.parseInt(newwords.replaceAll("editcustommessage", "").split(" ")[0]);//works perfectly
//                                String word = newwords.replaceAll("editcustommessage", "").split(" ")[1];
//                                config.editcustommessage(id, word);
//                                return true;
//                            }else{
//                                sender.sendMessage(ChatColor.RED+"You don't have permission");
//                                return true;
//                            }
//                    }
//                    if(args[0].equalsIgnoreCase("editcustomcommand")){
//                            if(sender.hasPermission("language.admin")){
//                                String newwords = "";
//                                for (Integer i = 0; i < args.length; i++) {
//                                    String arg = args[i]+" ";
//                                    newwords = newwords+arg;
//                                }
//                                Integer id = Integer.parseInt(newwords.replaceAll("editcustomcommand", "").split(" ")[0]);//works perfectly
//                                String word = newwords.replaceAll("editcustomcommand", "").split(" ")[1];
//                                config.editcustomcommand(id, word);
//                                return true;
//                            }else{
//                                sender.sendMessage(ChatColor.RED+"You don't have permission");
//                                return true;
//                            }
//                    }
                    //=================================================================================================
                    if(args[0].equalsIgnoreCase("list")){
                        if(args.length == 1){
                            if(sender.hasPermission("language.admin")){
                                sender.sendMessage(linetag);
                                for (String s : config.getswearlist()){
                                    sender.sendMessage("-"+s);
                                }
                                sender.sendMessage(line);
                                return true;
                            }else{
                                sender.sendMessage(ChatColor.RED+"You don't have permission");
                                return true;
                            }
                        }else{
                            sender.sendMessage("/language list");
                            return true;
                        }
                    }

                    if(args[0].equalsIgnoreCase("elist")){
                        if(args.length == 1){
                            if(sender.hasPermission("language.admin")){
                                sender.sendMessage(linetag);
                                for (String s : config.getexceptionlist()){
                                    sender.sendMessage("-"+s);
                                }
                                sender.sendMessage(line);
                                return true;
                            }else{
                                sender.sendMessage(ChatColor.RED+"You don't have permission");
                                return true;
                            }
                        }else{
                            sender.sendMessage("/language elist");
                            return true;
                        }
                    }

                    if(args[0].equalsIgnoreCase("add")){
                        if(args.length > 1){
                            if(sender.hasPermission("language.admin")){
                                String word = Wordcatch.getFinalArg(args, 1).toLowerCase();
                                if(config.addswear(word) == false){
                                    sender.sendMessage(word+" is already in the swear list");
                                    return true;
                                }
                                sender.sendMessage("You added '"+word+"' to the swear list.");
                                return true;
                            }else{
                                sender.sendMessage(ChatColor.RED+"You don't have permission");
                                return true;
                            }
                        }else{
                            sender.sendMessage("/language add [String]");
                            return true;
                        }
                    }

                    if(args[0].equalsIgnoreCase("exempt")){
                        if(args.length > 1){
                            if(sender.hasPermission("language.admin")){
                                String word = Wordcatch.getFinalArg(args, 1).toLowerCase();
                                if(config.addexempt(word) == false){
                                    sender.sendMessage(word+" is already in exception list");
                                    return true;
                                }
                                sender.sendMessage("You added '"+word+"' to the exception list.");
                                return true;
                            }else{
                                sender.sendMessage(ChatColor.RED+"You don't have permission");
                                return true;
                            }
                        }else{
                            sender.sendMessage("/language except [String]");
                            return true;
                        }
                    }


                    if(args[0].equalsIgnoreCase("remove")){
                        if(args.length > 1){
                            if(sender.hasPermission("language.admin")){
                                String word = Wordcatch.getFinalArg(args, 1).toLowerCase();
                                if(config.removeswear(word) == false){
                                    sender.sendMessage(word+"' is not on the swear list.");
                                    return true;
                                }
                                sender.sendMessage("You removed '"+word+"' from the swear list.");
                                return true;
                            }else{
                                sender.sendMessage(ChatColor.RED+"You don't have permission");
                                return true;
                            }
                        }else{
                            sender.sendMessage("/language remove [String]");
                            return true;
                        }
                    }


                    if(args[0].equalsIgnoreCase("rexempt")){
                        if(args.length > 1){
                            if(sender.hasPermission("language.admin")){
                                String word = Wordcatch.getFinalArg(args, 1).toLowerCase();
                                if(config.removeexempt(word) == true){
                                    sender.sendMessage("You removed '"+word+"' from the exception list.");
                                    return true;
                                }

                                sender.sendMessage(word+"' is not on the exception list.");
                                return true;
                            }else{
                                sender.sendMessage(ChatColor.RED+"You don't have permission");
                                return true;
                            }
                        }else{
                            sender.sendMessage("/language exceptremove [String]");
                            return true;
                        }
                    }

                }
            }
            return false;
        }

    }
