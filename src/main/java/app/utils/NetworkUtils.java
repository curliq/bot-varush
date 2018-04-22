package app.utils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    public final static String BATTLERITE_BASE_URL = "https://api.dc01.gamelockerapp.com/shards/global/";
    public final static String TWITCH_BASE_URL = "https://api.twitch.tv/";

    private static String TWITCH_TOKEN = Secrets.TWITCH_TOKEN_1;

    /**
     * Get retrofit object to interact with Battlerite API
     */
    public static Retrofit getBattleriteRetrofit() {
        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("Authorization", Secrets.BATTLERITE_TOKEN);
        headersMap.put("Accept", "application/vnd.api+json");
        return getRetrofit(BATTLERITE_BASE_URL, headersMap);
    }

    /**
     * Get retrofit object to interact with Twitch API
     */
    public static Retrofit getTwitchRetrofit() {
        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("Client-ID", TWITCH_TOKEN);
        return getRetrofit(TWITCH_BASE_URL, headersMap);
    }

    /**
     * Get generic retrofit object
     */
    public static Retrofit getRetrofit(String baseUrl, HashMap<String, String> headersMap) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

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
     * Twitch's quota is 30 requests per minute which is not enough for a server with 1k+ users,
     * so when we get a 429 (exceeded limit) we start using another twitch token.
     * These are all stored in Secrets.java as 'TWITCH_TOKEN_n'
     */
    public static void nextTwitchToken() {
        if (TWITCH_TOKEN.equals(Secrets.TWITCH_TOKEN_1))
            TWITCH_TOKEN = Secrets.TWITCH_TOKEN_2;
        else if (TWITCH_TOKEN.equals(Secrets.TWITCH_TOKEN_2))
            TWITCH_TOKEN = Secrets.TWITCH_TOKEN_3;
        else if (TWITCH_TOKEN.equals(Secrets.TWITCH_TOKEN_3))
            TWITCH_TOKEN = Secrets.TWITCH_TOKEN_1;
    }

}