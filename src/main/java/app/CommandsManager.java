package app;

import java.util.ArrayList;

import app.commands.About;
import app.commands.Live;
import app.commands.Stats;
import app.commands.Welp;

public class CommandsManager {

    ArrayList<Command> commandsArray = new ArrayList<>();

    public void init() {
        commandsArray.add(new Stats());
        // commandsArray.add(new Live());
        commandsArray.add(new About());
        commandsArray.add(new Welp(commandsArray));
    }

    public Command getCommand(String key) {
        for (Command command : commandsArray) {
            if (command.getKey().equals(key))
                return command;
        }
        return null;
    }

}