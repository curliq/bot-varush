package app.commands;

import net.dv8tion.jda.core.EmbedBuilder;

import app.App;
import app.Command;
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
                "The bot is open source and everyone is welcome to contribute: github.com/joaosardinha/bot-varush, "
                        + "to request a " + "feature click on Issues and create a new one describing what you'd like "
                        + "to see, or ping %s",
                App.jda.getUserById(GenericUtils.OWNER_DISCORD_USER_ID).getAsMention()), false);
        eb.addBlankField(false);
        return eb;
    }

}