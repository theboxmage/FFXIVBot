package com.boxfort.demo.Commands;

import com.boxfort.demo.Abstracts.AbstractCommand;
import com.boxfort.demo.Abstracts.CustomEvent;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.awt.*;

@Component
public class PingCommand extends AbstractCommand {

    @Autowired
    public PingCommand(RestTemplate restTemplate)
    {
        super("ping");
        this.restTemplate = restTemplate;
    }
    private final RestTemplate restTemplate;
    @Override
    public void execute(CustomEvent event) {
        MessageCreateEvent messageCreateEvent = (MessageCreateEvent) event.getEvent();
        messageCreateEvent.getChannel().sendMessage(new EmbedBuilder().setDescription("Pong").setColor(Color.pink));
    }

}
