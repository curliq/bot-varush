package app.rest;

import java.io.IOException;

import app.rest.pojos.MatchPOJO;
import app.rest.pojos.PlayerPOJO;
import app.rest.pojos.TeamStatsPOJO;
import app.utils.BattleriteUtils;
import app.utils.GenericUtils;
import app.utils.NetworkUtils;
import retrofit2.Response;

public class HttpRequests {

    public static Response<PlayerPOJO> getPlayerByName(String playerName) {
        try {
            return NetworkUtils.getBattleriteRetrofit().create(BattleriteInterface.class)
                    .getPlayerID(GenericUtils.urlEncode(playerName)).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Response<PlayerPOJO> getPlayersByIds(String ids) {
        try {
            return NetworkUtils.getBattleriteRetrofit().create(BattleriteInterface.class)
                    .getPlayersByID(ids).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Response<TeamStatsPOJO> getTeam(String playedId) {
        try {
            return NetworkUtils.getBattleriteRetrofit().create(BattleriteInterface.class)
                    .getPlayerStats(playedId, BattleriteUtils.SEASON).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Response<MatchPOJO> getMatches(String filterCreatedAfter) {
//        return NetworkUtils.getBattleriteRetrofit().create() //todo finish this
        return null;
    }

}
