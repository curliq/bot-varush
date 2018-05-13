package app;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;

import javax.security.auth.login.LoginException;

import app.discord.MessageListener;
import app.discord.StreamingRoleListener;
import app.tasks.TasksManager;


public class App {

    public static JDA jda;

    public static void main(String[] args) {
        new App().setUpBot();
    }

    private void setUpBot() {
        try {
            // Initiate the Discord object
            jda = new JDABuilder(AccountType.BOT).setToken(Secrets.BOT_TOKEN).buildBlocking();

            // set the messages listener and pass the initiated commandsManager
            // and the user's game listener for Streaming role, more info at StreamingRoleListener.java
            jda.addEventListener(new MessageListener(), new StreamingRoleListener(jda));

            // set user game listener for Streamer role, more info at StreamingRoleListener.java
            jda.addEventListener(new StreamingRoleListener(jda));

            // set the Bot's game in discord to "Watching out for !br welp"
            jda.getPresence().setGame(Game.of(GameType.WATCHING, " out for !br welp"));

            // schedule tasks
            TasksManager.init();
        } catch (InterruptedException | LoginException exception) {
            exception.printStackTrace();
        }
    }

}
