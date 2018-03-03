package app;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.rest.twitch.TwitchInterface;
import app.rest.twitch.pojos.StreamPOJO;
import app.utils.Helper;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import retrofit2.Response;

/**
 * This class listens for changes in Game presence on every user in the server.
 * 
 * Porpuse of this is to add a role called Streamer to whomever is streaming Battlerite at the moment and remove it
 * as soon as they finish the stream or stop playing Battlerite.
 * 
 * To use this create a role called {@value utils.Helper.#STREAMER_ROLE_NAME} 
 * and pin it to the right hand side on your server.
 * 
 */
public class StreamerRoleListener extends ListenerAdapter {

    Role streamerRole;

    /**
     * called whenever someone in the server changes their "playing" status
     */
    @Override
    public void onUserGameUpdate(UserGameUpdateEvent event) {
        super.onUserGameUpdate(event);

        // stop if there is no role called Helper.SREAMING_ROLE_NAME
        if (event.getGuild().getRolesByName(Helper.STREAMING_ROLE_NAME, true).isEmpty())
            return;

        if (userHasProbationRole(event))
            return;

        streamerRole = event.getGuild().getRolesByName(Helper.STREAMING_ROLE_NAME, true).get(0);

        Helper.log(event.getUser().getName());
        if (!isStreaming(event))
            removeStreamerRole(event);
        else {
            // start a new thread to check if is streaming battlerite
            Thread one = new Thread() {
                public void run() {
                    if (!isStreamingBattlerite(event))
                        removeStreamerRole(event);
                    else
                        addStreamerRole(event);
                }
            };
            one.start();
        }
    }

    /**
     * Checks if the user is streaming on twitch
     */
    private boolean isStreaming(UserGameUpdateEvent event) {
        if (event.getCurrentGame() == null || event.getCurrentGame().getUrl() == null)
            return false;
        return Game.isValidStreamingUrl(event.getCurrentGame().getUrl());
    }

    /**
     * Checks if the Game/Category on twitch is Battlerite
     */
    private boolean isStreamingBattlerite(UserGameUpdateEvent event) {
        // wait 1 minute before cheking the game from twitch because their api takes a bit to update after changes
        // to the stream
        try {
            Thread.sleep(60000);
        } catch (InterruptedException v) {
            v.printStackTrace();
        }

        TwitchInterface api = new Helper().getTwitchRetrofit().create(TwitchInterface.class);
        String streamUrl = event.getCurrentGame().getUrl();
        String twitchUserName = streamUrl.substring(streamUrl.lastIndexOf('/') + 1);
        // call twitch api to check the user's stream game/category
        try {
            Response<StreamPOJO> response = api.getStreamByName(twitchUserName).execute();
            // no stream found
            if (response.body().getData().isEmpty()) {
                return false;
            }
            return response.body().getData().get(0).getGameId().equals(Helper.TWITCH_BATTLERITE_ID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void addStreamerRole(UserGameUpdateEvent event) {
        Helper.log("add role");
        event.getGuild().getController().addSingleRoleToMember(event.getMember(), streamerRole).queue();
    }

    private void removeStreamerRole(UserGameUpdateEvent event) {
        if (event.getMember().getRoles().contains(streamerRole)) {
            Helper.log("remove role");
            event.getGuild().getController().removeSingleRoleFromMember(event.getMember(), streamerRole).queue();
        }
    }
    
    /**
     * check if user has a "probation" role, if yes then dont add the Streamer role
     */
    private boolean userHasProbationRole(UserGameUpdateEvent event) {
        try {
            Role probationRole = event.getGuild().getRolesByName(Helper.PROBATION_ROLE_NAME, true).get(0);
            return event.getMember().getRoles().contains(probationRole);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}