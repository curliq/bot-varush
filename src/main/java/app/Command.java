package app;

import java.util.ArrayList;

import javax.annotation.Nullable;

import app.rest.battlerite.BattleriteInterface;
import app.utils.BattleriteUtils;
import app.utils.GenericUtils;
import app.utils.NetworkUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;

/**
 * Base class for every command
 */

public abstract class Command {

    /**
     * Key is the first keyword after the trigger and is what defines a command, i.e. "stats"
     */
    private String key;

    /**
     * Description of the command, how to use and what it does
     */
    private String description;

    /**
     * Params of the command, as a list of strings, i.e. "!br stats curlicue" where "curlicue" is param number 0
     */
    private ArrayList<String> params;

    /**
     * JDA object
     */
    private JDA jda;

    /**
    * Method used to return the repply message, must be implemented
    */
    protected abstract EmbedBuilder getReply();

    /**
     * Return Retrofit instance to use for Battlerite API
     */
    protected BattleriteInterface getBattleriteRetrofit() {
        return NetworkUtils.getBattleriteRetrofit().create(BattleriteInterface.class);
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
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
        if (params != null)
            return params;
        else
            return new ArrayList<>();
    }

    /**
     * @param params the params to set
     */
    public void setParams(ArrayList<String> params) {
        this.params = params;
    }

    /**
     * @return the jda
     */
    @Nullable
    public JDA getJda() {
        return jda;
    }

    /**
     * @param jda the jda to set
     */
    public void setJda(JDA jda) {
        this.jda = jda;
    }

}