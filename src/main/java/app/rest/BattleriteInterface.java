package app.rest;

import app.rest.pojos.PlayerPOJO;
import app.rest.pojos.TeamStatsPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BattleriteInterface {

    @GET("players")
    Call<PlayerPOJO> getPlayerID(@Query("filter[playerNames]") String name);
   
    @GET("players")
    Call<PlayerPOJO> getPlayersByID(@Query("filter[playerIds]") String idsArray); //filter[playerIds]=123,123,123

    @GET("teams")
    Call<TeamStatsPOJO> getPlayerStats(@Query("filter[playerIds]") String id, @Query("tag[season]") int season);

}