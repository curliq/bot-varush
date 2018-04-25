package app;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import app.utils.BattleriteUtils;
import app.utils.GenericUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.MessageUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Object that listens for every message send on the discord server
 */

public class MessageListener extends ListenerAdapter {

    private CommandsManager commandsManager;
    private HashMap<String, String> commandsShortcuts;

    public MessageListener(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
        commandsShortcuts = new HashMap<>();
        commandsShortcuts.put("!c", "!br stats curlicue");
        commandsShortcuts.put("!ex", "!br stats ExBlack");
        commandsShortcuts.put("!bo", "!br stats Lustknecht");
        commandsShortcuts.put("!g", "!br stats Grisillo");
        commandsShortcuts.put("!a", "!br stats Azginporsuk");
        commandsShortcuts.put("!9", "!br stats 9gag");
        commandsShortcuts.put("!al1", "!br stats Aldys");
        commandsShortcuts.put("!al2", "!br stats Twitch.tv/AldysTV");
        commandsShortcuts.put("!m", "!br stats marchallificent");
        commandsShortcuts.put("!cash", "!br stats Cash12121");
        commandsShortcuts.put("!ND", "!br stats NeloDante");
    }

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        if (event.getMessage().getCreationTime().isAfter(OffsetDateTime.now().minusSeconds(15)))
            processMessageSent(event.getMessage(), event.getAuthor(), event.getChannel());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        processMessageSent(event.getMessage(), event.getAuthor(), event.getChannel());
    }

    /**
     * Method that gets called when there's a new message or the last message was edited
     * Figures out what command to call basically
     */
    private void processMessageSent(Message message, User author, MessageChannel channel) {

        String content = message.getContentRaw();

        // Check if command contains a shortcut, if yes, replace shortcut text with shortcut value and
        // append the rest of the params, i.e. "!c 2s" becomes "!br stats curlicue 2s"
        for (String shortcutKey : commandsShortcuts.keySet()) {
            if (content.contains(shortcutKey)) {
                int keyIndex = content.contains(" ") ? content.indexOf(" ") : content.length();
                // get shortcut, i.e. "!c"
                String key = content.substring(0, keyIndex);
                // get the other params after the shortcut, i.e. "2s"
                String rest = content.substring(keyIndex);

                content = commandsShortcuts.get(key) + rest;
            }
        }

        // Check if message starts with "!br" to invoke the specific command
        if (content.toLowerCase().startsWith(GenericUtils.COMMAND_TRIGGER)) {

            // create a scanner to go through every word in the command
            Scanner scanner = new Scanner(content);

            // Get the first word after "!br" i.e. the command key
            scanner.next(); //skip first word (ie !br)
            String commandKey = scanner.next().toLowerCase();

            // Create arraylist with the params passed by the player, i.e. each consecutive word
            ArrayList<String> params = new ArrayList<>();
            while (scanner.hasNext())
                params.add(scanner.next());

            // close the scanner
            scanner.close();

            // Get the command object that matches the command key sent by the player (eg "stats")
            Command command = commandsManager.getCommand(commandKey);

            if (command == null) {
                // The command doesn't exist, send message saying suggesting !br welp
                sendMessage(author, channel, GenericUtils.getBasicEmbedMessage(GenericUtils.ERROR_TITLE,
                        "That's not really a command, do `!br welp` to see all the commands"));
            } else {
                // show typing indicator
                channel.sendTyping().queue();
                // sends the params to the command object to be used
                command.setParams(params);
                // calculate the response from the command (getReply()) and send it
                sendMessage(author, channel, command.getReply());
            }
        }
    }

    /**
     * send a message to the current channel the user is typing in.
     * pass event received from the messages listener and the EmbedBuilder with the message data
     */
    private void sendMessage(User author, MessageChannel channel, EmbedBuilder messageBuilder) {
        channel.sendMessage(baseMessage(author, messageBuilder)).queue();
    }

    /**
     * base message for every message send, basically just set color and footer
     */
    private MessageEmbed baseMessage(User author, EmbedBuilder messageBuilder) {

        // get pipe symbol from hex code
        String bigPipe = String.valueOf(Character.toChars(Integer.parseInt("23AA", 16)));
        // set embed message color
        messageBuilder.setColor(new Color(BattleriteUtils.BATTLERITE_COLOR_PRIMARY));
        // set embed message footer
        messageBuilder.setFooter("Requested by " + author.getName() + bigPipe + " !br welp for more",
                author.getAvatarUrl());

        return messageBuilder.build();
    }

}