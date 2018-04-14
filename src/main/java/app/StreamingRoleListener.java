package app;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import app.rest.twitch.TwitchInterface;
import app.rest.twitch.pojos.StreamPOJO;
import app.utils.Helper;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.core.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import retrofit2.Response;

/**
 * This class listens for changes in Game presence on every user in the server.
 * 
 * Purpose of this is to add a role called Streamer to whomever is streaming Battlerite at the moment and remove it
 * as soon as they finish the stream or stop playing Battlerite.
 * 
 * To use this create a role called {@value utils.Helper.#STREAMER_ROLE_NAME} 
 * and pin it to the right hand side on your server.
 */
public class StreamingRoleListener extends ListenerAdapter {

    private final int RECALL_TWITCH_INTERVAL = 90 * 1000; // 1:30 minutes

    private Role streamerRole;

    public StreamingRoleListener(JDA jda) {
        for (Member m : jda.getGuildsByName("battlerite", true).get(0).getMembers())
            runChecks(jda.getGuildsByName("battlerite", true).get(0), m, m.getGame());
    }

    /**
     * called whenever someone in the server changes their "playing" status
     */
    @Override
    public void onUserGameUpdate(UserGameUpdateEvent event) {
        super.onUserGameUpdate(event);

        // this isn't in runChecks, so we still remove the role after twitch callback, if the user was given the 
        // probation role while streaming
        if (userHasProbationRole(event.getGuild(), event.getMember()))
            return;

        runChecks(event.getGuild(), event.getMember(), event.getCurrentGame());
    }

    /**
     * Called whenever someone gets added a new role
     * Useful for when someone joins the server already streaming, so game change wont be detected, but
     * if they add region role then we can add the Streaming role
     */
    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        super.onGuildMemberRoleAdd(event);
        Helper.log(event.getUser().getName() + " added role " + event.getRoles().get(0).getName());

        // this isn't in runChecks, so we still remove the role after twitch callback, if the user was given the 
        // probation role while streaming
        if (userHasProbationRole(event.getGuild(), event.getMember()))
            return;

        if (!event.getRoles().get(0).getName().equals(Helper.STREAMING_ROLE_NAME))
            runChecks(event.getGuild(), event.getMember(), event.getMember().getGame());
    }

    private void runChecks(Guild guild, Member member, Game currentGame) {

        if (!userHasAtLeastOneRole(member))
            return;

        // stop if there is no role called Helper.SREAMING_ROLE_NAME
        try {
            streamerRole = guild.getRolesByName(Helper.STREAMING_ROLE_NAME, true).get(0);
        } catch (Exception e) {
            Helper.log("no streamer role found");
            e.printStackTrace();
            return;
        }

        if (!isStreaming(currentGame))
            removeStreamerRole(guild, member);
        else {
            Helper.log(member.getEffectiveName() + " is streaming");

            // Start a new thread to check if is streaming battlerite (because it will call twitch API)
            Thread newThread = new Thread() {
                public void run() {
                    if (isStreamingBattlerite(currentGame))
                        addStreamerRole(guild, member);
                    else
                        removeStreamerRole(guild, member);
                }
            };
            newThread.start();

            // Is streaming so call twitch again soon
            scheduleUpdate(guild, member, currentGame);
        }
    }

    /**
     * Checks if the user is streaming on twitch
     */
    private boolean isStreaming(Game currentGame) {
        if (currentGame == null || currentGame.getUrl() == null)
            return false;
        return Game.isValidStreamingUrl(currentGame.getUrl());
    }

    /**
     * Checks if the Game/Category on twitch is Battlerite
     */
    private boolean isStreamingBattlerite(Game currentGame) {
        TwitchInterface api = new Helper().getTwitchRetrofit().create(TwitchInterface.class);
        String streamUrl = currentGame.getUrl();
        String twitchUserName = streamUrl.substring(streamUrl.lastIndexOf('/') + 1);
        // call twitch api to check the user's stream game/category
        try {
            Response<StreamPOJO> response = api.getStreamByName(twitchUserName).execute();
            Helper.log(new Gson().toJson(response.body()));
            // no stream found
            if (response.body().getData().isEmpty()) {
                return false;
            }
            return response.body().getData().get(0).getGameId().equals(Helper.TWITCH_BATTLERITE_ID);
        } catch (IOException e) {
            Helper.log("failed getting response from twitch");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Add the role to the user
     */
    private void addStreamerRole(Guild guild, Member member) {
        if (!member.getRoles().contains(streamerRole)) {
            Helper.log("add role to " + member);
            guild.getController().addSingleRoleToMember(member, streamerRole).queue();
        }
    }

    /**
     * Check if user has the role and remove it
     */
    private void removeStreamerRole(Guild guild, Member member) {
        if (member.getRoles().contains(streamerRole)) {
            Helper.log("remove role from " + member);
            guild.getController().removeSingleRoleFromMember(member, streamerRole).queue();
        }
    }

    /**
     * Schedule another call to twich to check if the game/category has changed
     */
    private void scheduleUpdate(Guild guild, Member member, Game currentGame) {
        TimerTask task = new TimerTask() {
            public void run() {
                runChecks(guild, member, currentGame);
            }
        };
        new Timer().schedule(task, RECALL_TWITCH_INTERVAL);
    }

    /**
     * Check if the user as at least one role, which will normally be the region role
     */
    private boolean userHasAtLeastOneRole(Member member) {
        return !member.getRoles().isEmpty();
    }

    /**
     * Check if user has a "probation" role, if yes then dont add the Streamer role
     */
    private boolean userHasProbationRole(Guild guild, Member member) {
        try {
            Role probationRole = guild.getRolesByName(Helper.PROBATION_ROLE_NAME, true).get(0);
            return member.getRoles().contains(probationRole);
        } catch (IndexOutOfBoundsException e) {
            Helper.log("no probation role found");
            e.printStackTrace();
            return false;
        }
    }
}