package app.utils.mapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

import app.rest.pojos.PlayerPOJO;
import app.utils.GenericUtils;

public class MappingUtils {

    /**
     * Get a static JsonArray object from the playerStats mapping file
     */
    public static JsonArray getPlayerStatsJson() {
        String path = "/playerStats.json";

        InputStream is = GenericUtils.class.getResourceAsStream(path);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(isr);

        Gson gson = new Gson();
        return gson.fromJson(bufferedReader, JsonArray.class);
    }

    /**
     * Smart mapping from the /playerStats.json file.
     * Get the right stat object from the player stats through reflection, by sending the "stackableRangeName" and the
     * "englishLocalizedName".
     *
     * @param stackableRangeName   the "stackableRangeName" of the object, e.g.: "CharacterWins"
     * @param englishLocalizedName the "englishLocalizedName" of the object, e.g.: "Freya"
     * @param playerStats          the stats instance of the player we want to get the data from
     */
    public static Integer getPlayerStatsValue(String stackableRangeName,
                                    String englishLocalizedName,
                                    PlayerPOJO.Stats playerStats) {

        String databaseField = getDatabaseField(stackableRangeName, englishLocalizedName);

        try {
            // get the field through reflection
            Field field = PlayerPOJO.Stats.class.getDeclaredField(databaseField);
            field.setAccessible(true);
            // get the field's value from our player.stats instance
            return Integer.valueOf(String.valueOf(field.get(playerStats)));
        } catch (NoSuchFieldException | IllegalAccessException | NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Get database field name from "stackableRangeName" and "englishLocalizedName"
     *
     * @param stackableRangeName   the "stackableRangeName" of the object, e.g.: "CharacterWins"
     * @param englishLocalizedName the "englishLocalizedName" of the object, e.g.: "Freya"
     */
    public static String getDatabaseField(String stackableRangeName, String englishLocalizedName) {
        // loop through every object in our mapping json file
        for (JsonElement stat : MappingUtils.getPlayerStatsJson()) {
            JsonObject statJson = stat.getAsJsonObject();

            if (statJson.get("StackableRangeName").getAsString().equals(stackableRangeName))

                // every object is duplicated with the DevName as eg: "AshkaCampaign", so we filter those out
                if (!statJson.get("DevName").getAsString().contains("Campaign"))

                    if (statJson.get("EnglishLocalizedName").getAsString().equals(englishLocalizedName))

                        // prefix "c" to the field to match the name of the field in our object, because they all
                        // start with "c"
                        return "c" + statJson.get("StackableId").getAsString();
        }
        return "";
    }
}
