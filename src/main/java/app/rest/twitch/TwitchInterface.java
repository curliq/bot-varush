package app.rest.twitch;

import app.rest.twitch.pojos.StreamPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TwitchInterface {

    @GET("helix/streams")
    Call<StreamPOJO> getStreamByName(@Query("user_login") String twitchName);

}