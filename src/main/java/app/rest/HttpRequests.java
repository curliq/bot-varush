package app.rest;

import java.io.IOException;

import app.rest.pojos.MatchPOJO;
import app.rest.pojos.PlayerPOJO;
import app.rest.pojos.TeamStatsPOJO;
import app.utils.BattleriteUtils;
import app.utils.NetworkUtils;
import app.utils.TextUtils;
import retrofit2.Call;
import retrofit2.Response;

public class HttpRequests {

    private static BattleriteInterface battleriteInterface =
            NetworkUtils.getBattleriteRetrofit().create(BattleriteInterface.class);

    /**
     * Get a player by passing his ign
     *
     * @param playerName his ign
     */
    public static Response<PlayerPOJO> getPlayerByName(String playerName) {
        try {
            return battleriteInterface.getPlayerByName(TextUtils.urlEncode(playerName)).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get a list of players by passing their IDs
     *
     * @param ids the ids of the players we want, comma separated, i.e. "123,43,5234,123"
     */
    public static Response<PlayerPOJO> getPlayersByIds(String ids) {
        try {
            return battleriteInterface.getPlayersByID(ids).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * (Async)
     * Get a list of players by passing their IDs
     *
     * @param ids the ids of the players we want, comma separated, i.e. "123,43,5234,123"
     */
    public static Call<PlayerPOJO> getPlayersByIdsAsync(String ids) {
        return battleriteInterface.getPlayersByID(ids);
    }

    /**
     * Get the all the teams from a player
     *
     * @param playedId the id fo the player we want to get the teams of
     */
    public static Response<TeamStatsPOJO> getTeams(String playedId) {
        try {
            return battleriteInterface.getPlayerStats(playedId, BattleriteUtils.SEASON).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get a list of matches, only returns 5
     *
     * @param filterCreatedAfter filter after match's end datetime
     */
    public static Response<MatchPOJO> getMatches(String filterCreatedAfter) {
        try {
            return battleriteInterface.getMatches(filterCreatedAfter, "RANKED").execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
