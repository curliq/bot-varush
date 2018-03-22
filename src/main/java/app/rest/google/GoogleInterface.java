package app.rest.google;

import java.util.HashMap;

import app.rest.google.pojos.UrlShortenerPOJO;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.Query;

public interface GoogleInterface {

    @POST("urlshortener/v1/url")
    Call<UrlShortenerPOJO> getShortenedUrl(@Query("key") String key, @Body HashMap<String, String> body);

}