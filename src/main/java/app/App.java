package app;

import javax.security.auth.login.LoginException;

import app.CommandsManager;
import app.MessageListener;
import app.StreamerRoleListener;
import app.utils.Auth;
import app.utils.Helper;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;

public class App {

    public static void main(String[] args) {
        new App().setUpBot();
    }

    private void setUpBot() {
        try {
            // Initiate the Discord object
            JDA jda = new JDABuilder(AccountType.BOT).setToken(Auth.BOT_TOKEN).buildBlocking();

            // Initiate a CommandsManager object, which builds a list of all the existent commands
            CommandsManager commandsManager = new CommandsManager(jda);
            commandsManager.init();

            // Initiatialize the cache map(s)
            Helper.init();

            // set the messages listener and pass the initiated commandsManager
            jda.addEventListener(new MessageListener(commandsManager));
            // set user game listener for Streamer role, more info at StreamerRoleListener.java
            jda.addEventListener(new StreamerRoleListener());
            jda.getPresence().setGame(Game.of(GameType.WATCHING, " out for !br welp"));

        } catch (InterruptedException | LoginException exception) {
            exception.printStackTrace();
            return;
        }
    }

}
