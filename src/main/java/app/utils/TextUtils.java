package app.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import app.rest.HttpRequests;
import app.rest.pojos.PlayerPOJO;
import retrofit2.Response;

public class TextUtils {

    /**
     * Our array of illegal characters
     * We want to avoid these, because they will print crazy stuff on discord
     */
    public static String[] illegalStrings() {
        return new String[] {"●", "̮", "̃", " ", "̯", "͡", "丨", "啊", "浅", "风", "境", "过", "⚜", "☭", "٩", "۶"};
    }

    /**
     * Get a pipe symbol (⎪) from its hex code
     */
    public static String pipeSymbol() {
        return String.valueOf(Character.toChars(Integer.parseInt("23AA", 16)));
    }

    /**
     * Build a String made of whitespaces
     *
     * @param amount the amount of whitespaces the string will have
     */
    public static String whiteSpaces(int amount) {
        return characterRepeat(' ', amount);
    }

    /**
     * Build a String made of given char
     *
     * @param character the char
     * @param amount    how many chars
     */
    public static String characterRepeat(char character, int amount) {
        StringBuilder whiteSpace = new StringBuilder();
        for (int i = 0; i < amount; i++)
            whiteSpace.append(character);
        return whiteSpace.toString();
    }

    /** Urlencode a string */
    public static String urlEncode(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return string;
        }
    }

    /**
     * The following method converts all the letters into upper/lower case,
     * depending on their position near a space or other special chars.
     */
    public static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add
                // other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    /**
     * Divide a string into multiple strings between each n commas.
     * Example:
     * "123, 456, 678, 890, 123, 645" becomes ["123, 456, 890", "123, 645"]
     *
     * @param string   list of values, ex: "123, 412, 144, 5512, 6234"
     * @param howOften between how many commas do we want to split. For battlerite player ID requests use 3
     */
    public static ArrayList<String> splitStringInCommas(String string, int howOften) {
        ArrayList<String> strings = new ArrayList<>();
        int nth = 0;
        int cont = 0;
        int i = 0;
        for (; i < string.length(); i++) {
            if (string.charAt(i) == ',')
                nth++;
            if (nth == 3 || i == string.length() - 1) {
                if (i == string.length() - 1) { //with this if you prevent to cut the last number
                    String subString = string.substring(cont, i + 1);
                    if (subString.charAt(subString.length() - 1) == ',')
                        subString = subString.substring(0, subString.length() - 1);
                    strings.add(subString);
                    Response<PlayerPOJO> otherPlayersResponse = HttpRequests.getPlayersByIds(subString);
                } else {
                    String subString = string.substring(cont, i);
                    if (subString.charAt(subString.length() - 1) == ',')
                        subString = subString.substring(0, subString.length() - 1);
                    strings.add(subString);
                }
                nth = 0;
                cont = i + 1;
            }
        }
        return strings;
    }

}
