package com.boxfort.demo.Services;

import com.boxfort.demo.Abstracts.CustomEvent;
import com.boxfort.demo.Configuration.DiscordBotConfiguration;
import lombok.Getter;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BotService {
    private final DiscordBotConfiguration discordBotConfiguration;
    private final CommandMap commandMap;

    @Getter
    private DiscordApi discordApi;

    @Autowired
    public BotService(DiscordBotConfiguration discordBotConfiguration, CommandMap commandMap) {
        this.discordBotConfiguration = discordBotConfiguration;
        this.commandMap = commandMap;
    }

    @PostConstruct
    private void init() {
        this.discordApi = new DiscordApiBuilder().setToken(discordBotConfiguration.getToken()).login().join();
        this.createDefaultListeners();
    }

    private void createDefaultListeners() {
        this.getDiscordApi().addMessageCreateListener(messageCreateEvent -> {
            String message = messageCreateEvent.getMessageContent();
            if(!messageCreateEvent.getMessageAuthor().asUser().map(User::isBot).orElse(false))
            {
                if(message.length() > 0 && message.startsWith(discordBotConfiguration.getPrefix()))
                {
                    commandMap.execute(new CustomEvent(messageCreateEvent));
                }
            }
        });

    }
}
