package app.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import java.util.Collections;

import app.Command;
import app.rest.battlerite.pojos.TeamStatsPOJO;
import app.rest.battlerite.pojos.TeamStatsPOJO.Data;
import app.rest.battlerite.pojos.PlayerPOJO;
import app.utils.Helper;
import app.utils.TeamCachedPOJO;
import net.dv8tion.jda.core.EmbedBuilder;
import retrofit2.Response;

public class Stats extends Command {

    public final static String KEY = "stats";
    public final static String PARAM_2V2 = "2s";
    public final static String PARAM_3V3 = "3s";

    private Helper helper = new Helper();
    private PlayerPOJO.Data playerData;
    private Response<TeamStatsPOJO> teamStatsResponse;

    public Stats() {
        setKey(KEY);
        setDescription("`" + Helper.COMMAND_TRIGGER + " " + getKey() + " playerName` - get the stats of a player \n"
                + "`" + Helper.COMMAND_TRIGGER + " " + getKey() + " playerName 2s` - get the 2v2 teams of a player \n"
                + "`" + Helper.COMMAND_TRIGGER + " " + getKey() + " playerName 3s` - get the 3v3 teams of a player");
    }

    @Override
    public EmbedBuilder getReply() {

        try {
            // fetches the player ID from the player name
            Response<PlayerPOJO> playerResponse = getBattleriteRetrofit()
                    .getPlayerID(helper.urlEncode(getParams().get(0))).execute();

            // checks if player exists
            if (playerResponse.body().getData().isEmpty()) {
                return helper.getBasicEmbedMessage(Helper.ERROR_TITLE, "That's not a player I'm afraid");
            }

            // fetches the team data from the player ID
            teamStatsResponse = getBattleriteRetrofit()
                    .getPlayerStats(playerResponse.body().getData().get(0).getId(), Helper.SEASON).execute();

            // return error message if something went wrong
            if (playerResponse.isSuccessful() && !teamStatsResponse.isSuccessful())
                return helper.getBasicEmbedMessage(Helper.ERROR_TITLE, "Found the player but couldn't find any data");

            else if (!playerResponse.isSuccessful() || !teamStatsResponse.isSuccessful())
                return helper.getBasicEmbedMessage(Helper.ERROR_TITLE, Helper.ERROR_MESSAGE);

            playerData = playerResponse.body().getData().get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return helper.getBasicEmbedMessage(Helper.ERROR_TITLE, Helper.ERROR_MESSAGE);
        }

        if (getParams().size() > 1)
            if (getParams().get(1).equals(PARAM_2V2))
                return getTeamsData(true);
            else if (getParams().get(1).equals(PARAM_3V3))
                return getTeamsData(false);
            else
                return getSoloData();
        else
            return getSoloData();
    }

    private EmbedBuilder getSoloData() {
        TeamStatsPOJO.Data playerTeam = teamStatsResponse.body().getData().get(0);
        // loop through the teams and check which one is the one for
        // solo (i.e. just one member) just in case it's not the first one
        for (TeamStatsPOJO.Data team : teamStatsResponse.body().getData()) {
            if (team.getAttributes().getStats().getMembers().size() == 1)
                playerTeam = team;
        }
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle(playerData.getAttributes().getName(), helper.getBrStatsPlayerUrl(playerData.getId()));
        eb.setDescription(helper.getPlayerTitle(playerData.getAttributes().getStats().gettitleID()));
        eb.setThumbnail(Helper.STATS_SOLO_IMAGE);
        eb.addBlankField(false);
        addTeamInfo(eb, playerTeam);
        eb.addBlankField(false);
        return eb;
    }

    private EmbedBuilder getTeamsData(boolean is2v2) {
        EmbedBuilder eb = new EmbedBuilder();

        // add all the teams out of placements to a list
        ArrayList<Data> teamsArray = teamStatsResponse.body().getData().stream()
                .filter(p -> p.getAttributes().getStats().getMembers().size() == (is2v2 ? 2 : 3))
                .filter(p -> p.getAttributes().getStats().getPlacementGamesLeft() == 0)
                .collect(Collectors.toCollection(ArrayList::new));

        // order the teams by division
        orderTeams(teamsArray);

        eb.setTitle(playerData.getAttributes().getName(), helper.getBrStatsPlayerUrl(playerData.getId()));
        eb.setDescription((is2v2 ? "2v2" : "3v3") + " teams stats - " + teamsArray.size() + " teams");
        eb.setThumbnail(is2v2 ? Helper.STATS_2V2_IMAGE : Helper.STATS_3V3_IMAGE);

        if (teamsArray.isEmpty()) {
            eb.addField("Nothing to see here", "flex dab swag turn up", true);
            return eb;
        }

        // get the ids of the players that are in the teams
        String otherPlayersIds = getPlayersInTeamsString(teamsArray);

        // get all the other players in the teams, divide in 2 requests because the api only returns 6 people max
        ArrayList<PlayerPOJO.Data> otherPlayersList = getPlayersInTeams(otherPlayersIds);

        // build view for each team
        for (TeamStatsPOJO.Data team : teamsArray) {

            eb.addBlankField(false);

            eb.addField(team.getAttributes().getName() + getPlayersInThisTeamText(is2v2, team, otherPlayersList),
                    "————————————————————", false);
            addTeamInfo(eb, team);
        }

        eb.addBlankField(false);

        return eb;
    }

    //////////////////////////// helper functions

    /**
     * Get a list of all the other players in the teams
     */
    private ArrayList<PlayerPOJO.Data> getPlayersInTeams(String otherPlayersIds) {
        ArrayList<PlayerPOJO.Data> otherPLayersList = new ArrayList<>();
        try {
            Response<PlayerPOJO> otherPlayersResponse = getBattleriteRetrofit().getPlayersByID(otherPlayersIds)
                    .execute();
            // get the ids after the 6th id, to make another request because battlerite limits the bulk player request
            // to 6 players max
            int i = otherPlayersIds.indexOf(',',
                    1 + otherPlayersIds.indexOf(',', 1 + otherPlayersIds.indexOf(',', 1 + otherPlayersIds.indexOf(',',
                            1 + otherPlayersIds.indexOf(',', 1 + otherPlayersIds.indexOf(','))))));

            // get players excluding the 6 first found from the teams                
            Response<PlayerPOJO> otherPlayersResponse2 = getBattleriteRetrofit()
                    .getPlayersByID(otherPlayersIds.substring(i + 1)).execute();

            // add them all to a list
            otherPLayersList.addAll(otherPlayersResponse.body().getData());
            otherPLayersList.addAll(otherPlayersResponse2.body().getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return otherPLayersList;
    }

    /**
     * Make string of the other players in this team
     */
    private String getPlayersInThisTeamText(boolean is2v2, TeamStatsPOJO.Data team,
            ArrayList<PlayerPOJO.Data> otherPLayersList) {

        // create a list and add the players of this team to it
        ArrayList<String> otherPlayersNames = otherPLayersList.stream()
                .filter(p -> team.getAttributes().getStats().getMembers().contains(p.getId()))
                .map(p -> p.getAttributes().getName()).collect(Collectors.toCollection(ArrayList::new));

        String otherPlayersString = "";
        try {
            if (is2v2)
                otherPlayersString = " (carried by " + otherPlayersNames.get(0) + ")";
            else
                otherPlayersString = " (carried by " + otherPlayersNames.get(0) + " and " + otherPlayersNames.get(1)
                        + ")";
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return otherPlayersString;
    }

    /**
     * Add wins, losses, win ratio and league (to avoid code duplication)
     */
    private void addTeamInfo(EmbedBuilder eb, TeamStatsPOJO.Data team) {
        TeamStatsPOJO.Attributes teamAttrs = team.getAttributes();

        // calculate win rate
        double winRate = (Double.valueOf(teamAttrs.getStats().getWins())
                / (Double.valueOf(teamAttrs.getStats().getWins()) + Double.valueOf(teamAttrs.getStats().getLosses())))
                * 100f;
        String winRateString = Double.isNaN(winRate) ? "git gud" : helper.roundTwoDecimals(winRate) + "%";

        eb.addField("Wins", "" + teamAttrs.getStats().getWins(), true);
        eb.addField("Losses", "" + teamAttrs.getStats().getLosses(), true);
        eb.addField("Win ratio", winRateString, true);
        eb.addField("League", makeLeagueText(team), true);

        // Update team entry with new points amount
        Helper.saveTeamPoints(team.getId(), new TeamCachedPOJO(teamAttrs.getStats().getDivision(),
                teamAttrs.getStats().getLeague(), teamAttrs.getStats().getDivisionRating()));

    }

    /**
     * Make league text.
     * Checks if is in placements or not
     * Gets the points delta
     */
    private String makeLeagueText(TeamStatsPOJO.Data team) {
        TeamStatsPOJO.Attributes teamAttrs = team.getAttributes();
        TeamCachedPOJO cachedPoints = Helper.getTeamPoints(team.getId());

        if (teamAttrs.getStats().getPlacementGamesLeft() > 0)
            return "Placements: " + teamAttrs.getStats().getPlacementGamesLeft() + " games left";

        return helper.getLeague(teamAttrs.getStats().getLeague()) + " " + teamAttrs.getStats().getDivision() + ", "
                + teamAttrs.getStats().getDivisionRating() + "pts" + getPointsDelta(cachedPoints, teamAttrs.getStats());
    }

    /**
     * Calculate the delta between the current points and cached points considering the division and league
     */
    private String getPointsDelta(TeamCachedPOJO oldPoints, TeamStatsPOJO.Stats newPoints) {

        if (oldPoints == null)
            return "";

        // promoted league(s)
        int newGlobalPoints = helper.getGlobalPoints(newPoints.getLeague(), newPoints.getDivision())
                + newPoints.getDivisionRating();
        int oldGlobalPoints = helper.getGlobalPoints(oldPoints.getLeague(), oldPoints.getDivision())
                + oldPoints.getPoints();

        int pointsDiff = newGlobalPoints - oldGlobalPoints;
        String finalString = String.valueOf(pointsDiff);

        if (pointsDiff == 0) {
            return "";
        } else if (pointsDiff > 0) {
            finalString = "+" + pointsDiff;
        }

        return " *(" + finalString + ")*";
    }

    /**
     * Order teams vy division then league then points
     */
    private void orderTeams(ArrayList<Data> teamsArray) {

        Comparator<TeamStatsPOJO.Data> byLeague = new Comparator<TeamStatsPOJO.Data>() {
            public int compare(TeamStatsPOJO.Data o1, TeamStatsPOJO.Data o2) {
                if (o1.getAttributes().getStats().getLeague() == o2.getAttributes().getStats().getLeague())
                    return 0;
                return o2.getAttributes().getStats().getLeague() - o1.getAttributes().getStats().getLeague();
            }
        };

        Comparator<TeamStatsPOJO.Data> byDivision = new Comparator<TeamStatsPOJO.Data>() {
            public int compare(TeamStatsPOJO.Data o1, TeamStatsPOJO.Data o2) {
                if (o1.getAttributes().getStats().getDivision() == o2.getAttributes().getStats().getDivision())
                    return 0;
                return o1.getAttributes().getStats().getDivision() - o2.getAttributes().getStats().getDivision();
            }
        };

        Comparator<TeamStatsPOJO.Data> byPoints = new Comparator<TeamStatsPOJO.Data>() {
            public int compare(TeamStatsPOJO.Data o1, TeamStatsPOJO.Data o2) {
                if (o1.getAttributes().getStats().getDivisionRating() == o2.getAttributes().getStats()
                        .getDivisionRating())
                    return 0;
                return o2.getAttributes().getStats().getDivisionRating()
                        - o1.getAttributes().getStats().getDivisionRating();
            }
        };

        Collections.sort(teamsArray, byLeague.thenComparing(byDivision).thenComparing(byPoints));

    }

    private String getPlayersInTeamsString(ArrayList<Data> teamsArray) {
        String otherPlayersIds = "";

        // loop through all the teams
        for (TeamStatsPOJO.Data team : teamsArray) {
            // loop through all the players in each team
            for (Long otherPlayerID : team.getAttributes().getStats().getMembers()) {
                // check if player isnt the one requesting and isnt repeated
                if (!otherPlayerID.equals(playerData.getId()))
                    otherPlayersIds += otherPlayerID + ",";
            }
        }
        // remove last comma
        if (otherPlayersIds.length() > 0)
            otherPlayersIds = otherPlayersIds.substring(0, otherPlayersIds.length() - 1);

        return otherPlayersIds;
    }

}
