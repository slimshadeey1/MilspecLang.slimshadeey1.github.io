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
                    }
                    sender.sendMessage(line);
                    return true;
                }
                if(args.length >= 1){
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
