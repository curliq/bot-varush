package app.commands;

import app.commands.core.Command;
import app.utils.GenericUtils;
import net.dv8tion.jda.core.EmbedBuilder;

public class Live extends Command {

    public final static String KEY = "live";

    public Live() {
        setDescription(String.format("`%s %s playerName` - get the stats of the current game this player is in.",
                GenericUtils.COMMAND_TRIGGER, getKey()));

    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public EmbedBuilder getReply() {

        // getBattleriteRetrofit()
        // 
        // EmbedBuilder eb = new EmbedBuilder();
        // 
        // eb.setTitle(playerPlayer.getName() + "'s stats'");
        // eb.setDescription(GenericUtils.getPlayerTitle(playerPlayer.getStats().getTitleId()));
        // eb.setThumbnail(GenericUtils.ACCOUNT_IMAGE);

        return null;
    }

}