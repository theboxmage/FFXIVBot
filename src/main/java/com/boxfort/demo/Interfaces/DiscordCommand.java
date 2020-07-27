package com.boxfort.demo.Interfaces;

import com.boxfort.demo.Abstracts.CustomEvent;
import org.javacord.api.event.Event;

public interface DiscordCommand {
    void execute(CustomEvent event);
    String getAlias();
}
