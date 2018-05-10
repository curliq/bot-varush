package app.commands;

import net.dv8tion.jda.core.EmbedBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Handler;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import app.Command;
import app.db.DbRequests;
import app.db.models.Player;
import app.rest.HttpRequests;
import app.rest.pojos.PlayerPOJO;
import app.utils.BattleriteUtils;
import app.utils.GenericUtils;
import app.utils.TextUtils;

public class Rank extends Command {

    public final static String KEY = "rank";

    public Rank() {
        setDescription(
                String.format("`%s %s championName` - Display the players with the most wins with the given champion.",
                        GenericUtils.COMMAND_TRIGGER, getKey()));
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    protected EmbedBuilder getReply() {
        String championName = TextUtils.capitalizeString(getParams().get(0));

        // check if it's ruh kaan
        if (championName.toLowerCase().equals("ruh"))
            championName = championName + " " + TextUtils.capitalizeString(getParams().get(1));

        // check if its a champion
        if (!Arrays.asList(BattleriteUtils.CHAMPIONS).contains(championName))
            return GenericUtils.getBasicEmbedMessage(GenericUtils.ERROR_TITLE,
                    "Ask SLS to make " + championName + " a champion.");

        // add title, description and champion image
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor(championName + " leaderboard", "https://google.com", GenericUtils.ASSETS_URL + championName
                .replace(" ", "") + ".png");
        eb.setDescription("Players with the most wins on " + championName);

        // get 15 players from database, and update them from api, sort them, and make list of top 10
        List<String> playersIds = DbRequests.getChampionRank(championName).stream()
                .map(p -> p.getPlayerPojo().getId()).collect(Collectors.toCollection(ArrayList::new));
        StringBuilder stringBuilder = new StringBuilder();
        for (String id : playersIds)
            stringBuilder.append(id).append(",");
        stringBuilder.setLength(stringBuilder.length() - 1);

        List<PlayerPOJO.Data> players = BattleriteUtils.getPlayersInTeams(stringBuilder.toString());
        List<PlayerPOJO.Data> finalPlayers = players;
        new Thread(() -> {
            for (PlayerPOJO.Data playerPOJO : finalPlayers)
                DbRequests.savePlayer(playerPOJO.getAttributes(), playerPOJO);
        }).start();
        String finalChampionName = championName;
        players.sort((PlayerPOJO.Data z1, PlayerPOJO.Data z2) ->
                Integer.compare(z2.getChampionWins(finalChampionName), z1.getChampionWins(finalChampionName)));
        players = players.subList(0, 10);

        // try to make body fit with 1024 as per Discord max size
        // if not keep trying to shrink the message, extraCompact removes every line between each row so shouldn't
        // need to shrink more than that
        String body;
        body = makeBody(players, championName, 2, false, false);
        if (body.length() >= 1024) {
            body = makeBody(players, championName, 1, false, false);
            if (body.length() >= 1024) {
                body = makeBody(players, championName, 1, true, false);
                if (body.length() >= 1024) {
                    body = makeBody(players, championName, 0, true, false);
                    if (body.length() >= 1024) {
                        body = makeBody(players, championName, 1, true, true);
                        if (body.length() > 1024)
                            body = makeBody(players, championName, 0, true, true);
                        else
                            body = "Names too big :[";
                    }
                }
            }
        }

        eb.addField("", body, false);
        return eb;
    }

    private String makeBody(List<PlayerPOJO.Data> players, String championName, int padding, boolean isCompact,
                            boolean isExtraCompact) {
        StringBuilder stringBuilder = new StringBuilder();
        String pipe = TextUtils.pipeSymbol();
        String cross = "?";
        int maxColumnSize = 16;
        int biggestNameLength = "Name".length();
        int biggestWinsLength = "Wins".length();
        int biggestLossesLength = "Losses".length();

        // loop through all players to get the biggest name, wins and losses string length, to use later to calculate
        // the columns width
        for (PlayerPOJO.Data p : players) {
            String name = p.getAttributes().getName();
            int wins = p.getChampionWins(championName);
            int losses = p.getChampionLosses(championName);

            if (name.length() > biggestNameLength)
                biggestNameLength = name.length();
            if (String.valueOf(wins).length() > biggestWinsLength)
                biggestWinsLength = String.valueOf(wins).length();
            if (String.valueOf(losses).length() > biggestLossesLength)
                biggestLossesLength = String.valueOf(losses).length();
        }

        if (biggestNameLength > maxColumnSize)
            biggestNameLength = maxColumnSize;
        if (biggestWinsLength > maxColumnSize)
            biggestWinsLength = maxColumnSize;
        if (biggestLossesLength > maxColumnSize)
            biggestLossesLength = maxColumnSize;

        stringBuilder
                // add position title i.e. "  #  ⎪"
                .append("#").append(TextUtils.whiteSpaces(1 + padding)).append(pipe)
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
                .append(TextUtils.characterRepeat('_', 2 + padding))
                .append(pipe)
                .append(TextUtils.characterRepeat('_', biggestNameLength + padding * 2))
                .append(pipe)
                .append(TextUtils.characterRepeat('_', biggestWinsLength + padding * 2))
                .append(pipe)
                .append(TextUtils.characterRepeat('_', biggestLossesLength + padding * 2))
                .append(pipe)
                .append(TextUtils.characterRepeat('_', 6 + padding))
                .append("\n");

        // add empty line with just pipes i.e. "   ⎪               ⎪      ⎪        ⎪       "
        if (!isCompact)
            stringBuilder
                    .append(TextUtils.whiteSpaces(2 + padding))
                    .append(pipe)
                    .append(TextUtils.whiteSpaces(biggestNameLength + padding * 2))
                    .append(pipe)
                    .append(TextUtils.whiteSpaces(biggestWinsLength + padding * 2))
                    .append(pipe)
                    .append(TextUtils.whiteSpaces(biggestLossesLength + padding * 2))
                    .append(pipe)
                    .append(TextUtils.whiteSpaces(6 + padding))
                    .append("\n");

        // loop through all players and add a line with their data
        for (PlayerPOJO.Data player : players) {
            int position = players.indexOf(player) + 1;
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
                    .append(TextUtils.whiteSpaces((position >= 10 ? 0 : 1) + padding))
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
                        .append(TextUtils.whiteSpaces(2 + padding))
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
            if (position != 10 &&
                    !isExtraCompact)
                stringBuilder
                        .append(TextUtils.whiteSpaces(2 + padding)).append(pipe)
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
