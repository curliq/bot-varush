package app.commands;

import app.Command;
import app.utils.Helper;
import net.dv8tion.jda.core.EmbedBuilder;

public class Live extends Command {

    public final static String KEY = "live";

    public Live() {
        setKey(KEY);
        setDescription("`" + Helper.COMMAND_TRIGGER + " " + getKey()
                + " playerName` - get the stats of the current game this player is in.");

    }

    @Override
    public EmbedBuilder getReply() {

        // getBattleriteRetrofit()
// 
        // EmbedBuilder eb = new EmbedBuilder();
// 
        // eb.setTitle(playerUser.getName() + "'s stats'");
        // eb.setDescription(helper.getPlayerTitle(playerUser.getStats().gettitleID()));
        // eb.setThumbnail(Helper.ACCOUNT_IMAGE);

        return null;
    }

}