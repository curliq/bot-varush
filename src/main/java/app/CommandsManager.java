package app;

import java.util.ArrayList;

import app.commands.About;
import app.commands.Live;
import app.commands.Stats;
import app.commands.Welp;

public class CommandsManager {

    ArrayList<Command> commandsArray = new ArrayList<>();

    public void init() {
        // Add all the commands to our array, which will be used a few places
        commandsArray.add(new Stats());
        // commandsArray.add(new Live());
        commandsArray.add(new About());
        commandsArray.add(new Welp(commandsArray));
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