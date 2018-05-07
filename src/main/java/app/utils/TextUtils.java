package app.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TextUtils {

    /**
     * Our array of illegal characters
     * We want to avoid these, because they will print crazy stuff on discord
     */
    public static String[] illegalStrings() {
        return new String[] {"●", "̮", "̃", " ", "̯", "͡", "丨", "啊", "浅", "风", "境", "过"};
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

}
