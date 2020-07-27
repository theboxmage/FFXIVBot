package com.boxfort.demo.Services;

import com.boxfort.demo.Abstracts.AbstractCommand;
import com.boxfort.demo.Abstracts.CustomEvent;
import com.boxfort.demo.Interfaces.DiscordCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommandMap {
    private final List<DiscordCommand> commands;
    private final Map<String, DiscordCommand> commandMap;

    @Autowired
    public CommandMap(List<DiscordCommand> commands) {
        this.commands = commands;
        this.commandMap = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        for (DiscordCommand command : commands) {
            commandMap.put(command.getAlias(), command);
        }
    }

    public void execute(CustomEvent event) {

        DiscordCommand command = commandMap.get(event.getArgs()[0]);
        if(command != null){
            command.execute(event);
        }
    }
}
