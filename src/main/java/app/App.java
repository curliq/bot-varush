package app;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;

import javax.security.auth.login.LoginException;

public class App {

    public static void main(String[] args) {
        new App().setUpBot();
    }

    public static JDA jda;

    private void setUpBot() {
        try {
            // Initiate the Discord object
            jda = new JDABuilder(AccountType.BOT).setToken(Secrets.BOT_TOKEN).buildBlocking();

            // Initiate a CommandsManager object, which builds a list of all the existent commands

            // set the messages listener and pass the initiated commandsManager
            jda.addEventListener(new MessageListener());
            // set user game listener for Streamer role, more info at StreamingRoleListener.java
            jda.addEventListener(new StreamingRoleListener(jda));
            jda.getPresence().setGame(Game.of(GameType.WATCHING, " out for !br welp"));


        } catch (InterruptedException | LoginException exception) {
            exception.printStackTrace();
        }
    }

}
