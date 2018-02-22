package app.rest;

import app.rest.pojos.UserPOJO;
import app.rest.pojos.TeamStatsPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BattleriteInterface {

    @GET("players")
    Call<UserPOJO> getUserID(@Query("filter[playerNames]") String name);

    @GET("teams")
    Call<TeamStatsPOJO> getPlayerStats(@Query("filter[playerIds]") Long id, @Query("tag[season]") int season);

    
}