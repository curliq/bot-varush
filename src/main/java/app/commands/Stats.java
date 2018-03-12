package app.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

import app.Command;
import app.rest.battlerite.pojos.TeamStatsPOJO;
import app.rest.battlerite.pojos.TeamStatsPOJO.Attributes;
import app.rest.battlerite.pojos.PlayerPOJO;
import app.utils.Helper;
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
        TeamStatsPOJO.Attributes playerTeam = teamStatsResponse.body().getData().get(0).getAttributes();
        // loop through the teams and check which one is the one for
        // solo (i.e. just one member) just in case it's not the first one
        for (TeamStatsPOJO.Data team : teamStatsResponse.body().getData()) {
            if (team.getAttributes().getStats().getMembers().size() == 1)
                playerTeam = team.getAttributes();
        }
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle(playerData.getAttributes().getName());
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
        ArrayList<Attributes> teamsArray = teamStatsResponse.body().getData().stream()
                .filter(p -> p.getAttributes().getStats().getMembers().size() == (is2v2 ? 2 : 3))
                .filter(p -> p.getAttributes().getStats().getPlacementGamesLeft() == 0)
                .map(p -> p.getAttributes())
                .collect(Collectors.toCollection(ArrayList::new));

        // order the teams by division
        orderTeams(teamsArray);

        eb.setTitle(playerData.getAttributes().getName());
        eb.setDescription((is2v2 ? "2v2" : "3v3") + " teams stats - " + teamsArray.size() + " teams");
        eb.setThumbnail(is2v2 ? Helper.STATS_2V2_IMAGE : Helper.STATS_3V3_IMAGE);

        if (teamsArray.isEmpty()) {
            eb.addField("Nothing to see here", "flex dab swag turn up", true);
            return eb;
        }

        // get the ids of the players that are in the teams
        String otherPlayersIds = getPlayersInTeams(teamsArray);

        // get all the other players in the teams, divide in 2 requests because the api only returns 6 people max
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

        // build view for each team
        for (TeamStatsPOJO.Attributes team : teamsArray) {

            // create a list and add the players of this team to it
            ArrayList<String> otherPlayersNames = otherPLayersList.stream()
                    .filter(p -> team.getStats().getMembers().contains(p.getId()))
                    .map(p -> p.getAttributes().getName())
                    .collect(Collectors.toCollection(ArrayList::new));

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

            eb.addBlankField(false);

            eb.addField(team.getName() + otherPlayersString, "————————————————————", false);
            addTeamInfo(eb, team);
        }

        eb.addBlankField(false);

        return eb;
    }

    //////////////////////////// helper functions

    /**
     * Add wins, losses, win ratio and league (to avoid code duplication)
     */
    private void addTeamInfo(EmbedBuilder eb, TeamStatsPOJO.Attributes team) {
        double winRate = (Double.valueOf(team.getStats().getWins())
                / (Double.valueOf(team.getStats().getWins()) + Double.valueOf(team.getStats().getLosses()))) * 100f;
        String winRateString = Double.isNaN(winRate) ? "git gud" : helper.roundTwoDecimals(winRate) + "%";

        eb.addField("Wins", "" + team.getStats().getWins(), true);
        eb.addField("Losses", "" + team.getStats().getLosses(), true);
        eb.addField("Win ratio", winRateString, true);
        eb.addField("League", "" + helper.getLeage(team.getStats().getLeague()) + " " + team.getStats().getDivision()
                + " (" + team.getStats().getDivisionRating() + ")", true);
    }

    /**
     * Order teams vy division then league then points
     */
    private void orderTeams(ArrayList<Attributes> teamsArray) {

        Comparator<TeamStatsPOJO.Attributes> byLeague = new Comparator<TeamStatsPOJO.Attributes>() {
            public int compare(TeamStatsPOJO.Attributes o1, TeamStatsPOJO.Attributes o2) {
                if (o1.getStats().getLeague() == o2.getStats().getLeague())
                    return 0;
                return o2.getStats().getLeague() - o1.getStats().getLeague();
            }
        };

        Comparator<TeamStatsPOJO.Attributes> byDivision = new Comparator<TeamStatsPOJO.Attributes>() {
            public int compare(TeamStatsPOJO.Attributes o1, TeamStatsPOJO.Attributes o2) {
                if (o1.getStats().getDivision() == o2.getStats().getDivision())
                    return 0;
                return o1.getStats().getDivision() - o2.getStats().getDivision();
            }
        };

        Comparator<TeamStatsPOJO.Attributes> byPoints = new Comparator<TeamStatsPOJO.Attributes>() {
            public int compare(TeamStatsPOJO.Attributes o1, TeamStatsPOJO.Attributes o2) {
                if (o1.getStats().getDivisionRating() == o2.getStats().getDivisionRating())
                    return 0;
                return o2.getStats().getDivisionRating() - o1.getStats().getDivisionRating();
            }
        };

        Collections.sort(teamsArray, byLeague.thenComparing(byDivision).thenComparing(byPoints));

    }

    private String getPlayersInTeams(ArrayList<Attributes> teamsArray) {
        String otherPlayersIds = "";

        // loop through all the teams
        for (TeamStatsPOJO.Attributes team : teamsArray) {
            // loop through all the players in each team
            for (Long otherPlayerID : team.getStats().getMembers()) {
                // check if player isnt the one requesting and isnt repeated
                if (!otherPlayerID.equals(playerData.getId()))
                    otherPlayersIds += otherPlayerID + ",";
            }
        }
        // remove last comma
        if (otherPlayersIds.length() > 0)
            otherPlayersIds = otherPlayersIds.substring(0, otherPlayersIds.length() - 1);

        Helper.log(otherPlayersIds);

        return otherPlayersIds;
    }

}
