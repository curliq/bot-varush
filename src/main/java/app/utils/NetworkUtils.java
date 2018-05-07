package app.utils;

import java.util.HashMap;

import app.Secrets;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private final static String BATTLERITE_BASE_URL = "https://api.dc01.gamelockerapp.com/shards/global/";

    /**
     * Get retrofit object to interact with Battlerite API
     */
    public static Retrofit getBattleriteRetrofit() {
        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("Authorization", "Bearer " + Secrets.BATTLERITE_TOKEN);
        headersMap.put("Accept", "application/vnd.api+json");
        return getRetrofit(BATTLERITE_BASE_URL, headersMap);
    }

    /**
     * Get generic retrofit object
     */
    private static Retrofit getRetrofit(String baseUrl, HashMap<String, String> headersMap) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(loggingInterceptor);
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Builder requestBuilder = original.newBuilder();
            if (headersMap != null)
                for (String header : headersMap.keySet())
                    requestBuilder.addHeader(header, headersMap.get(header));

            requestBuilder.method(original.method(), original.body());
            //
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        OkHttpClient client = httpClient.build();

        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .client(client).build();
    }

}