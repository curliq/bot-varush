package app.commands;

import net.dv8tion.jda.core.EmbedBuilder;

import app.commands.core.Command;
import app.utils.GenericUtils;

public class About extends Command {

    public final static String KEY = "about";

    public About() {
        setDescription(
                String.format("`%s %s` - a bit of useless information.", GenericUtils.COMMAND_TRIGGER, getKey()));
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public EmbedBuilder getReply() {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Bot Varush");
        eb.setDescription("About");
        eb.addBlankField(false);
        eb.addField("Some info", "A Battlerite bot kek.", false);
        eb.addBlankField(false);
        eb.addField("Contribute & feature request", String.format(
                "The bot is open source and everyone is welcome to contribute: github.com/curliq/bot-varush, "
                        + "to request a " + "feature please join the server on %s and post on the suggestions channel",
                GenericUtils.VARUSH_DISCORD_SERVER), false);
        eb.addBlankField(false);
        return eb;
    }

}