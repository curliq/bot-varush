package app.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.io.File;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import net.dv8tion.jda.core.EmbedBuilder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Helper {

    public final static String BOT_VERSION = "1.2.1";
    
    public final static String COMMAND_TRIGGER = "!br";
    public final static String BATTLERITE_BASE_URL = "https://api.dc01.gamelockerapp.com/shards/global/";
    public final static String ERROR_TITLE = "Sorry...";
    public final static String ERROR_MESSAGE = "Oops, something wrong is not right";
    public final static String NOT_A_COMMAND = "That's not really a command lady";
    public final static int SEASON = 6;
    public final static int BATTLERITE_COLOR_PRIMARY = 0xEF7326;
    public final static String STATS_SOLO_IMAGE = "https://i.imgur.com/ohoiNqZ.png";
    public final static String STATS_2V2_IMAGE = "https://i.imgur.com/BEYAZRz.png";
    public final static String STATS_3V3_IMAGE = "https://i.imgur.com/igCOlpX.png";

    public Retrofit getBattleriteRetrofit() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(loggingInterceptor);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder().header("Authorization", Auth.BATTLERITE_TOKEN)
                        .header("Accept", "application/vnd.api+json").method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BATTLERITE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(client).build();

        return retrofit;
    }

    public String getLeage(int division) {
        switch (division) {
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

    public EmbedBuilder getBasicEmbedMessage(String title, String message) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.addField(title, message, false);
        return eb;
    }

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

    public Double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

}