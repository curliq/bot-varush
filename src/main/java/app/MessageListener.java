package app;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;

import app.utils.Helper;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Object that listens for every message send on the discord server
 */

public class MessageListener extends ListenerAdapter {

    private CommandsManager commandsManager;
    private Helper helper = new Helper();
    private HashMap<String, String> commandsShortcuts;

    public MessageListener(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
        commandsShortcuts = new HashMap<>();
        commandsShortcuts.put("!c", "!br stats curlicue");
        commandsShortcuts.put("!ex", "!br stats ExBlack");
        commandsShortcuts.put("!bo", "!br stats Lustknecht");
        commandsShortcuts.put("!bobo", "!br stats Lustknecht 2s");
        commandsShortcuts.put("!bobobo", "!br stats Lustknecht 3s");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        Message message = event.getMessage();
        String content = message.getContentRaw();

        // Check if command is a shortcut, if yes, replace message content with shortcut value
        if (commandsShortcuts.containsKey(content)) 
            content = commandsShortcuts.get(content);
        
        // Check if message starts with "!br" to invoke the specific command
        if (content.toLowerCase().startsWith(Helper.COMMAND_TRIGGER)) {

            Scanner scanner = new Scanner(content);

            // Get the first word after "!br" i.e. the command key
            scanner.next(); //skip first word (ie !br)
            String commandKey = scanner.next().toLowerCase();

            // Create arraylist with the params passed by the player, i.e. each consecutive word
            ArrayList<String> params = new ArrayList<String>();
            while (scanner.hasNext())
                params.add(scanner.next());

            // close the scanner
            scanner.close();

            // Get the command object that matches the command key sent by the player (eg "stats")
            Command command = commandsManager.getCommand(commandKey);

            if (command == null) {
                // The command doesn't exist
                sendMessage(event, helper.getBasicEmbedMessage(Helper.ERROR_TITLE,
                        "That's not really a command, do `!br welp` to see all the commands"));
                return;
            } else {
                // show typing indicator
                event.getChannel().sendTyping().queue();
                // sends the params to the command object to be used
                command.setParams(params);
                // calculate the response from the command (getReply()) and send it
                sendMessage(event, command.getReply());
            }
        }
    }

    /**
     * send a message to the current channel the user is typing in.
     * pass event received from the messages listener and the EmbedBuilder with the message data
     */
    private void sendMessage(MessageReceivedEvent event, EmbedBuilder messageBuilder) {
        event.getChannel().sendMessage(baseMessage(event, messageBuilder)).queue();
    }

    /**
     * base message for every message send, basically just color and footer
     */
    public MessageEmbed baseMessage(MessageReceivedEvent event, EmbedBuilder messageBuilder) {

        messageBuilder.setColor(new Color(Helper.BATTLERITE_COLOR_PRIMARY));
        messageBuilder.setFooter("Requested by " + event.getAuthor().getName() + " ⎪ !br welp for more",
                event.getAuthor().getAvatarUrl());

        return messageBuilder.build();
    };

}