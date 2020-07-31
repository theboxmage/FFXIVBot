package com.boxfort.demo.Commands;


import com.boxfort.demo.Abstracts.AbstractCommand;
import com.boxfort.demo.Abstracts.CustomEvent;
import com.boxfort.demo.Entities.Raffle;
import com.boxfort.demo.dao.RaffleDAO;
import com.boxfort.demo.dao.RaffleMemberDAO;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class RaffleCommand extends AbstractCommand {
    private final RaffleDAO raffleDAO;
    private final RaffleMemberDAO raffleMemberDAO;

    @Autowired
    public RaffleCommand(RaffleDAO raffleDAO, RaffleMemberDAO raffleMemberDAO)
    {
        super("raffle");
        this.raffleDAO = raffleDAO;
        this.raffleMemberDAO = raffleMemberDAO;
    }


    @Override
    public void execute(CustomEvent event) {
        MessageCreateEvent messageCreateEvent = (MessageCreateEvent)event.getEvent();
        java.lang.String[] arguments = messageCreateEvent.getMessageContent().split(" ");
        System.out.println("Raffle Event Recieved");
        if(arguments.length < 2 || arguments[1].equals("help"))
        {
            sendCreateHelpMessage(messageCreateEvent);

            return;
        }
        switch(arguments[1].toLowerCase())
        {
            case "create":
                createRaffle(arguments, messageCreateEvent);
                break;
        }

    }

    private void createRaffle(String[] arguments, MessageCreateEvent messageCreateEvent) {
        if(arguments.length < 4)
        {
            sendCreateHelpMessage(messageCreateEvent);
        }
        Raffle raffle = new Raffle();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 3; i < arguments.length; i++)
        {
            stringBuilder.append(arguments[i]).append(i == arguments.length - 1 ? "" : " ");
        }
        raffle.setEndDateTime(LocalDateTime.now().plusDays(Long.parseLong(arguments[2]))
                .truncatedTo(ChronoUnit.HOURS));
        raffle.setAuthorId(messageCreateEvent.getMessageAuthor().getId());
        raffle.setAuthor(messageCreateEvent.getMessageAuthor().getName());
        raffle.setTitle(stringBuilder.toString());
        raffle.setMessageId(messageCreateEvent.getMessageId());

        messageCreateEvent.getChannel().sendMessage(getConfirmation(raffle));

        raffleDAO.save(raffle);
    }

    private EmbedBuilder getConfirmation(Raffle raffle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Raffle **").append(raffle.getTitle()).append("** has begun.\n");
        stringBuilder.append("It will end at **").append(raffle.getEndDateTime()).append(" EST**.\n");


        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Raffle Created");
        embedBuilder.setColor(Color.green);
        embedBuilder.setDescription(stringBuilder.toString());
        embedBuilder.setFooter("Add a reaction to this message to join.");
        return embedBuilder;

    }

    private void sendCreateHelpMessage(MessageCreateEvent messageCreateEvent) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(Color.red);
        embedBuilder.setTitle("Raffle Help");
        embedBuilder.addField("Creating a raffle", "?Raffle create <time in days> <title>\n?Raffle create 7 Mogstation Giveaway");
        embedBuilder.addField("Ending a raffle", "?Raffle end <MessageId/Raffle Name>\n?Raffle end Mogstation Giveaway");

        messageCreateEvent.getChannel().sendMessage(embedBuilder);
    }
}
