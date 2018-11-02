package app.commands;

import com.google.gson.Gson;

import net.dv8tion.jda.core.EmbedBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import app.commands.core.Command;
import app.db.DbRequests;
import app.db.models.Team;
import app.rest.HttpRequests;
import app.rest.pojos.PlayerPOJO;
import app.rest.pojos.TeamStatsPOJO;
import app.rest.pojos.TeamStatsPOJO.Data;
import app.utils.BattleriteUtils;
import app.utils.GenericUtils;
import app.utils.NetworkUtils;
import retrofit2.Response;

public class Stats extends Command {

    public final static String KEY = "stats";
    private final static String PARAM_2V2 = "2s";
    private final static String PARAM_3V3 = "3s";

    private PlayerPOJO.Data playerData;
    private Response<TeamStatsPOJO> teamStatsResponse;

    public Stats() {
        setDescription(String.format(
                "`%1$s %2$s" + " playerName` - get the stats of a player \n" + "`%1$s %2$s"
                        + " playerName 2s` - get the 2v2 teams of a player \n"
                        + "`%1$s %2$s playerName 3s` - get the 3v3 teams of a player",
                GenericUtils.COMMAND_TRIGGER, getKey()));
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public EmbedBuilder getReply() {

        try {
            String playerName = getParams().get(0);
            // fetches the player ID from the player name
            Response<PlayerPOJO> playerResponse = HttpRequests.getPlayerByName(playerName);
//            GenericUtils.log(new Gson().toJson(playerResponse.body()));

            // checks if player exists
            if (playerResponse.body().getData().isEmpty())
                return GenericUtils.getBasicEmbedMessage(GenericUtils.ERROR_TITLE, "That's not a player I'm afraid");

            GenericUtils.log(Long.valueOf(playerResponse.headers().get("x-ratelimit-remaining")));

            // fetches the team data from the player ID
            teamStatsResponse = HttpRequests.getTeams(playerResponse.body().getData().get(0).getId());
//            GenericUtils.log(new Gson().toJson(teamStatsResponse.body()));

            // return error message if something went wrong
            if (playerResponse.isSuccessful() && !teamStatsResponse.isSuccessful())
                return GenericUtils.getBasicEmbedMessage(GenericUtils.ERROR_TITLE,
                        "Found the player but couldn't find any data");

            else if (!playerResponse.isSuccessful() || !teamStatsResponse.isSuccessful())
                return GenericUtils.getBasicEmbedMessage(GenericUtils.ERROR_TITLE, GenericUtils.ERROR_MESSAGE);

            playerData = playerResponse.body().getData().get(0);
            DbRequests.savePlayer(playerData);

        } catch (Exception e) {
            e.printStackTrace();
            return GenericUtils.getBasicEmbedMessage(GenericUtils.ERROR_TITLE, GenericUtils.ERROR_MESSAGE);
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

        eb.setTitle(playerData.getAttributes().getName(), BattleriteUtils.getBrStatsPlayerUrl(playerData.getId()));
        eb.setDescription(BattleriteUtils.getPlayerTitle(playerData.getAttributes().getStats().getTitleId()));
        eb.setThumbnail(
                GenericUtils.ASSETS_PROFILE_URL + playerData.getAttributes().getStats().getPictureID() + ".png");
        GenericUtils.log(GenericUtils.ASSETS_PROFILE_URL + playerData.getAttributes().getStats().getPictureID() + ".png");
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
        BattleriteUtils.orderTeams(teamsArray);

        eb.setTitle(playerData.getAttributes().getName(), BattleriteUtils.getBrStatsPlayerUrl(playerData.getId()));
        eb.setDescription(
                String.format("%s teams stats - %s teams", (is2v2 ? "2v2" : "3v3"), teamsArray.size()));
        eb.setThumbnail(is2v2 ? BattleriteUtils.STATS_2V2_IMAGE : BattleriteUtils.STATS_3V3_IMAGE);

        if (teamsArray.isEmpty()) {
            eb.addField("Nothing to see here", "flex dab swag turn up", true);
            return eb;
        }

        // get the ids of the players that are in the teams
        String otherPlayersIds = getPlayersInTeamsString(teamsArray);

        ArrayList<PlayerPOJO.Data> otherPlayersList = NetworkUtils.getPlayersFromIds(otherPlayersIds);

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

    /**
     * Make string of the other players in this team
     */
    private String getPlayersInThisTeamText(boolean is2v2, TeamStatsPOJO.Data team,
                                            ArrayList<PlayerPOJO.Data> otherPLayersList) {

        // create a list and add the players of this team to it
        GenericUtils.log("member of " + team.getAttributes().getName() + " " + new Gson().toJson(team.getAttributes().getStats().getMembers()));
        ArrayList<String> otherPlayersNames = otherPLayersList.stream()
                .filter(p -> team.getAttributes().getStats().getMembers().contains(p.getId()))
                .map(p -> p.getAttributes().getName()).collect(Collectors.toCollection(ArrayList::new));
        Set<String> otherPlayersNamesSet = new HashSet<>(otherPlayersNames);
        otherPlayersNames.clear();
        otherPlayersNames.addAll(otherPlayersNamesSet);

        String otherPlayersString = "";
        try {
            if (is2v2)
                otherPlayersString = String.format(" (carried by %s)", otherPlayersNames.get(0));
            else
                otherPlayersString = String.format(" (carried by %s and %s)", otherPlayersNames.get(0),
                        otherPlayersNames.get(1));
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
        Team cachedPoints = DbRequests.getTeam(team.getId());

        // calculate win rate
        double winRate = (Double.valueOf(teamAttrs.getStats().getWins())
                / (Double.valueOf(teamAttrs.getStats().getWins()) + Double.valueOf(teamAttrs.getStats().getLosses())))
                * 100f;
        String winRateString = Double.isNaN(winRate) ? "git gud" : GenericUtils.roundTwoDecimals(winRate) + "%";

        eb.addField("Wins", teamAttrs.getStats().getWins() +
                getWinsLossesDelta(true, teamAttrs.getStats().getWins(), cachedPoints), true);
        eb.addField("Losses", teamAttrs.getStats().getLosses() +
                getWinsLossesDelta(false, teamAttrs.getStats().getLosses(), cachedPoints), true);
        eb.addField("Win ratio", winRateString, true);
        eb.addField("League", makeLeagueText(team, cachedPoints), true);

        GenericUtils.log(teamAttrs.getName());
        // Update team entry with new points amount
        DbRequests.saveTeam(team.getId(), teamAttrs.getStats().getMembers(), teamAttrs.getName(),
                teamAttrs.getStats().getLeague(), teamAttrs.getStats().getDivision(),
                teamAttrs.getStats().getDivisionRating(), teamAttrs.getStats().getAvatar(),
                teamAttrs.getStats().getWins(), teamAttrs.getStats().getLosses(),
                teamAttrs.getStats().getPlacementGamesLeft(), teamAttrs.getStats().getTopLeague(),
                teamAttrs.getStats().getTopDivision(), teamAttrs.getStats().getTopDivisionRating());
    }


    /**
     * Get the difference of wins or losses since the last request
     *
     * @param isWins    true for wins, false for losses
     * @param newAmount the current amount of wins or losses
     */
    private String getWinsLossesDelta(boolean isWins, int newAmount, Team cachedPoints) {
        if (cachedPoints == null)
            return "";

        int oldAmount = isWins ? cachedPoints.getWins() : cachedPoints.getLosses();
        if (newAmount <= oldAmount)
            return "";
        else
            return " *(+" + (newAmount - oldAmount) + ")*";

    }

    /**
     * Make league/division text.
     * Checks if is in placements or not
     * Gets the points delta from database
     */
    private String makeLeagueText(TeamStatsPOJO.Data team, Team cachedPoints) {
        TeamStatsPOJO.Attributes teamAttrs = team.getAttributes();

        if (teamAttrs.getStats().getPlacementGamesLeft() > 0)
            return "Placements: " + teamAttrs.getStats().getPlacementGamesLeft() + " games left";

        return String.format("%s %s, %s pts %s", BattleriteUtils.getLeague(teamAttrs.getStats().getLeague()),
                teamAttrs.getStats().getDivision(), teamAttrs.getStats().getDivisionRating(),
                getPointsDelta(cachedPoints, teamAttrs.getStats()));
    }

    /**
     * Calculate the delta between the current points and cached points considering the division and league
     */
    private String getPointsDelta(Team oldPoints, TeamStatsPOJO.Stats newPoints) {

        if (oldPoints == null)
            return "";

        int newGlobalPoints = BattleriteUtils.getGlobalPoints(newPoints.getLeague(), newPoints.getDivision())
                + newPoints.getDivisionRating();
        int oldGlobalPoints = BattleriteUtils.getGlobalPoints(oldPoints.getLeague(), oldPoints.getDivision())
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
     * Take a list of teams and return a list of players Ids as a string (i.e. "123, 412, 124")
     * @param teamsArray list of teams
     */
    private String getPlayersInTeamsString(ArrayList<Data> teamsArray) {
        StringBuilder otherPlayersIds = new StringBuilder();

        // loop through all the teams, first 4 only, because discord wont show more anyway
        for (int i = 0; i < 4; i++) {
            // loop through all the players in each team
            try {
                for (String otherPlayerID : teamsArray.get(i).getAttributes().getStats().getMembers()) {
                    // check if player isnt the one requesting and isnt repeated
                    if (!otherPlayerID.equals(playerData.getId()))
                        otherPlayersIds.append(otherPlayerID).append(",");
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        // remove last comma
        if (otherPlayersIds.length() > 0)
            otherPlayersIds = new StringBuilder(otherPlayersIds.substring(0, otherPlayersIds.length() - 1));

        return otherPlayersIds.toString();
    }

}
