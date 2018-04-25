package app.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;

import net.dv8tion.jda.core.EmbedBuilder;

public class GenericUtils {

    public final static String BOT_VERSION = "3.2.0";
    public final static String COMMAND_TRIGGER = "!br";
    public final static long OWNER_DISCORD_USER_ID = 233347968378339328L;
    public final static String ERROR_TITLE = "Sorry...";
    public final static String ERROR_MESSAGE = "Oops, something wrong is not right";
    public final static String STREAMING_ROLE_NAME = "Streaming";
    public final static String PROBATION_ROLE_NAME = "Under Probation";

    /**
     * Shortcut to log something on the console
     */
    public static void log(Object o) {
        System.out.println(o);
        System.out.println(" ");
    }    

    /**
     * Build a basic embed message with just one field
     */
    public static EmbedBuilder getBasicEmbedMessage(String title, String message) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.addField(title, message, false);
        return eb;
    }

    /**
     * Round decimal to 2 cases
     */
    public static Double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    /**
     * Urlencode a string
     */
    public static String urlEncode(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return string;
        }
    }
    
}