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
    JDA jda;

    public StreamerRoleListener(JDA jda) {
        this.jda = jda;
        streamerRole = jda.getRolesByName(Helper.STREAMING_ROLE_NAME, true).get(0);
    }

    /**
     * called whenever someone in the server changes their "playing" status
     */
    @Override
    public void onUserGameUpdate(UserGameUpdateEvent event) {
        super.onUserGameUpdate(event);

        Helper.log("game updated for " + event.getUser().getName());

        if (isStreaming(event) && isStreamingBattlerite(event)) {
            addStreamerRole(event);
        } else {
            removeStreamerRole(event);
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
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TwitchInterface api = new Helper().getTwitchRetrofit().create(TwitchInterface.class);
        String streamUrl = event.getCurrentGame().getUrl();
        String twitchUserName = streamUrl.substring(streamUrl.lastIndexOf('/') + 1);
        // call twitch api to check the user's stream game/category
        try {
            Response<StreamPOJO> response = api.getStreamByName(twitchUserName).execute();
            if (response.body().getData().isEmpty()) {
                Helper.log("Not streaming");
                return false;
            }
            return response.body().getData().get(0).getGameId().equals(Helper.TWITCH_BATTLERITE_ID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void addStreamerRole(UserGameUpdateEvent event) {
        event.getGuild().getController().addSingleRoleToMember(event.getMember(), streamerRole).queue();
    }

    private void removeStreamerRole(UserGameUpdateEvent event) {
        event.getGuild().getController().removeSingleRoleFromMember(event.getMember(), streamerRole).queue();
    }

}