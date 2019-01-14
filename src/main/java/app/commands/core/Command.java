package app.commands.core;

import net.dv8tion.jda.core.EmbedBuilder;

import java.util.ArrayList;

/**
 * Base class for every command
 */

public abstract class Command {

    /** Description of the command, how to use and what it does */
    private String description;

    /** Params of the command, as a list of strings, i.e. "!br stats curlicue" where "curlicue" is param number 0 */
    private ArrayList<String> params = new ArrayList<>();

    /** Each Command object will yield a discord message, this is the ID of that message, null before it's sent */
    private String discordMessageId;

    /** Key is the first keyword after the trigger and is what defines a command, i.e. "stats" */
    public abstract String getKey();

    /** instance of our CommandEventsListener */
    protected CommandEventsListener eventsListener;

    /** Method used to return the reply message, must be implemented */
    public abstract EmbedBuilder getReply();

    /**
     * set our CommandEventsListener instance
     *
     * @param eventsListener the CommandEventsListener instance
     */
    public void setOnCommandEventsListener(CommandEventsListener eventsListener) {
        this.eventsListener = eventsListener;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the params
     */
    public ArrayList<String> getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(ArrayList<String> params) {
        this.params = params;
    }

    /**
     * @return the discordMessageId
     */
    public String getDiscordMessageId() {
        return discordMessageId;
    }

    /**
     * @param discordMessageId the params to set
     */
    public void setDiscordMessageId(String discordMessageId) {
        this.discordMessageId = discordMessageId;
    }

    public interface CommandEventsListener {
        void onMessageUpdated(String discordMessageId, EmbedBuilder eb);
    }
}

