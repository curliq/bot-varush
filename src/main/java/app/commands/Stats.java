package app.commands;

import app.Command;
import app.rest.pojos.TeamStatsPOJO;
import app.rest.pojos.UserPOJO;
import app.utils.Helper;
import net.dv8tion.jda.core.EmbedBuilder;
import retrofit2.Response;

public class Stats extends Command {

    public final static String KEY = "stats";

    Helper helper = new Helper();

    public Stats() {
        setKey(KEY);
        setDescription("`" + Helper.COMMAND_TRIGGER + " " + getKey() + " playername123` - get the stats of a player.");
    }

    @Override
    public EmbedBuilder getReply() {

        Response<UserPOJO> playerResponse;
        Response<TeamStatsPOJO> teamStatsResponse;

        try {
            // fetches the user ID from the user name
            playerResponse = getBattleriteRetrofit().getUserID(getParams().get(0)).execute();

            // checks if player exists
            if (playerResponse.body().getData().isEmpty()) {
                return helper.getBasicEmbedMessage(Helper.ERROR_TITLE, "That's not a player I'm afraid");
            }

            // fetches the team data from the user ID
            teamStatsResponse = getBattleriteRetrofit()
                    .getPlayerStats(playerResponse.body().getData().get(0).getId(), Helper.SEASON).execute();

            // return error message if something went wrong
            if (!playerResponse.isSuccessful() || !teamStatsResponse.isSuccessful())
                return helper.getBasicEmbedMessage(Helper.ERROR_TITLE, Helper.ERROR_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            return helper.getBasicEmbedMessage(Helper.ERROR_TITLE, Helper.ERROR_MESSAGE);
        }

        // get player's details/attributes
        UserPOJO.Attributes playerUser = playerResponse.body().getData().get(0).getAttributes();

        // get the first team of the list of teams from the player
        TeamStatsPOJO.Attributes playerTeam = teamStatsResponse.body().getData().get(0).getAttributes();
        // loop through the teams and check which one is the one for
        // solo (i.e. just one member) just in case it's not the first one
        for (TeamStatsPOJO.Data team : teamStatsResponse.body().getData()) {
            if (team.getAttributes().getStats().getMembers().size() == 1)
                playerTeam = team.getAttributes();
        }

        // get the solo team stats
        TeamStatsPOJO.Stats playerStats = playerTeam.getStats();

        // calculate win rate percentage and check if not NaN
        double winRate = (Double.valueOf(playerStats.getWins())
                / (Double.valueOf(playerStats.getWins()) + Double.valueOf(playerStats.getLosses()))) * 100f;
        String winRateString = Double.isNaN(winRate) ? "git gud" : helper.roundTwoDecimals(winRate) + "%";
        
        // set message's data
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(playerUser.getName());
        eb.setDescription(helper.getPlayerTitle(playerUser.getStats().gettitleID()));
        eb.setThumbnail(Helper.ACCOUNT_IMAGE);
        eb.addBlankField(false);
        eb.addField("Wins", "" + playerStats.getWins(), true);
        eb.addField("Losses", "" + playerStats.getLosses(), true);
        eb.addField("Win ratio", winRateString, true);
        eb.addField("League", "" + helper.getDivision(playerStats.getLeague()) + " " + playerStats.getDivision() + " ("
                + playerStats.getDivisionRating() + ")", true);
        eb.addBlankField(false);
        return eb;
    }

}
