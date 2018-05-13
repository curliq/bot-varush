package app.tasks;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import app.db.DbRequests;
import app.rest.HttpRequests;
import app.rest.pojos.MatchPOJO;
import app.rest.pojos.PlayerPOJO;
import app.utils.DbUtils;
import app.utils.GenericUtils;
import app.utils.TextUtils;
import retrofit2.Response;

/** Class with scripts that might want to be ran occasionally */
public class Scripts {

    /**
     * Query matches endpoint on a loop, and save all the players
     *
     * @param initialStartDate the initial date we start getting the matches from
     */
    public static void getNewPlayersFromMatches(LocalDateTime initialStartDate) {

        Set<String> playersIds = new HashSet<>();
        LocalDateTime createdAfter = initialStartDate;

        // Get matches while our date is before now, and update this date every request,
        // to the last match's creation datetime
        while (createdAfter.isBefore(LocalDateTime.now())) {

            try {
                // get the matches since createdAfter, the API limits this to 5 matches only
                Response<MatchPOJO> response = HttpRequests.getMatches(createdAfter.toString() + 'Z');

                refreshRpm(response);

                // loop through all the included data from the (~5) matches we just got
                // the matches endpoint returns {data: [matches], included: [data related to the matches, i.e. players]}
                for (MatchPOJO.IncludedObject includedObject : response.body().getIncluded()) {

                    // check if the included object is a player, if so then save it in db for later,
                    if (includedObject.getType().equals(MatchPOJO.IncludedObjectType.Player.val)) {
                        playersIds.add(includedObject.getId());
                    }
                }

                // stop it if it's not getting matches anymore
                if (response.body().getData().isEmpty())
                    break;

                // get the createdAt datetime from the latest match we got, i.e. the last because they're ordered by
                // date
                String lastMatchCreated = response.body().getData()
                        .get(response.body().getData().size() - 1).getAttributes().getCreatedAt();

                GenericUtils.log("last match created at " + lastMatchCreated);

                // update out createdAfter object, add 1 second to void staying in a loop if 5 matches have the same
                // exact start time
                createdAfter = LocalDateTime.ofInstant(
                        Instant.parse(lastMatchCreated), ZoneId.of(ZoneOffset.UTC.getId())).plusSeconds(1);
                // prevent parsing issues
                if (createdAfter.getSecond() == 0)
                    createdAfter = createdAfter.withSecond(1);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ArrayList<String> playersIdsList = new ArrayList<>(playersIds);

        // loop through all the players and get them 3 by 3 from the API and save them in the db
        for (int i = 0; i < playersIdsList.size() / 3; i += 3) {
            try {
                String ids = playersIdsList.get(i) + "," + playersIdsList.get(i + 1) + "," + playersIdsList.get(i + 2);
                Response<PlayerPOJO> response = HttpRequests.getPlayersByIds(ids);
                for (PlayerPOJO.Data player : response.body().getData())
                    DbRequests.savePlayer(player);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void savePlayersStats() {
        ArrayList<String> playersArray = new ArrayList<>();
        StringBuilder playersIds = new StringBuilder();
        for (int i = 32000; i > 0; i--) {
            ResultSet rs = DbUtils.makeRequest("SELECT id FROM players WHERE c2 is null and name is null limit 5;");
            try {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                playersArray.clear();
                playersIds.setLength(0);
                while (rs.next()) {
                    for (int k = 1; k <= columnsNumber; k++) {
                        String columnValue = rs.getString(k);
                        GenericUtils.log(columnValue + " " + rsmd.getColumnName(k));
                        playersArray.add(columnValue);
                    }
                }
                for (String playedId : playersArray)
                    playersIds.append(playedId).append(',');
                playersIds.setLength(playersIds.length() - 1);

                Response<PlayerPOJO> players = HttpRequests.getPlayersByIds(playersIds.toString());
                for (PlayerPOJO.Data player : players.body().getData()) {
                    DbRequests.savePlayer(player);
                }
                refreshRpm(players);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /** Thread.sleep() when we run out of requests in the quota */
    private static void refreshRpm(Response response) {
        try {
            // sleep if requests is approaching the max
            if (Long.valueOf(response.headers().get("x-ratelimit-remaining")) < 10) {
                long resetInMillis = (Long.valueOf(response.headers().get("x-ratelimit-reset")) / 10000000) + 1000;
                GenericUtils.log(resetInMillis);
                GenericUtils.log("sleeps");
                try {
                    Thread.sleep(resetInMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                GenericUtils.log("wake up");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
