package app;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import app.utils.Helper;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    CommandsManager commandsManager;
    Helper helper = new Helper();

    public MessageListener(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        Message message = event.getMessage();
        String content = message.getContentRaw();

        // CHeck if message starts with "!br" to invoke the specific command
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
                command.setParams(params);
                // message.delete().queue();
                sendMessage(event, command.getReply());
            }
        }
    }

    private void sendMessage(MessageReceivedEvent event, EmbedBuilder messageBuilder) {
        event.getChannel().sendMessage(baseMessage(event, messageBuilder)).queue();
    }

    /**
     * Message builders
     */

    public MessageEmbed baseMessage(MessageReceivedEvent event, EmbedBuilder messageBuilder) {

        messageBuilder.setColor(new Color(Helper.BATTLERITE_COLOR_PRIMARY));
        messageBuilder.setFooter("Requested by " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl());

        return messageBuilder.build();
    };

}