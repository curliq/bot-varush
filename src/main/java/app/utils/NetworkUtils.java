package app.utils;

import java.util.ArrayList;
import java.util.HashMap;

import app.Secrets;
import app.rest.HttpRequests;
import app.rest.pojos.PlayerPOJO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private final static String BATTLERITE_BASE_URL = "https://api.developer.battlerite.com/shards/global/";

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

    /**
     * Get a list of players data from a list of Ids as a String
     * "123, 456, 678, 890, 123, 645" becomes "123, 456, 890" (make call) and "123, 645" (make another call)
     *
     * @param playersIds list of players POJOs
     */
    public static ArrayList<PlayerPOJO.Data> getPlayersFromIds(String playersIds) {
        ArrayList<PlayerPOJO.Data> otherPLayersList = new ArrayList<>();

        // get the ids after the 3th id, to make another request because battlerite limits the bulk player request
        // to ~3 players max
        ArrayList<String> splitIds = TextUtils.splitStringInCommas(playersIds, 3);
        for (String ids : splitIds) {
            Response<PlayerPOJO> otherPlayersResponse = HttpRequests.getPlayersByIds(ids);
            otherPLayersList.addAll(otherPlayersResponse.body().getData());
        }

        return otherPLayersList;
    }

    /**
     * Get a list of players data from a list of Ids as an ArrayList
     * Converts the array into a string and then splits it like:
     * ["123, 456, 678, 890, 123, 645"] becomes "123, 456, 890" (make call) and "123, 645" (make another call)
     *
     * @param playersIds array of players POJOs
     */
    public static ArrayList<PlayerPOJO.Data> getPlayersFromIds(ArrayList<String> playersIds) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String id : playersIds)
            stringBuilder.append(id).append(",");
        stringBuilder.setLength(stringBuilder.length() - 1);

        ArrayList<PlayerPOJO.Data> otherPLayersList = new ArrayList<>();

        // get the ids after the 3th id, to make another request because battlerite limits the bulk player request
        // to ~3 players max
        ArrayList<String> splitIds = TextUtils.splitStringInCommas(stringBuilder.toString(), 3);
        for (String ids : splitIds) {
            Response<PlayerPOJO> otherPlayersResponse = HttpRequests.getPlayersByIds(ids);
            otherPLayersList.addAll(otherPlayersResponse.body().getData());
        }

        return otherPLayersList;
    }
}