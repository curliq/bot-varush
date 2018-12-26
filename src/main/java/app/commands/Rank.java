package app.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import app.commands.core.Command;
import app.db.DbRequests;
import app.db.models.Player;
import app.rest.HttpRequests;
import app.rest.pojos.PlayerPOJO;
import app.utils.BattleriteUtils;
import app.utils.GenericUtils;
import app.utils.NetworkUtils;
import app.utils.TextUtils;

public class Rank extends Command {

    public final static String KEY = "rank";

    private String championName;
    private String playerName;

    final int maxColumnSize = 16;
    private int biggestRankLength = 2;
    private int biggestNameLength = "Name".length();
    private int biggestWinsLength = "Wins".length();
    private int biggestLossesLength = "Losses".length();
    private int padding = 2; //the tables' padding in space characters

    public Rank() {
        setDescription(
                String.format("`%1$s %2$s championName` - Display the players with the most wins with the given champion." +
                                "\n`%1$s %2$s championName playerName` - also show your own position.",
                        GenericUtils.COMMAND_TRIGGER, getKey()));
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public EmbedBuilder getReply() {

        init();

        if (!isChampion()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(championName).append(" is not a champion, the options are:\n\n");
            for (String champion : BattleriteUtils.CHAMPIONS)
                stringBuilder.append(champion).append("\n");
            return GenericUtils.getBasicEmbedMessage(GenericUtils.ERROR_TITLE, stringBuilder.toString());
        }

        // get 15 top players from db
        List<PlayerPOJO.Data> cachedPlayers = DbRequests.getChampionRank(championName, 15)
                .stream().map(Player::getPlayerPojo).collect(Collectors.toList());

        // get the passed player from db
        Player player = DbRequests.getSinglePlayerStats(championName, playerName);

        // get updated data asynchronously
        new Thread(() -> {
            ArrayList<String> playersIds =
                    cachedPlayers.stream().map(PlayerPOJO.Data::getId).collect(Collectors.toCollection(ArrayList::new));

            // get update players from API and sort by champion wins
            List<PlayerPOJO.Data> playersUpdated = NetworkUtils.getPlayersFromIds(playersIds);
            playersUpdated.sort((PlayerPOJO.Data z1, PlayerPOJO.Data z2) ->
                    Integer.compare(z2.getChampionWins(championName), z1.getChampionWins(championName)));

            Player playerUpdatedFromDb = null;
            try {
                // get the updated passed player from API
                PlayerPOJO.Data playerUpdated = HttpRequests.getPlayerByName(playerName).body().getData().get(0);
                // save the player in the db
                DbRequests.savePlayer(playerUpdated);
                // get the player again from the db to get his position
                playerUpdatedFromDb = DbRequests.getSinglePlayerStats(championName, playerName);
            } catch (Exception e) {
                GenericUtils.log("couldn't get player " + playerName + " from API");
                e.printStackTrace();
            }

            // call onMessageUpdated
            if (eventsListener != null)
                eventsListener.onMessageUpdated(
                        getDiscordMessageId(), makeEmbed(playersUpdated, false, playerUpdatedFromDb));

            // update the db with the updated top 15 players
            new Thread(() -> {
                for (PlayerPOJO.Data playerPOJO : playersUpdated)
                    DbRequests.savePlayer(playerPOJO);
            }).start();

        }).start();

        return makeEmbed(cachedPlayers, true, player);
    }

    /**
     * initialize things
     */
    private void init() {
        championName = TextUtils.capitalizeString(getParams().get(0));

        // check if it's ruh kaan
        if (championName.equalsIgnoreCase("ruh") ||  championName.equalsIgnoreCase("shen")) {
            championName = championName + " " + TextUtils.capitalizeString(getParams().get(1));
        }

        try {
            if (championName.toLowerCase().contains("ruh") || championName.toLowerCase().contains("shen"))
                playerName = getParams().get(2);
            else
                playerName = getParams().get(1);
        } catch (IndexOutOfBoundsException ignored) {
        }

    }

    /**
     * Build the embed for the message
     *
     * @param players           the players to put in the main table
     * @param isUpdatingPlayers is still fetching data from the API
     */
    private EmbedBuilder makeEmbed(List<PlayerPOJO.Data> players, boolean isUpdatingPlayers, Player player) {
        EmbedBuilder eb = new EmbedBuilder();

        makeTitle(eb, isUpdatingPlayers);

        // do this before we make the main table to get the sizes for the table so they're all align
        calculateTableSpace(players);
        List<PlayerPOJO.Data> playerInList = new ArrayList<>();
        if (player != null) {
            playerInList.add(player.getPlayerPojo());
            calculateTableSpace(playerInList);
        }

        eb.addField("", getTable(players, true), false);
        eb.addField("", "", true);

        if (player != null) {
            eb.addField(new Field("", getTable(playerInList, false), false));
        } else if (playerName != null)
            eb.addField("", "Player \"" + playerName + "\" not found", false);

        eb.addBlankField(false);
        return eb;
    }

    /**
     * Returns true if it's a champion
     */
    private boolean isChampion() {
        return Arrays.asList(BattleriteUtils.CHAMPIONS).contains(championName);
    }

    /**
     * Set message title
     */
    private void makeTitle(EmbedBuilder eb, boolean isUpdatingPlayers) {
        eb.setAuthor(championName + " leaderboard", "https://battlerite-stats.com/",
                GenericUtils.ASSETS_URL + championName.replace(" ", "") + ".png");
        GenericUtils.log(GenericUtils.ASSETS_URL + championName.replace(" ", "") + ".png");
        StringBuilder description = new StringBuilder();
        description.append("Players with the most wins on ").append(championName);
        if (isUpdatingPlayers)
            description.append(" **(Updating players...**)");
        eb.setDescription(description);
    }

    /**
     * try to make body fit with 1024 as per Discord max size
     * if not keep trying to shrink the message, extraCompact removes every line between each row so
     * shouldn't need to shrink more than that
     *
     * @param playersList list of players to put in the list and their data
     */
    private String getTable(List<PlayerPOJO.Data> playersList, boolean showHeader) {
        List<PlayerPOJO.Data> players = playersList.size() >= 9 ? playersList.subList(0, 10) : playersList;
        String body;
        body = makeTable(players, false, false, showHeader);
        if (body.length() >= GenericUtils.DISCORD_MESSAGE_MAX_CHAR) {
            padding = 1;
            body = makeTable(players, false, false, showHeader);
            if (body.length() >= GenericUtils.DISCORD_MESSAGE_MAX_CHAR) {
                padding = 1;
                body = makeTable(players, true, false, showHeader);
                if (body.length() >= GenericUtils.DISCORD_MESSAGE_MAX_CHAR) {
                    padding = 0;
                    body = makeTable(players, true, false, showHeader);
                    if (body.length() >= GenericUtils.DISCORD_MESSAGE_MAX_CHAR) {
                        padding = 1;
                        body = makeTable(players, true, true, showHeader);
                        if (body.length() > GenericUtils.DISCORD_MESSAGE_MAX_CHAR) {
                            padding = 1;
                            body = makeTable(players, true, true, showHeader);
                        } else
                            body = "Names too big :[";
                    }
                }
            }
        }
        return body;
    }

    /**
     * loop through all players to get the biggest name, wins and losses string length, to use later to
     * calculate
     * the columns width
     *
     * @param players the list of players
     */
    private void calculateTableSpace(List<PlayerPOJO.Data> players) {
        for (PlayerPOJO.Data p : players) {
            String position = p.getPosition() != null ? p.getPosition() : String.valueOf(players.indexOf(p) + 1);
            String name = p.getAttributes().getName();
            int wins = p.getChampionWins(championName);
            int losses = p.getChampionLosses(championName);

            if (position.length() > biggestRankLength)
                biggestRankLength = position.length();
            if (name.length() > biggestNameLength)
                biggestNameLength = name.length();
            if (String.valueOf(wins).length() > biggestWinsLength)
                biggestWinsLength = String.valueOf(wins).length();
            if (String.valueOf(losses).length() > biggestLossesLength)
                biggestLossesLength = String.valueOf(losses).length();
        }

        if (biggestRankLength > maxColumnSize)
            biggestRankLength = maxColumnSize;
        if (biggestNameLength > maxColumnSize)
            biggestNameLength = maxColumnSize;
        if (biggestWinsLength > maxColumnSize)
            biggestWinsLength = maxColumnSize;
        if (biggestLossesLength > maxColumnSize)
            biggestLossesLength = maxColumnSize;
    }

    /**
     * Build a table with rank | name | wins | losses | w/r
     *
     * @param players        the list of players
     * @param isCompact      is the space reduced to not go over the character limit
     * @param isExtraCompact is the space even more reduced to not go over the character limit
     */
    private String makeTable(List<PlayerPOJO.Data> players, boolean isCompact, boolean isExtraCompact,
                             boolean showHeader) {
        final StringBuilder stringBuilder = new StringBuilder();
        final String pipe = TextUtils.pipeSymbol();
        final String cross = "?";

        if (showHeader) {
            stringBuilder
                    // add position title i.e. "  #  ⎪"
                    .append("#").append(TextUtils.whiteSpaces(biggestRankLength - "#".length() + padding)).append(pipe)
                    // add name title i.e. "  Name  ⎪"
                    .append(TextUtils.whiteSpaces(padding)).append("Name")
                    .append(TextUtils.whiteSpaces(biggestNameLength - "Name".length() + padding)).append(pipe)
                    // add wins title i.e. "  Wins  ⎪"
                    .append(TextUtils.whiteSpaces(padding)).append("Wins")
                    .append(TextUtils.whiteSpaces(biggestWinsLength - "Wins".length() + padding)).append(pipe)
                    // add losses title i.e. "  Losses  ⎪"
                    .append(TextUtils.whiteSpaces(padding)).append("Losses")
                    .append(TextUtils.whiteSpaces(biggestLossesLength - "Losses".length() + padding)).append(pipe)
                    // add win rate title i.e. "  W/L  ⎪"
                    .append(TextUtils.whiteSpaces(padding)).append("W/L")
                    .append("\n")
                    // add empty line with underscores and pipes i.e. "___⎪_______________⎪______⎪________⎪_______"
                    .append(TextUtils.characterRepeat('_', biggestRankLength + padding))
                    .append(pipe)
                    .append(TextUtils.characterRepeat('_', biggestNameLength + padding * 2))
                    .append(pipe)
                    .append(TextUtils.characterRepeat('_', biggestWinsLength + padding * 2))
                    .append(pipe)
                    .append(TextUtils.characterRepeat('_', biggestLossesLength + padding * 2))
                    .append(pipe)
                    .append(TextUtils.characterRepeat('_', 6 + padding))
                    .append("\n");
        }
        // add empty line with just pipes i.e. "   ⎪               ⎪      ⎪        ⎪       "
        if (!isCompact)
            stringBuilder
                    .append(TextUtils.whiteSpaces(biggestRankLength + padding))
                    .append(pipe)
                    .append(TextUtils.whiteSpaces(biggestNameLength + padding * 2))
                    .append(pipe)
                    .append(TextUtils.whiteSpaces(biggestWinsLength + padding * 2))
                    .append(pipe)
                    .append(TextUtils.whiteSpaces(biggestLossesLength + padding * 2))
                    .append(pipe)
                    .append("\n");

        // loop through all players and add a line with their data
        for (PlayerPOJO.Data player : players) {
            int position = player.getPosition() != null ?
                    Integer.valueOf(player.getPosition()) : players.indexOf(player) + 1;
            String name = player.getAttributes().getName();
            int wins = player.getChampionWins(championName);
            int losses = player.getChampionLosses(championName);
            // calculate win rate
            double winRate = ((double) wins / ((double) wins + (double) losses)) * 100f;
            String winRateString = Double.isNaN(winRate) ? "git gud" : GenericUtils.roundTwoDecimals(winRate) + "%";

            for (String illegalString : TextUtils.illegalStrings())
                if (name.contains(illegalString))
                    name = name.replace(illegalString, cross);

            // add position i.e. "#2   ⎪"
            stringBuilder
                    .append(position)
                    .append(TextUtils.whiteSpaces(biggestRankLength - String.valueOf(position).length() + padding))
                    .append(pipe);
            // add name i.e. "  Curlicue     ⎪"
            if (name.length() > maxColumnSize) {
                // check if name is longer than 16 chars, if so, break it into two and make two rows for the name
                if (name.length() > maxColumnSize * 2)
                    name = name.substring(0, 31);
                final int mid = name.length() / 2; //get the middle of the String
                String[] parts = {name.substring(0, mid), name.substring(mid)};
                stringBuilder
                        .append(TextUtils.whiteSpaces(padding))
                        .append(parts[0])
                        .append(TextUtils.whiteSpaces(biggestNameLength - parts[0].length() + padding))
                        .append(pipe)
                        .append(TextUtils.whiteSpaces(biggestWinsLength + padding * 2))
                        .append(pipe)
                        .append(TextUtils.whiteSpaces(biggestLossesLength + padding * 2))
                        .append(pipe)
                        .append("\n")
                        .append(TextUtils.whiteSpaces(biggestRankLength + padding))
                        .append(pipe)
                        .append(TextUtils.whiteSpaces(padding))
                        .append(parts[1])
                        .append(TextUtils.whiteSpaces(biggestNameLength - parts[1].length() + padding))
                        .append(pipe);
            } else {
                stringBuilder
                        .append(TextUtils.whiteSpaces(padding)).append(name)
                        .append(TextUtils.whiteSpaces(biggestNameLength - name.length() + padding))
                        .append(pipe);
            }
            // add wins i.e. "  3443  ⎪"
            stringBuilder
                    .append(TextUtils.whiteSpaces(padding)).append(wins)
                    .append(TextUtils.whiteSpaces(biggestWinsLength - String.valueOf(wins).length() + padding))
                    .append(pipe);
            // add losses i.e. "  143  ⎪"
            stringBuilder
                    .append(TextUtils.whiteSpaces(padding)).append(losses)
                    .append(TextUtils.whiteSpaces(biggestLossesLength - String.valueOf(losses).length() + padding))
                    .append(pipe);
            // add win rate i.e. "  62.57%  "
            stringBuilder
                    .append(TextUtils.whiteSpaces(padding)).append(winRateString)
                    .append(TextUtils.whiteSpaces(1));
            stringBuilder.append("\n");

            // add empty line with spaces and pipes i.e. "     ⎪                 ⎪        ⎪        ⎪"
            if (position != 10 && !isExtraCompact)
                stringBuilder
                        .append(TextUtils.whiteSpaces(biggestRankLength + padding)).append(pipe)
                        .append(TextUtils.whiteSpaces(biggestNameLength + padding * 2)).append(pipe)
                        .append(TextUtils.whiteSpaces(biggestWinsLength + padding * 2)).append(pipe)
                        .append(TextUtils.whiteSpaces(biggestLossesLength + padding * 2)).append(pipe);
            stringBuilder.append("\n");

        }
        stringBuilder.append("```");
        stringBuilder.insert(0, "```");
        GenericUtils.log(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
