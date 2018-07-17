package app.commands;

import net.dv8tion.jda.core.EmbedBuilder;

import java.util.Timer;
import java.util.TimerTask;

import app.App;
import app.commands.core.Command;
import app.utils.GenericUtils;

public class Restart extends Command {

    public final static String KEY = "restart";

    private long authorId;

    /**
     * Initiate command passing the author (who requested the command)
     *
     * @param authorId the user's discriminator
     */
    public Restart(long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public EmbedBuilder getReply() {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Restart Varush");
        if (isAuthorized()) {
            eb.setDescription("Restarted");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    App.jda.shutdown();
                    App.initiateJda();
                }
            }, 1000);
        } else {
            eb.setDescription("I don't answer to you.");
        }
        return eb;
    }

    private boolean isAuthorized() {
        return GenericUtils.OWNER_DISCORD_USER_ID == authorId;
    }
}