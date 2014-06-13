package io.github.slimshadeey1.MilspecLang;

/**
 * Created by Ben Byers on 6/9/2014.
 */

    import org.bukkit.ChatColor;
    import org.bukkit.command.Command;
    import org.bukkit.command.CommandExecutor;
    import org.bukkit.command.CommandSender;


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
                        sender.sendMessage(ChatColor.YELLOW+"/language setad [playername] [number-of-attempts] -"+ChatColor.WHITE+"Sets a new count of attempts for a player");
                        sender.sendMessage(ChatColor.YELLOW+"/language removead [playername] -"+ChatColor.WHITE+"removes a playe completely from ad database");
                        sender.sendMessage(ChatColor.YELLOW+"/language addad [playername] [number-of-attempts]-"+ChatColor.WHITE+"adds an attempt to a players attempt count");
                        sender.sendMessage(ChatColor.YELLOW+"/language listad -"+ChatColor.WHITE+"Lists all players and attempts made at spamming");

                    }
                    sender.sendMessage(line);
                    return true;
                }

                if(args.length >= 1){
                    //Custom set start
                    //-------------------------------------------------------------------------------------------------------------------------
                    if(args[0].equalsIgnoreCase("setad")) {
                        if (args.length < 2 | args.length > 2) {
                            if (sender.hasPermission("language.admin")) {
                                //sender.sendMessage(linetag);
                                sender.sendMessage(ChatColor.RED + "Changing " + args[1] + " on advertisers list.");
                                config.setad(args[1], Integer.parseInt(args[2]));
                                return true;

                            } else {
                                sender.sendMessage(ChatColor.RED + "You don't have permission");
                                return true;
                            }
                        } else {
                            sender.sendMessage(ChatColor.YELLOW + "/language setad [playername] [number-of-attempts] -" + ChatColor.WHITE + "Sets a new count of attempts for a player");
                            return true;
                        }
                    }
                    if(args[0].equalsIgnoreCase("removead")){
                        if(args.length>1|args.length<1) {
                            if (sender.hasPermission("language.admin")) {
                                //sender.sendMessage(linetag);
                                sender.sendMessage(ChatColor.RED + "Removing " + args[1] + " from advertisers list.");
                                config.removead(args[1]);
                                return true;

                            } else {
                                sender.sendMessage(ChatColor.RED + "You don't have permission");
                                return true;
                            }
                        }else{
                            sender.sendMessage(ChatColor.YELLOW+"/language removead [playername] -"+ChatColor.WHITE+"removes a playe completely from ad database");

                        }
                    }
                    if(args[0].equalsIgnoreCase("addad")){
                        if(args.length< 2 | args.length > 2) {
                            if (sender.hasPermission("language.admin")) {
                                //sender.sendMessage(linetag);
                                sender.sendMessage(ChatColor.RED + "Adding Strike to " + args[1] + " on advertisers list.");
                                config.addAd(args[1], Integer.parseInt(args[2]));
                                return true;

                            } else {
                                sender.sendMessage(ChatColor.RED + "You don't have permission");
                                return true;
                            }
                        }else{
                            sender.sendMessage(ChatColor.YELLOW+"/language addad [playername] [number-of-attempts]-"+ChatColor.WHITE+"adds an attempt to a players attempt count");
                        }
                    }
                    if(args[0].equalsIgnoreCase("listad")){
                        if(args.length == 1) {
                            if (sender.hasPermission("language.admin")) {
                                //sender.sendMessage(linetag);
                                sender.sendMessage(ChatColor.RED + "Advertisers List");
                                for (String name : config.addresses) {
                                    Integer number = config.advertiserdbin.get(name.split("-")[0].trim());
                                    sender.sendMessage(ChatColor.RED + "Name: " + ChatColor.YELLOW + name.split("-")[0].trim() + ChatColor.RED + " Attempts: " + ChatColor.YELLOW + number);
                                }
                                return true;

                            } else {
                                sender.sendMessage(ChatColor.RED + "You don't have permission");
                                return true;
                            }
                        }else{
                            sender.sendMessage(ChatColor.YELLOW+"/language listad -"+ChatColor.WHITE+"Lists all players and attempts made at spamming");
                        }
                    }
                    //-------------------------------------------------------------------------------------------------------------------------
                    if(args[0].equalsIgnoreCase("addword")) {
                        if (args.length<3){
                        if (sender.hasPermission("language.admin")) {
                            //sender.sendMessage(linetag);
                            String newwords = "";
                            for (Integer i = 0; i < args.length; i++) {
                                String arg = args[i] + " ";
                                newwords = newwords + arg;
                            }
                            String commandnew = newwords.split("/")[1];//works perfectly
                            String messagenew = newwords.split("/")[0].split("message:")[1];
                            String newword = newwords.split("/")[0].split("message:")[0].replaceAll("addcustomword", "");
                            sender.sendMessage("New Word: " + newword + " New Message: " + messagenew + " New Command: " + commandnew);
                            config.addcustomset(newword, messagenew, commandnew);
                            return true;

                        } else {
                            sender.sendMessage(ChatColor.RED + "You don't have permission");
                            return true;
                        }
                    }else {
                        sender.sendMessage(ChatColor.YELLOW + "/language addword [word] message:[message] [Command]-" + ChatColor.WHITE + "Adds word to custom configuration with messages and commands");
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
                        }else{
                            sender.sendMessage(ChatColor.YELLOW+"/language getlist -"+ChatColor.WHITE+"Lists all custom configs and displays ID's");
                        }
                    }
                    //--------------------------------------------------------------------------------------------------
                    if(args[0].equalsIgnoreCase("removeword")) {
                        if(args.length == 1){
                        if (sender.hasPermission("language.admin")) {
                            sender.sendMessage(linetag);
                            config.removecustomset(Integer.parseInt(args[1]));
                            sender.sendMessage(line);
                            return true;
                        } else {
                            sender.sendMessage(ChatColor.RED + "You don't have permission");
                            return true;
                        }
                    }else {
                        sender.sendMessage(ChatColor.YELLOW + "/language removeword [id] -" + ChatColor.WHITE + "removes word from custom configuration");

                    }
                    }
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
