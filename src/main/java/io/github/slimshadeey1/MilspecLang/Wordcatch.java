package io.github.slimshadeey1.MilspecLang;

import org.apache.commons.validator.routines.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Ben Byers on 6/8/2014.
 * This plays the roll of catching swearwords from config files.
 * This contains arrays from swear jar plugin that seemed to be well done.
 */
public class Wordcatch {
    public static String getFinalArg(final String[] args, final int start){
        final StringBuilder bldr = new StringBuilder();
        for (int i = start; i < args.length; i++)
        {
            if (i != start)
            {
                bldr.append(" ");
            }
            bldr.append(args[i]);
        }
        return bldr.toString();
    }

    public static List<String> aggresivemode(String swear){
        String message = swear.toLowerCase();
        List<String> finalwords = new ArrayList<String>();
        // replace common replacements with normal characters [Original Message] = "A*ssh()le"
        String mess = message.replace("*", ""); //mess = message with all * removed i.e "Assh(()le"
        String mes3 = mess.replace("()", "o"); //mes3 = mess and also changes () to o i.e. "Assh(ole"
        String mes2 = mes3.replace("(", ""); //mes2 = mes3 with all ( removed i.e. "Asshole"
        String mes = mes2.replace(")", "");
        String me1 = mes.replace("/", "");
        String me2 = me1.replace(".", "");
        String me3 = me2.replace(",", "");
        String me4 = me3.replace("4", "a");
        String me5 = me4.replace(";", "");
        String me6 = me5.replace("'", "");
        String me7 = me6.replace("#", "");
        String me8 = me7.replace("~", "");
        String me9 = me8.replace("^", "");
        String me10 = me9.replace("-", "");
        String me11 = me10.replace("+", "");
        String me12 = me11.replace("1", "i");
        String me13 = me12.replace("0", "o");
        String me14 = me13.replace("$", "s");
        String messageo = me14.replace("@", "o"); //the @ sign has two uses so you check against both
        String messagea = me14.replace("@", "a"); //See above comment
        //remove spaces
        String removespaceso = messageo.replaceAll(" ", ""); //remove spaces for word version with letter o
        String removespacesa = messagea.replaceAll(" ", ""); //remove spaces for word version with letter a
        //remove duplicate words in the string
        String Finalswearchecko = removeDups(removespaceso);//remove dupes for word version with letter o
        String Finalswearchecka = removeDups(removespacesa);//remove dupes for word version with letter a
        finalwords.add(Finalswearchecko); //Value version o
        finalwords.add(Finalswearchecka); //Value version a
        finalwords.add(message); //Value version normal
        return finalwords;
    }

    public static String removeDups(String s){ //s is the argument value past thru the system
        if ( s.length() <= 1 ) return s;
        if( s.substring(1,2).equals(s.substring(0,1)) ) return removeDups(s.substring(1));
        else return s.substring(0,1) + removeDups(s.substring(1));
    }
    public static List<String> checkforbidden(List<String> finalwords){
        List<String> forbiddenlist = new ArrayList<String>();
        for(String exception: config.getexceptionlist()){ //checking for forbiden
            for(String swear :finalwords){
                if(swear.contains(exception)){
                    // return true, as word is in the forbidden list
                    return null;
                }
            }
        }
        for(String forbidden: config.getWords()){ //checking for swears
            for(String swear :finalwords){
                if(swear.toLowerCase().contains(forbidden)){
                    // return true, as word is in the swear list
                    if(!(forbiddenlist.contains(forbidden))){
                        forbiddenlist.add(forbidden);//returns what swear word was used
                    }
                }
            }
        }
        if(forbiddenlist.isEmpty())return null;
        return forbiddenlist;
    }
    public static List<String> checkaddress(String s){
        List<String> addresslist = new ArrayList<String>();
        for (String address: s.split(" ")){
            if (InetAddressValidator.getInstance().isValid(address)){
                addresslist.add(address);
            }
            if (DomainValidator.getInstance().isValid(address)){
                addresslist.add(address);
            }
        }
        return addresslist;
    }
    public static List<String> checkswear(List<String> finalwords){
        List<String> swearlist = new ArrayList<String>();
        for(String exception: config.getexceptionlist()){ //checking for exceptions
            for(String swear :finalwords){ //final words are what were converted into detectable words. asshole will return ass
                if(swear.contains(exception)){
                    // return false, as word is in the exception list
                    return null;
                }
            }
        }
        for(String swears: config.getswearlist()){ //checking for swears
            for(String swear :finalwords){
                if(swear.contains(swears)){
                    // return true, as word is in the swear list
                    if(!(swearlist.contains(swears))){
                        swearlist.add(swears);//returns what swear word was used
                    }
                }
            }
        }

        if(swearlist.isEmpty())return null;
        return swearlist;
    }

    public static List<String> isswear(String s){
        return checkswear(aggresivemode(s));
    }
    public static List<String> isforbidden(String s) {
        return checkforbidden(aggresivemode(s));
    }
    public static List<String> isipaddress(String s) { return checkaddress(s);}
}
