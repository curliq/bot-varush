package app.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class BattleriteUtils {

    public final static int SEASON = 7; // current battlerite season to default in every request
    public final static int BATTLERITE_COLOR_PRIMARY = 0xEF7326;
    public final static String STATS_SOLO_IMAGE = "https://i.imgur.com/ohoiNqZ.png";
    public final static String STATS_2V2_IMAGE = "https://i.imgur.com/BEYAZRz.png";
    public final static String STATS_3V3_IMAGE = "https://i.imgur.com/igCOlpX.png";
    public final static String TWITCH_BATTLERITE_ID = "493277"; // twitch maps games by ID, and battlerite is this

    /**
     * Get points amount from Bronze 5 to Grand Champion 1.
     * Bronze 5 is 0 points. Gold 5 is division 1000, etc
     */
    public static int getGlobalPoints(int league, int division) {
        switch (league) {
        // bronze
        case 0:
            switch (division) {
            case 5:
                return 0;
            case 4:
                return 100;
            case 3:
                return 200;
            case 2:
                return 300;
            case 1:
                return 400;
            }
            // silver
        case 1:
            switch (division) {
            case 5:
                return 500;
            case 4:
                return 600;
            case 3:
                return 700;
            case 2:
                return 800;
            case 1:
                return 900;
            }
            // gold
        case 2:
            switch (division) {
            case 5:
                return 1000;
            case 4:
                return 1100;
            case 3:
                return 1200;
            case 2:
                return 1300;
            case 1:
                return 1400;
            }
            // platinum
        case 3:
            switch (division) {
            case 5:
                return 1500;
            case 4:
                return 1600;
            case 3:
                return 1700;
            case 2:
                return 1800;
            case 1:
                return 1900;
            }
            // diamond
        case 4:
            switch (division) {
            case 5:
                return 2000;
            case 4:
                return 2100;
            case 3:
                return 2200;
            case 2:
                return 2300;
            case 1:
                return 2400;
            }
            // champion
        case 5:
            switch (division) {
            case 5:
                return 2500;
            case 4:
                return 2600;
            case 3:
                return 2700;
            case 2:
                return 2800;
            case 1:
                return 2900;
            }
            // grand champion
        case 6:
            return 3000;
        }
        return 0;
    }

    public static String getBrStatsPlayerUrl(String playerID) {
        return "https://battlerite-stats.com/profile/" + playerID;
    }

    /**
    * Get a player's title
    */
    public static String getPlayerTitle(String titleID) {

        try {
            String path = "/playerTitles.json";

            InputStream is = GenericUtils.class.getResourceAsStream(path);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(isr);

            Gson gson = new Gson();
            JsonArray json = gson.fromJson(bufferedReader, JsonArray.class);

            for (JsonElement titleObject : json) {
                if (titleObject.getAsJsonObject().get("stackableId").getAsString().equals(titleID))
                    return titleObject.getAsJsonObject().get("text").getAsString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Boosted Silver";
        }
        return "Boosted Silver";
    }

    /**
     * Get a player's league name
     */
    public static String getLeague(int league) {
        switch (league) {
        case 0:
            return "Bronze";
        case 1:
            return "Silver";
        case 2:
            return "Gold";
        case 3:
            return "Platinum";
        case 4:
            return "Diamond";
        case 5:
            return "Champion";
        case 6:
            return "Grand Champion";
        default:
            return "No league yet";
        }
    }
}