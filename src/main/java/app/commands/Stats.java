package app.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import com.google.gson.Gson;

import app.Command;
import app.rest.pojos.TeamStatsPOJO;
import app.rest.pojos.TeamStatsPOJO.Attributes;
import app.rest.pojos.PlayerPOJO;
import app.utils.Helper;
import net.dv8tion.jda.core.EmbedBuilder;
import retrofit2.Response;

public class Stats extends Command {

    public final static String KEY = "stats";
    public final static String PARAM_2V2 = "2s";
    public final static String PARAM_3V3 = "3s";

    private Helper helper = new Helper();
    private Response<PlayerPOJO> playerResponse;
    PlayerPOJO.Data playerData;
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
            playerResponse = getBattleriteRetrofit().getPlayerID(getParams().get(0)).execute();

            // checks if player exists
            if (playerResponse.body().getData().isEmpty()) {
                return helper.getBasicEmbedMessage(Helper.ERROR_TITLE, "That's not a player I'm afraid");
            }

            // fetches the team data from the player ID
            teamStatsResponse = getBattleriteRetrofit()
                    .getPlayerStats(playerResponse.body().getData().get(0).getId(), Helper.SEASON).execute();

            // return error message if something went wrong
            if (!playerResponse.isSuccessful() || !teamStatsResponse.isSuccessful())
                return helper.getBasicEmbedMessage(Helper.ERROR_TITLE, Helper.ERROR_MESSAGE);

            playerData = playerResponse.body().getData().get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return helper.getBasicEmbedMessage(Helper.ERROR_TITLE, Helper.ERROR_MESSAGE);
        }

        if (getParams().size() > 1)
            if (getParams().get(1).equals(PARAM_2V2))
                return get2v2Data();
            else if (getParams().get(1).equals(PARAM_3V3))
                return get3v3Data();
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

    private EmbedBuilder get2v2Data() {
        EmbedBuilder eb = new EmbedBuilder();
        ArrayList<Attributes> teamsArray = new ArrayList<>();

        // add all the 2-player teams to a list
        for (TeamStatsPOJO.Data team : teamStatsResponse.body().getData())
            if (team.getAttributes().getStats().getMembers().size() == 2 && !team.getAttributes().getName().isEmpty())
                teamsArray.add(team.getAttributes());

        orderTeams(teamsArray);

        eb.setTitle(playerData.getAttributes().getName());
        eb.setDescription("2v2 teams stats - " + teamsArray.size() + " teams");
        eb.setThumbnail(Helper.STATS_2V2_IMAGE);

        // get the ids of the players that are in the teams
        String otherPlayersIds = getPlayersInTeams(teamsArray);

        // get those players all in one request
        Response<PlayerPOJO> otherPlayers = null;
        try {
            otherPlayers = getBattleriteRetrofit().getPlayersByID(otherPlayersIds).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (TeamStatsPOJO.Attributes team : teamsArray) {

            // get the ID of the other player in the team by looping to the otherPLayers list and 
            // get the one that this team contains
            assert otherPlayers != null;
            String otherPlayerName = "";
            for (PlayerPOJO.Data otherPlayer : otherPlayers.body().getData()) {
                if (team.getStats().getMembers().contains(otherPlayer.getId())) {
                    otherPlayerName = " (carried by " + otherPlayer.getAttributes().getName() + ")";
                }
            }

            eb.addBlankField(false);

            eb.addField(team.getName() + otherPlayerName, "————————————————————", false);
            addTeamInfo(eb, team);
        }

        eb.addBlankField(false);

        return eb;
    }

    private EmbedBuilder get3v3Data() {
        EmbedBuilder eb = new EmbedBuilder();
        ArrayList<Attributes> teamsArray = new ArrayList<>();

        // add all the 3-player teams to a list
        for (TeamStatsPOJO.Data team : teamStatsResponse.body().getData())
            if (team.getAttributes().getStats().getMembers().size() == 3 && !team.getAttributes().getName().isEmpty())
                teamsArray.add(team.getAttributes());

        orderTeams(teamsArray);

        eb.setTitle(playerData.getAttributes().getName());
        eb.setDescription("3v3 teams stats - " + teamsArray.size() + " teams");
        eb.setThumbnail(Helper.STATS_3V3_IMAGE);

        // get the ids of all the players that are in the teams
        String otherPlayersIds = getPlayersInTeams(teamsArray);

        // get those players all in one request
        ArrayList<PlayerPOJO.Data> otherPLayersList = new ArrayList<>();
        try {
            Response<PlayerPOJO> otherPlayersResponse = getBattleriteRetrofit().getPlayersByID(otherPlayersIds).execute();
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

        for (TeamStatsPOJO.Attributes team : teamsArray) {

            // get the ID of the other player in the team by looping to the otherPLayers list and 
            // get the one that this team contains
            ArrayList<String> otherPlayersNames = new ArrayList<>();
            for (PlayerPOJO.Data otherPlayer : otherPLayersList) {
                if (team.getStats().getMembers().contains(otherPlayer.getId())) {
                    otherPlayersNames.add(otherPlayer.getAttributes().getName());
                }
            }

            String otherPlayersString = "";
            try {
                otherPlayersString = " (carried by " + otherPlayersNames.get(0) + " and " + otherPlayersNames.get(1)
                        + ")";
            } catch (IndexOutOfBoundsException e) {
                System.out.println("IndexOutOfBoundsException on Stats:189");
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

        return otherPlayersIds;
    }

}
