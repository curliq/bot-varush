package app.commands;

import net.dv8tion.jda.core.EmbedBuilder;

import java.util.ArrayList;

import app.commands.core.Command;
import app.utils.GenericUtils;

public class Welp extends Command {

    public final static String KEY = "welp";
    public final static String KEY2 = "help";

    /**
     * This command is the only one that also takes the commandsArray
     */
    public Welp() {
        setDescription(
                String.format("`%s %s` - get the list of all commands.", GenericUtils.COMMAND_TRIGGER, getKey()));
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public EmbedBuilder getReply() {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("All that Varush knows");

        ArrayList<Command> commandsArray = new ArrayList<>();
        // Add all the commands to our array, which will be used a few places
        commandsArray.add(new Stats());
        // commandsArray.add(new Live());
        commandsArray.add(new Rank());
        commandsArray.add(new About());
        commandsArray.add(new Welp());

        for (Command command : commandsArray) {
            String title = command.getKey().equals(Live.KEY) ? Live.KEY + "  (coming soon)" : command.getKey();
            eb.addField("", String.format("**%s**\n" + command.getDescription(), title), false);
        }
        eb.addBlankField(false);
        return eb;
    }

}