package app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import app.rest.google.GoogleInterface;
import app.rest.google.pojos.UrlShortenerPOJO;
import net.dv8tion.jda.core.EmbedBuilder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Helper {

    public final static String BOT_VERSION = "2.4.0";
    public final static String COMMAND_TRIGGER = "!br";
    public final static long OWNER_DISCORD_USER_ID = 233347968378339328L;
    public final static String BATTLERITE_BASE_URL = "https://api.dc01.gamelockerapp.com/shards/global/";
    // public final static String BATTLERITE_BASE_URL = "http://demo2022116.mockable.io/";
    public final static String TWITCH_BASE_URL = "https://api.twitch.tv/";
    public final static String GOOGLE_API_BASE_URL = "https://www.googleapis.com/";
    public final static String ERROR_TITLE = "Sorry...";
    public final static String ERROR_MESSAGE = "Oops, something wrong is not right";
    public final static String NOT_A_COMMAND = "That's not really a command lady";
    public final static int SEASON = 7; // current battlerite season to default in every request
    public final static int BATTLERITE_COLOR_PRIMARY = 0xEF7326;
    public final static String STATS_SOLO_IMAGE = "https://i.imgur.com/ohoiNqZ.png";
    public final static String STATS_2V2_IMAGE = "https://i.imgur.com/BEYAZRz.png";
    public final static String STATS_3V3_IMAGE = "https://i.imgur.com/igCOlpX.png";
    public final static String TWITCH_BATTLERITE_ID = "493277"; // twitch maps games by ID, and battlerite is this
    public final static String STREAMING_ROLE_NAME = "Streaming";
    public final static String PROBATION_ROLE_NAME = "Under Probation";

    public static LinkedHashMap<Long, TeamCachedPOJO> teamsPointsCacheMap;

    public static void init() {
        final int MAX_ENTRIES = 100;
        teamsPointsCacheMap = new LinkedHashMap<Long, TeamCachedPOJO>(MAX_ENTRIES, .75F, true) {
            // This method is called just after a new entry has been added
            public boolean removeEldestEntry(Map.Entry<Long, TeamCachedPOJO> eldest) {
                return size() > MAX_ENTRIES - 1;
            }
        };
    }

    /**
     * Shortcut to log something on the console
     */
    public static void log(Object o) {
        System.out.println(o);
    }

    /**
     * Get retrofit object to interact with Battlerite API
     */
    public Retrofit getBattleriteRetrofit() {
        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("Authorization", Auth.BATTLERITE_TOKEN);
        headersMap.put("Accept", "application/vnd.api+json");
        return getRetrofit(BATTLERITE_BASE_URL, headersMap);
    }

    /**
     * Get retrofit object to interact with Twitch API
     */
    public Retrofit getTwitchRetrofit() {
        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("Client-ID", Auth.TWITCH_TOKEN);
        return getRetrofit(TWITCH_BASE_URL, headersMap);
    }

    /**
     * Get generic retrofit object
     */
    public Retrofit getRetrofit(String baseUrl, HashMap<String, String> headersMap) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(loggingInterceptor);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Builder requestBuilder = original.newBuilder();
                if (headersMap != null)
                    for (String header : headersMap.keySet())
                        requestBuilder.addHeader(header, headersMap.get(header));

                requestBuilder.method(original.method(), original.body());
                // 
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .client(client).build();

        return retrofit;
    }

    /**
     * Get a player's league name
     */
    public String getLeague(int league) {
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
     * Build a basic embed message with just one field
     */
    public EmbedBuilder getBasicEmbedMessage(String title, String message) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.addField(title, message, false);
        return eb;
    }

    /**
     * Get a player's title
     */
    public String getPlayerTitle(long titleID) {

        try {
            String path = "/playerTitles.json";

            InputStream is = getClass().getResourceAsStream(path);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(isr);

            Gson gson = new Gson();
            JsonArray json = gson.fromJson(bufferedReader, JsonArray.class);

            for (JsonElement titleObject : json) {
                if (titleObject.getAsJsonObject().get("stackableId").getAsLong() == titleID)
                    return titleObject.getAsJsonObject().get("text").getAsString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Boosted Silver";
        }
        return "Boosted Silver";
    }

    /**
     * Round decimal to 2 cases
     */
    public Double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    /**
     * Urlencode a string
     */
    public String urlEncode(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return string;
        }
    }

    /**
     * Save a team's current points to cache, key being the player's ID, and value the points amount
     */
    public static void saveTeamPoints(long teamID, TeamCachedPOJO teamCachedPOJO) {
        teamsPointsCacheMap.put(teamID, teamCachedPOJO);
    }

    /**
     * Get the saved points amount from a team, may be null
     */
    @Nullable
    public static TeamCachedPOJO getTeamPoints(long teamID) {
        TeamCachedPOJO teamCachedPOJO = teamsPointsCacheMap.get(teamID);
        return teamCachedPOJO;
    }

    /**
     * Get points amount from Bronze 5 to Grand Champion 1.
     * Bronze 5 is 0 points. Gold 5 is division 1000, etc
     */
    public int getGlobalPoints(int league, int division) {
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

    public String getBrStatsPlayerUrl(long playerID) {
        return "https://battlerite-stats.com/profile/" + playerID;
    }

    /**
     * Shorten url with goo.gl
     */
    public String shortenUrl(String longUrl) {
        try {
            HashMap<String, String> bodyMap = new HashMap<>();
            // bodyMap.put("key", Auth.GOOGLE_URL_SHORTENER_KEY);
            bodyMap.put("longUrl", longUrl);
            Response<UrlShortenerPOJO> response = getRetrofit(GOOGLE_API_BASE_URL, null).create(GoogleInterface.class)
                    .getShortenedUrl(Auth.GOOGLE_URL_SHORTENER_KEY, bodyMap).execute();
            return response.body().getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return longUrl;
    }
}