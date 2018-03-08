package app.commands;

import java.util.ArrayList;

import app.Command;
import app.utils.Helper;
import net.dv8tion.jda.core.EmbedBuilder;

public class WhoTheBestBot extends Command {

    public final static String KEY = "whothebestbot";

    /**
     * This command is the only one that also takes the commandsArray
     */
    public WhoTheBestBot() {
        setKey(KEY);
        setDescription("`" + Helper.COMMAND_TRIGGER + " " + getKey() + "` - surprise feature.");
    }

    @Override
    public EmbedBuilder getReply() {
        EmbedBuilder eb = new EmbedBuilder();
        eb.addField("Who the best bot?", "Varush the best bot", false);
        eb.setImage("https://media.discordapp.net/attachments/302927546323042315/415543751130349568/Battlerite_2018-01-06_16-55-00.png");
        return eb;
    }

} 