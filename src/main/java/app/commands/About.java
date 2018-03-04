package app.commands;

import app.Command;
import app.utils.Helper;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;

public class About extends Command {

    public final static String KEY = "about";

    public About(JDA jda) {
        setKey(KEY);
        setDescription("`" + Helper.COMMAND_TRIGGER + " " + getKey() + "` - a bit of useless information.");
        setJda(jda);
    }

    @Override
    public EmbedBuilder getReply() {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Bot Varush");
        eb.setDescription("About");
        eb.addBlankField(false);
        eb.addField("Some info",
                "Just another bot that aims to cover all (read: most) the features a Battlerite bot can have.", false);
        eb.addBlankField(false);
        eb.addField("Contribute & feature request",
                "The bot is open source and everyone is welcome to contribute: github.com/joaosardinha/bot-varush, "
                        + "to request a " + "feature click on Issues and create a new one describing what you'd like "
                        + "to see, or ping " + getJda().getUserById(Helper.OWNER_DISCORD_USER_ID).getAsMention(),
                false);
        eb.addBlankField(false);
        return eb;
    }

}