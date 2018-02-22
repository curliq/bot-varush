package app.commands;

import java.util.ArrayList;

import app.Command;
import app.utils.Helper;
import net.dv8tion.jda.core.EmbedBuilder;

public class Welp extends Command {

    public final static String KEY = "welp";

    ArrayList<Command> commandsArray;

    public Welp(ArrayList<Command> commandsArray) {
        setKey(KEY);
        setDescription("`" + Helper.COMMAND_TRIGGER + " " + getKey() + "` - get the list of all commands.");
        this.commandsArray = commandsArray;
    }

    @Override
    public EmbedBuilder getReply() {
        EmbedBuilder eb = new EmbedBuilder();
        for (Command command : commandsArray) {
            String title = command.getKey().equals(Live.KEY) ? Live.KEY + "  (coming soon)" : command.getKey();
            eb.addField(title, command.getDescription(), false);
            eb.addBlankField(false);
        }
        return eb;
    }

}