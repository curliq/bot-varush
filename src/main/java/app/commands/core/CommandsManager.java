package app.commands.core;

import app.commands.About;
import app.commands.Rank;
import app.commands.Restart;
import app.commands.Stats;
import app.commands.Welp;
import app.commands.WhoTheBestBot;

public final class CommandsManager {

    private CommandsManager() {
    }

    /**
     * Get the command from the commandsArray from it's key
     */
    public static Command getCommand(String key, long authorId) {
        // Loop through all commands until the one with the received key is found
        switch (key) {
            case Stats.KEY:
                return new Stats();
            case About.KEY:
                return new About();
            case Welp.KEY:
                return new Welp();
            case Welp.KEY2:
                return new Welp();
            case WhoTheBestBot.KEY:
                return new WhoTheBestBot();
            case Rank.KEY:
                return new Rank();
            case Restart.KEY:
                return new Restart(authorId);
        }
        return null;
    }

}