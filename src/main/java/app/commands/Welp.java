package app.commands;

import java.util.ArrayList;

import app.Command;
import app.utils.GenericUtils;
import net.dv8tion.jda.core.EmbedBuilder;

public class Welp extends Command {

    public final static String KEY = "welp";
    private ArrayList<Command> commandsArray;

    /**
     * This command is the only one that also takes the commandsArray
     */
    public Welp(ArrayList<Command> commandsArray) {
        setKey(KEY);
        setDescription(
                String.format("`%s %s` - get the list of all commands.", GenericUtils.COMMAND_TRIGGER, getKey()));
        this.commandsArray = commandsArray;
    }

    @Override
    public EmbedBuilder getReply() {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("All that Varush knows");
        for (Command command : commandsArray) {
            String title = command.getKey().equals(Live.KEY) ? Live.KEY + "  (coming soon)" : command.getKey();
            eb.addField("", String.format("**%s**\n" + command.getDescription(), title), false);
        }
        eb.addBlankField(false);
        return eb;
    }

}