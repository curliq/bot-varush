package app.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

import app.rest.pojos.TeamStatsPOJO;

public class BattleriteUtils {

    public final static int SEASON = 11; // current battlerite season to default in every request
    public final static int BATTLERITE_COLOR_PRIMARY = 0xEF7326;
    public final static String STATS_SOLO_IMAGE = "https://i.imgur.com/ohoiNqZ.png";
    public final static String STATS_2V2_IMAGE = "https://i.imgur.com/BEYAZRz.png";
    public final static String STATS_3V3_IMAGE = "https://i.imgur.com/igCOlpX.png";
    public final static String BATTLERITE_RICHPRESENCE_NAME = "Battlerite";
    public final static String BATTLERITE_ROYALE_RICHPRESENCE_NAME = "Battlerite Royale";
    public final static String ALYSIA = "Alysia";
    public final static String ASHKA = "Ashka";
    public final static String BAKKO = "Bakko";
    public final static String BLOSSOM = "Blossom";
    public final static String CROAK = "Croak";
    public final static String DESTINY = "Destiny";
    public final static String EZMO = "Ezmo";
    public final static String FREYA = "Freya";
    public final static String IVA = "Iva";
    public final static String JADE = "Jade";
    public final static String JAMILA = "Jamila";
    public final static String JUMONG = "Jumong";
    public final static String LUCIE = "Lucie";
    public final static String OLDUR = "Oldur";
    public final static String PEARL = "Pearl";
    public final static String PESTILUS = "Pestilus";
    public final static String POLOMA = "Poloma";
    public final static String RAIGON = "Raigon";
    public final static String ROOK = "Rook";
    public final static String RUH_KAAN = "Ruh Kaan";
    public final static String SHIFU = "Shifu";
    public final static String SIRIUS = "Sirius";
    public final static String TAYA = "Taya";
    public final static String THORN = "Thorn";
    public final static String ULRIC = "Ulric";
    public final static String VARESH = "Varesh";
    public final static String ZANDER = "Zander";
    public final static String SHEN_RAO = "Shen Rao";
    public final static String[] CHAMPIONS = {ALYSIA, ASHKA, BAKKO, BLOSSOM, CROAK, DESTINY, EZMO, FREYA, IVA, JADE,
            JAMILA, JUMONG, LUCIE, OLDUR, PEARL, PESTILUS, POLOMA, RAIGON, ROOK, RUH_KAAN, SHIFU, SIRIUS, TAYA, THORN,
            ULRIC, VARESH, ZANDER, SHEN_RAO};


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

    /**
     * Order teams by division then league then points
     *
     * @param teamsArray array of teams to be ordered
     */
    public static void orderTeams(ArrayList<TeamStatsPOJO.Data> teamsArray) {

        Comparator<TeamStatsPOJO.Data> byLeague = (o1, o2) -> {
            if (o1.getAttributes().getStats().getLeague().equals(o2.getAttributes().getStats().getLeague()))
                return 0;
            return o2.getAttributes().getStats().getLeague() - o1.getAttributes().getStats().getLeague();
        };

        Comparator<TeamStatsPOJO.Data> byDivision = (o1, o2) -> {
            if (o1.getAttributes().getStats().getDivision().equals(o2.getAttributes().getStats().getDivision()))
                return 0;
            return o1.getAttributes().getStats().getDivision() - o2.getAttributes().getStats().getDivision();
        };

        Comparator<TeamStatsPOJO.Data> byPoints = (o1, o2) -> {
            if (o1.getAttributes().getStats().getDivisionRating().equals(o2.getAttributes().getStats()
                    .getDivisionRating()))
                return 0;
            return o2.getAttributes().getStats().getDivisionRating()
                    - o1.getAttributes().getStats().getDivisionRating();
        };

        teamsArray.sort(byLeague.thenComparing(byDivision).thenComparing(byPoints));

    }

}
