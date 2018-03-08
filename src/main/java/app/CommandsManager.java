package app;

import java.util.ArrayList;

import app.commands.About;
import app.commands.Live;
import app.commands.Stats;
import app.commands.Welp;
import app.commands.WhoTheBestBot;
import net.dv8tion.jda.core.JDA;

public class CommandsManager {

    ArrayList<Command> commandsArray = new ArrayList<>();
    JDA jda;

    public CommandsManager(JDA jda) {
        this.jda = jda;
    }

    public void init() {
        // Add all the commands to our array, which will be used a few places
        commandsArray.add(new Stats());
        // commandsArray.add(new Live());
        commandsArray.add(new About(jda));
        commandsArray.add(new Welp(commandsArray));
        commandsArray.add(new WhoTheBestBot());
    }

    /**
     * Get the command from the commandsArray from it's key
     */
    public Command getCommand(String key) {
        // Loop through all commands until the one with the received key is found
        for (Command command : commandsArray) {
            if (command.getKey().equals(key))
                return command;
        }
        return null;
    }

}